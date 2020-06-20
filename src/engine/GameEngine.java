package engine;

import engine.states.PickMovesState;
import engine.states.StartState;
import input.InputHandler;
import logic.DamageFormula;
import logic.Rand;
import logic.TypeMatchups;
import logic.database.Moves;
import logic.database.Pokemons;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;
import logic.things.Status;
import output.OutputHandler;

import java.util.List;

public class GameEngine {

    public static final int END = 0;
    public static final int PICK_MOVES_STATE = 2;

    public static final int ENDLESS_BATTLE_THRESHOLD = 1000;

    public static final int SKIP_ATTACK = 1;
    public static final int DONT_SKIP_ATTACK = 2;

    private State state;
    private Board board = null;

    public GameEngine(int inputMode, int outputMode) {
        InputHandler.setMode(inputMode);
        OutputHandler.setMode(outputMode);
        init();
    }

    private void handleStateChange(int nextState) {
        switch (nextState) {
            case PICK_MOVES_STATE:
                state = new PickMovesState();
        }
    }

    private void init() {
        List<Pokemon[]> teams = InputHandler.getTeams();
        this.board = new Board(teams.get(0), teams.get(1));
        this.state = new StartState();
    }

    public int game() {
        init();
        int winner;
        while(true) {
            List<String> moves = InputHandler.getMoves(board);
            OutputHandler.outputText("\nBEGINNING OF TURN " + board.turn + "\n");
            ++board.turn;
            int playerToMove = board.getPlayerWithFasterPokemon();
            if(playerToMove == Board.SPEEDTIE) {
                playerToMove = Rand.resolveSpeedTie();
            }
            playerMove(playerToMove, moves.get(playerToMove));
            OutputHandler.outputText("");
            winner = gameOver();
            if(winner != -1)
                return winner;
            playerToMove = changePlayerToMove(playerToMove);
            playerMove(playerToMove, moves.get(playerToMove));
            winner = gameOver();
            if(winner != -1)
                return winner;
        }
    }

    private void resolveAfterMovingStatuses(Pokemon attacker) {
        int status_result;
        for(Status status : attacker.getVolatileStatuses()) {
            if(status.getTrigger() == Status.Trigger.AFTER_MOVING) {
                status_result = status.resolveStatus(attacker);
                if (status_result != Status.NOTHING)
                    printStatusMessage(attacker.getSpecies(), status_result);
            }
        }
        if(attacker.getStatus() != null) {
            if (attacker.getStatus().getTrigger().equals(Status.Trigger.AFTER_MOVING)) {
                status_result = attacker.getStatus().resolveStatus(attacker);
                if (status_result != Status.NOTHING)
                    printStatusMessage(attacker.getSpecies(), status_result);
            }
        }
    }

    private int resolveBeforeMovingStatuses(Pokemon attacker) {
        // Priority order: Freeze -> Recharge -> Flinch -> Confusion -> Other permanent statuses
        if(attacker.getStatus() != null) {
            if (attacker.getStatus().getName().equals("Freeze")) {
                OutputHandler.outputText(attacker.getSpecies() + " is frozen solid!");
                return SKIP_ATTACK;
            }
        }

        boolean recharge = false;

        int status_result;
        for(int i = 0; i < attacker.getVolatileStatuses().size(); ++i) {
            Status status = attacker.getVolatileStatuses().get(i);
            if(status.getName().equals("Recharging")) {
                status_result = status.resolveStatus(attacker);
                printStatusMessage(attacker.getSpecies(), status_result);
                recharge = true;
            }
        }

        if(attacker.getStatus() != null) {
            if (attacker.getStatus().getTrigger().equals(Status.Trigger.BEFORE_MOVING)) {
                status_result = attacker.getStatus().resolveStatus(attacker);
                if (status_result != Status.NOTHING) {
                    printStatusMessage(attacker.getSpecies(), status_result);
                    return SKIP_ATTACK;
                }
            }
        }

        for(Status status : attacker.getVolatileStatuses()) {
            if(status.getTrigger() == Status.Trigger.BEFORE_MOVING && !status.getName().equals("Recharging")) {
                status_result = status.resolveStatus(attacker);
                if (status_result != Status.NOTHING){
                    printStatusMessage(attacker.getSpecies(), status_result);
                    return SKIP_ATTACK;
                }
            }
        }

        if(recharge)
            return SKIP_ATTACK;
        return DONT_SKIP_ATTACK;
    }

    private void printStatusMessage(String attacker_name, int status_result) {
        switch(status_result) {
            case Status.HURT_BY_POISON:
                OutputHandler.outputText(attacker_name + " is hurt by poison!");
                break;
            case Status.FAST_ASLEEP:
                OutputHandler.outputText(attacker_name + " is fast asleep!");
                break;
            case Status.FLINCH:
                OutputHandler.outputText(attacker_name + " flinched!");
                break;
            case Status.FULLY_PARALYZED:
                OutputHandler.outputText(attacker_name + " is paralyzed! It can't move!");
                break;
            case Status.HURT_BY_BURN:
                OutputHandler.outputText(attacker_name + " is hurt by its burn!");
                break;
            case Status.HURT_ITSELF_CONFUSION:
                OutputHandler.outputText(attacker_name + " hurt itself in its confusion!");
                break;
            case Status.RECHARGE:
                OutputHandler.outputText(attacker_name + " must recharge!");
                break;
            case Status.SNAPPED_OUT_CONFUSION:
                OutputHandler.outputText(attacker_name + " snapped out of confusion!");
                break;
            case Status.WOKE_UP:
                OutputHandler.outputText(attacker_name + " woke up!");
                break;

        }
    }

    private int changePlayerToMove(int playerToMove) {
        return 1 - playerToMove;
    }


    private void playerMove(int player, String action) {
        Move move = Moves.getMove(action);
        Pokemon attacker;
        Pokemon defender;
        switch(player) {
            case Board.PLAYER1:
                attacker = board.getPlayer1ActivePokemon();
                defender = board.getPlayer2ActivePokemon();
                break;
            default:
                attacker = board.getPlayer2ActivePokemon();
                defender = board.getPlayer1ActivePokemon();
        }

        //Resolve statuses that kick in before moving, such as sleep, paralysis and confusion
        if(resolveBeforeMovingStatuses(attacker) == SKIP_ATTACK)
            return;

        //Ok, nothing stopped the attacker from moving, let's progress
        OutputHandler.outputText(attacker.getSpecies() + " used " + move.getName() + "!");
        attacker.reduceMovePP(move);
        if(Rand.attackMissed(move.getAccuracy())) {
            OutputHandler.outputText(attacker.getSpecies() + "'s attack missed!");
            return;
        }
        //Determine damage to do
        int damage = 0;
        if(move.getPower() > 0) {
            boolean criticalHit = Rand.criticalHit(Pokemons.getPokemon(attacker.getSpecies()).getBase_speed());
            damage = DamageFormula.calcDamage(attacker, defender, move, criticalHit);
            int damageDealt = defender.inflictDamage(damage);
            if(criticalHit) {
                OutputHandler.outputText("\tA critical hit!");
                if(player==Board.PLAYER1)
                    Rand.crits++;
            }
            else if(player==Board.PLAYER1) Rand.nonCrits++;
            printEffectivessText(defender, move);
            OutputHandler.outputText(defender.getSpecies() + " lost " + (int)((double)damageDealt / defender.getHp() * 100) + "% of its health!");
        }
        //Apply any secondary effects that kick in regardless of the opponent fainting or not (ex: recoil)
        if(!move.getEffect().isEffectAppliedAfterOpponentFainted()) {
            int effectResult = move.getEffect().resolveEffect(attacker, defender, move, damage);
            printEffectResultText(effectResult, attacker.getSpecies(), defender.getSpecies());
        }
        int fainted = board.checkFaintedPokemon();
        if (fainted > -1) {
            resolveFaint(fainted);
            return;
        }
        //Apply any secondary effects that kick in only if the opponent didn't faint (ex: freeze / paralysis)
        if(move.getEffect().isEffectAppliedAfterOpponentFainted()) {
            int effectResult = move.getEffect().resolveEffect(attacker, defender, move, damage);
            printEffectResultText(effectResult, attacker.getSpecies(), defender.getSpecies());
        }
        fainted = board.checkFaintedPokemon();
        if (fainted > -1)
            resolveFaint(fainted);

        //Apply statuses that kick in after the player moved (ex: poison / burn)
        resolveAfterMovingStatuses(attacker);
        fainted = board.checkFaintedPokemon();
        if (fainted > -1)
            resolveFaint(fainted);

    }

    private void printEffectivessText(Pokemon defender, Move move) {
        if(TypeMatchups.isNotVeryEffective(move.getType(), defender.getType1(), defender.getType2()))
            OutputHandler.outputText("It's not very effective...");
        else if(TypeMatchups.isSuperEffective(move.getType(), defender.getType1(), defender.getType2()))
            OutputHandler.outputText("It's super effective!");
        else if(TypeMatchups.doesntAffect(move.getType(), defender.getType1(), defender.getType2()))
            OutputHandler.outputText("It doesnt affect " + defender.getSpecies() + "...");
    }


    private void printEffectResultText(int effectResult, String attacker_name, String defender_name) {
        switch(effectResult){
            case Effect.ALREADY_PARALYZED:
                OutputHandler.outputText(defender_name + " is already paralyzed.");
                break;
            case Effect.SUCCESFULLY_PARALYZED:
                OutputHandler.outputText(defender_name + " is paralyzed! It may be unable to move!");
                break;
            case Effect.BUT_IT_FAILED:
                OutputHandler.outputText("But it failed!");
                break;
            case Effect.SUCCESSFULLY_PUT_TO_SLEEP:
                OutputHandler.outputText(defender_name + " fell asleep!");
                break;
            case Effect.SUCCESFULLY_FROZEN:
                OutputHandler.outputText(defender_name + " was frozen solid!");
                break;
            case Effect.RECOVERY_SUCCESSFUL:
                OutputHandler.outputText(attacker_name + " restored its HP.");
                break;
            case Effect.SPEED_SHARPLY_RAISED:
                 OutputHandler.outputText(attacker_name + "'s speed rose sharply!");
                 break;
            case Effect.RECOIL_SUCCESSFUL:
                OutputHandler.outputText(defender_name + " is damaged by the recoil!");
                break;
            case Effect.SUCCESSFULLY_BURNED:
                OutputHandler.outputText(defender_name + " was burned!");
                break;
            case Effect.SPECIAL_DROP:
                OutputHandler.outputText(defender_name + "'s special fell!");
                break;
            case Effect.SPECIAL_SHARPLY_RAISED:
                OutputHandler.outputText(attacker_name + "'s special rose sharply!");
                break;
            case Effect.SPEED_DROP:
                OutputHandler.outputText(defender_name + "'s speed fell!");
                break;
            case Effect.ENERGY_DRAINED:
                OutputHandler.outputText(defender_name + " had its enegy drained!");

        }
    }

    private void resolveFaint(int fainted) {
        switch (fainted) {
            case 2:
                OutputHandler.outputText(board.getPlayer1ActivePokemon().getSpecies() + " fainted!");
                OutputHandler.outputText("The opposing " + board.getPlayer2ActivePokemon().getSpecies() + " fainted!");
                board.decrementPlayerCounsciousPokemon(Board.PLAYER1);
                board.decrementPlayerCounsciousPokemon(Board.PLAYER2);
                break;
            case Board.PLAYER1:
                OutputHandler.outputText(board.getPlayer1ActivePokemon().getSpecies() + " fainted!");
                board.decrementPlayerCounsciousPokemon(Board.PLAYER1);

                break;
            case Board.PLAYER2:
                OutputHandler.outputText("The opposing " + board.getPlayer2ActivePokemon().getSpecies() + " fainted!");
                board.decrementPlayerCounsciousPokemon(Board.PLAYER2);
                break;
        }
    }

    private int gameOver() {
        boolean player1HasConsciousPokemon = board.hasConsciousPokemon(Board.PLAYER1);
        boolean player2HasConsciousPokemon = board.hasConsciousPokemon(Board.PLAYER2);
        if((!player1HasConsciousPokemon && !player2HasConsciousPokemon) || board.turn == ENDLESS_BATTLE_THRESHOLD) {
            OutputHandler.outputText("Tie between player 1 and 2!");
            return Board.TIE;
        }
        else if(!player1HasConsciousPokemon) {
            OutputHandler.outputText("Player 2 won the game!");
            return Board.PLAYER2;
        }
        else if(!player2HasConsciousPokemon) {
            OutputHandler.outputText("Player 1 won the game!");
            return Board.PLAYER1;
        }
        else
            return -1;
    }

}
