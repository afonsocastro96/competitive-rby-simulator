package engine;

import input.InputHandler;
import logic.DamageFormula;
import logic.Rand;
import logic.TypeMatchups;
import logic.database.Moves;
import logic.database.effects.ChargeEffect;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;
import logic.things.Status;
import output.OutputHandler;

import java.util.List;
import java.util.Map;

public class GameEngine {

    public static final int END = 0;
    public static final int PICK_MOVES_STATE = 2;

    public static final int ENDLESS_BATTLE_THRESHOLD = 1000;

    public static final int SKIP_ATTACK = 1;
    public static final int DONT_SKIP_ATTACK = 2;

    private Board board = null;
    private Player playerToMove;

    public GameEngine(int inputMode, int outputMode) {
        InputHandler.setMode(inputMode);
        OutputHandler.setMode(outputMode);
        init();
    }

    private void init() {
        List<Pokemon[]> teams = InputHandler.getTeams();
        this.board = new Board(teams.get(0), teams.get(1));
    }

    public Board getBoard() {
        return board;
    }

    public Player game() {
        init();
        Player winner;
        while(true) {
            Map<Player, String> moves = InputHandler.getMoves(board);
            OutputHandler.outputText("\nBEGINNING OF TURN " + board.turn + "\n");
            ++board.turn;
            this.playerToMove = board.getPlayerToMoveFirst(moves);
            // If the player is charging a move, that's the move they are using this turn
            if(board.getPlayerActivePokemon(this.playerToMove).isChargingMove())
                playerMove(board.getPlayerActivePokemon(this.playerToMove).getMoveBeingCharged());
            else
                playerMove(moves.get(this.playerToMove));
            OutputHandler.outputText("");
            winner = isGameOver();
            if(winner != Player.NONE)
                return winner;
            swapPlayerToMove();
            // If the player is charging a move, that's the move they are using this turn
            if(board.getPlayerActivePokemon(this.playerToMove).isChargingMove())
                playerMove(board.getPlayerActivePokemon(this.playerToMove).getMoveBeingCharged());
            else
                playerMove(moves.get(this.playerToMove));
            winner = isGameOver();
            if(winner != Player.NONE)
                return winner;
        }
    }

    // TODO: Implement switching out. Remove all volatile statuses, reset boosts and drops (including burn/paralysis),
    // restore typing changed by Conversion, reset bad poison counter, reset priority modifier back to 0,
    // reset invulnerability from fly/dig in case of a full paralysis, restore stats changed by Transform

    private void resolveAfterMovingStatuses(Pokemon attacker, Pokemon defender) {
        int status_result;
        for(Status status : attacker.getVolatileStatuses()) {
            if(status.getTrigger() == Status.Trigger.AFTER_MOVING) {
                status_result = status.resolveStatus(attacker, defender);
                if (status_result != Status.NOTHING)
                    printStatusMessage(attacker.getSpecies(), status_result);
            }
        }
        if(attacker.getStatus() != null) {
            if (attacker.getStatus().getTrigger().equals(Status.Trigger.AFTER_MOVING)) {
                status_result = attacker.getStatus().resolveStatus(attacker, defender);
                if (status_result != Status.NOTHING)
                    printStatusMessage(attacker.getSpecies(), status_result);
            }
        }
    }

    // Resolve all the  checks that may stop the Pokemon from attacking
    private int resolveBeforeMovingConditions(Pokemon attacker, Pokemon defender, Move move) {
        // Resolve statuses that kick in before moving, such as sleep, paralysis and confusion
        if(resolveBeforeMovingStatuses(attacker, defender) == SKIP_ATTACK) return SKIP_ATTACK;
        // If no move is already being charged and the Pokemon is using a charging move, set up charging turn
        if(!attacker.isChargingMove() && move.getEffect().getName().equals("Charge Effect")) {
            resolveChargingMoveEffect(attacker, move);
            return SKIP_ATTACK;
        }
        return DONT_SKIP_ATTACK;
    }

    private int resolveBeforeMovingStatuses(Pokemon attacker, Pokemon defender) {
        int status_result;

        // Priority order: Freeze/Sleep -> Recharge -> Flinch -> Confusion -> Paralysis
        if(attacker.getStatus() != null) {
            if (attacker.getStatus().getName().equals("Freeze")) {
                status_result = attacker.getStatus().resolveStatus(attacker, defender);
                printStatusMessage(attacker.getSpecies(), status_result);
                return SKIP_ATTACK;
            }
            if (attacker.getStatus().getName().equals("Sleep")) {
                status_result = attacker.getStatus().resolveStatus(attacker, defender);
                printStatusMessage(attacker.getSpecies(), status_result);
                return SKIP_ATTACK;
            }
        }

        for(int i = 0; i < attacker.getVolatileStatuses().size(); ++i) {
            Status status = attacker.getVolatileStatuses().get(i);
            switch (status.getName()) {
            case "Recharging":
            case "Flinch":
                status_result = status.resolveStatus(attacker, defender);
                printStatusMessage(attacker.getSpecies(), status_result);
                return SKIP_ATTACK;

            case "Confusion":
                    status_result = status.resolveStatus(attacker, defender);
                    if (status_result == Status.HURT_ITSELF_CONFUSION) {
                        printStatusMessage(attacker.getSpecies(), status_result);
                        return SKIP_ATTACK;
                    } else if (status_result == Status.SNAPPED_OUT_CONFUSION)
                        printStatusMessage(attacker.getSpecies(), status_result);
            }
        }

        if(attacker.getStatus() != null) {
            if (attacker.getStatus().getName().equals("Paralysis")) {
                status_result = attacker.getStatus().resolveStatus(attacker, defender);
                if (status_result != Status.NOTHING) {
                    printStatusMessage(attacker.getSpecies(), status_result);
                    return SKIP_ATTACK;
                }
            }
        }

        return DONT_SKIP_ATTACK;
    }

    private void resolveChargingMoveEffect(Pokemon attacker, Move move) {
        ChargeEffect ce = (ChargeEffect) move.getEffect();
        attacker.setMoveBeingCharged(move.getName());
        if(ce.appliesInvulnerability())
            attacker.toggleInvulnerability();
        OutputHandler.outputText(attacker.getSpecies() + " " + ce.getChargingMessage());
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
            case Status.THAWED:
                OutputHandler.outputText(attacker_name + " thawed out!");
                break;
            case Status.FROZEN_SOLID:
                OutputHandler.outputText(attacker_name + " is frozen solid!");
                break;
            case Status.ENERGY_DRAINED:
                OutputHandler.outputText(attacker_name + " had its energy drained!");
                break;
        }
    }

    private void swapPlayerToMove() {
        if(this.playerToMove == Player.PLAYER1)
            this.playerToMove = Player.PLAYER2;
        else
            this.playerToMove = Player.PLAYER1;
    }


    private void playerMove(String action) {
        Move move = Moves.getMove(action);
        Pokemon attacker;
        Pokemon defender;
        if (this.playerToMove == Player.PLAYER1) {
            attacker = board.getPlayer1ActivePokemon();
            defender = board.getPlayer2ActivePokemon();
        } else {
            attacker = board.getPlayer2ActivePokemon();
            defender = board.getPlayer1ActivePokemon();
        }

        // Check if any status or the selection of a charging move stops us from attacking this turn
        if(resolveBeforeMovingConditions(attacker, defender, move) != SKIP_ATTACK) {
            //Ok, nothing stopped the attacker from moving, let's progress
            OutputHandler.outputText(attacker.getSpecies() + " used " + move.getName() + "!");
            attacker.reduceMovePP(move.getName());
            // If we just executed a charge move, clear it
            if(move.getEffect().getName().equals("Charge Effect")) {
                attacker.clearMoveBeingCharged();
                if (((ChargeEffect) move.getEffect()).appliesInvulnerability())
                    attacker.toggleInvulnerability();
            }
            // If the move is Dream Eater, check if the opponent is asleep
            if(move.getName().equals("Dream Eater") && defender.getStatus() == null) {
                if (!defender.getStatus().getName().equals("Sleep"))
                    printEffectResultText(Effect.BUT_IT_FAILED, attacker.getSpecies(), defender.getSpecies());
            }
            // Accuracy check
            else if (Rand.attackMissed(move.getAccuracy(), attacker, defender)) {
                OutputHandler.outputText(attacker.getSpecies() + "'s attack missed!");
                //HJK, JK, Selfdestruct and Explosion have side effects if they miss, so resolve those
                if (move.getName().equals("Hi Jump Kick") || move.getName().equals("Jump Kick")) {
                    attacker.inflictDamage(1);
                    OutputHandler.outputText(attacker.getSpecies() + " is hurt!");
                } else if (move.getEffect().getName().equals("Explode Effect")) {
                    attacker.setCurrentHp(0);
                }
                Player fainted = board.checkFaintedPokemon();
                // If we fainted from recoil/if we used explosion, the turn is over
                if (fainted != Player.NONE) {
                    resolveFaint(fainted);
                    return;
                }
            } else { // Move didn't miss, determine damage to do
                int damageDealt = 0;
                if (move.getPower() > 0) {
                    boolean criticalHit = Rand.criticalHit(move, attacker);

                    int damage = DamageFormula.calcDamage(attacker, defender, move, criticalHit);
                    if(damage == 0) { // Should only happen with Psywave in case of the Desync Glitch
                        System.out.println("(Note: Desync Clause triggered)");
                        printEffectResultText(Effect.BUT_IT_FAILED, attacker.getSpecies(), defender.getSpecies());
                    }
                    else {
                        damageDealt = defender.inflictDamage(damage);
                        if (move.getPower() != 1) { // Night Shade, Seismic Toss, Super Fang, Dragon Rage, Psybeam, Sonicboom
                            if (criticalHit && move.getPower() != 2) // OHKO moves
                                OutputHandler.outputText("\tA critical hit!");
                            printEffectivessText(defender, move);
                        }
                        if (damageDealt < 0)
                            OutputHandler.outputText("The substitute took damage for " + defender.getSpecies() + "!");
                        else if (damageDealt == 0)
                            OutputHandler.outputText(defender.getSpecies() + "'s substitute broke!");
                        else
                            OutputHandler.outputText(defender.getSpecies() + " lost " + (int) ((double) damageDealt / defender.getHp() * 100) + "% of its health!");
                    }
                }
                //Apply any secondary effects that kick in regardless of the opponent fainting or not (eg: recoil)
                if (!move.getEffect().isEffectAppliedAfterOpponentFainted()) {
                    int effectResult = move.getEffect().resolveEffect(attacker, defender, move, damageDealt);
                    printEffectResultText(effectResult, attacker.getSpecies(), defender.getSpecies());
                }
                Player fainted = board.checkFaintedPokemon();
                if (fainted != Player.NONE) {
                    resolveFaint(fainted);
                    return;
                }
                //Apply any secondary effects that kick in only if the opponent didn't faint (ex: freeze / paralysis)
                if (move.getEffect().isEffectAppliedAfterOpponentFainted()) {
                    int effectResult = move.getEffect().resolveEffect(attacker, defender, move, damageDealt);
                    printEffectResultText(effectResult, attacker.getSpecies(), defender.getSpecies());
                }
                fainted = board.checkFaintedPokemon();
                if (fainted != Player.NONE)
                    resolveFaint(fainted);
            }
        }

        //Apply statuses that kick in after the player moved and only if no Pokémon fainted (ex: poison / burn)
        // Note that these also get applied if the player didn't have a chance to move (e.g. because of flinch)
        resolveAfterMovingStatuses(attacker, defender);
        Player fainted = board.checkFaintedPokemon();
        if (fainted != Player.NONE)
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
            case Effect.SUCCESSFULLY_PARALYSED:
                OutputHandler.outputText(defender_name + " is paralyzed! It may be unable to move!");
                break;
            case Effect.BUT_IT_FAILED:
                OutputHandler.outputText("But it failed!");
                break;
            case Effect.SUCCESSFULLY_PUT_TO_SLEEP:
                OutputHandler.outputText(defender_name + " fell asleep!");
                break;
            case Effect.SUCCESSFULLY_FROZEN:
                OutputHandler.outputText(defender_name + " was frozen solid!");
                break;
            case Effect.SUCCESSFULLY_BADLY_POISONED:
                OutputHandler.outputText(defender_name + " was badly poisoned!");
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
            case Effect.SPEED_DROPPED:
                OutputHandler.outputText(defender_name + "'s speed fell!");
                break;
            case Effect.ENERGY_DRAINED:
                OutputHandler.outputText(defender_name + " had its energy drained!");
                break;
            case Effect.LIGHT_SCREEN_PROTECTED:
                OutputHandler.outputText(attacker_name + "'s protected against special attacks!");
                break;
            case Effect.REFLECT_GAINED_ARMOUR:
                OutputHandler.outputText(attacker_name + " gained armor!");
                break;
            case Effect.REST_SUCCESSFUL:
                OutputHandler.outputText(attacker_name + " slept and became healthy!");
                break;
            case Effect.ATTACK_SHARPLY_RAISED:
                OutputHandler.outputText(attacker_name + "'s attack rose sharply!");
                break;
            case Effect.COINS_SCATTERED:
                OutputHandler.outputText("Coins scattered everywhere!");
                break;
            case Effect.ATTACK_RAISED:
                OutputHandler.outputText(attacker_name + "'s attack rose!");
                break;
            case Effect.BECAME_CONFUSED:
                OutputHandler.outputText(defender_name + " became confused!");
                break;
            case Effect.PUT_UP_SUBSTITUTE:
                OutputHandler.outputText(attacker_name + " put up a substitute!");
                break;
            case Effect.DEFENCE_DROP:
                OutputHandler.outputText(defender_name + "'s defense fell! ");
                break;
            case Effect.SHROUDED_IN_MIST:
                OutputHandler.outputText(defender_name + " became shrouded in mist!");
                break;
            case Effect.DEFENCE_SHARPLY_RAISED:
                OutputHandler.outputText(defender_name + "'s defense sharply rose!");
                break;
            case Effect.DEFENCE_RAISED:
                OutputHandler.outputText(defender_name + "'s defense rose!");
                break;
            case Effect.ATTACK_DROPPED:
                OutputHandler.outputText(defender_name + "'s attack dropped!");
                break;
            case Effect.DEFENCE_SHARPLY_DROPPED:
                OutputHandler.outputText(defender_name + "'s defense dropped sharply!");
                break;
            case Effect.SUCCESSFULLY_POISONED:
                OutputHandler.outputText(defender_name + " was poisoned!");
                break;
            case Effect.GETTING_PUMPED:
                OutputHandler.outputText(attacker_name + " is getting pumped!");
                break;
            case Effect.STATUS_CHANGES_ELIMINATED:
                OutputHandler.outputText("Status changes are eliminated!");
                break;
            case Effect.CONVERTED_TYPE:
                OutputHandler.outputText("Converted type to " + defender_name + "'s!");
                break;
            case Effect.DREAM_EATEN:
                OutputHandler.outputText(defender_name + "'s name was eaten!");
                break;
            case Effect.BUT_NOTHING_HAPPENED:
                OutputHandler.outputText("But nothing happened!");
                break;
            case Effect.ONE_HIT_KO:
                OutputHandler.outputText("One-hit KO!");
                break;
            case Effect.WAS_SEEDED:
                OutputHandler.outputText(defender_name + " was seeded!");
                break;
            case Effect.ACCURACY_DROPPED:
                OutputHandler.outputText(defender_name + "'s accuracy dropped!");
                break;
            case Effect.EVASION_RAISED:
                OutputHandler.outputText(attacker_name + "'s evasion rose!");
                break;


        }
    }

    private void resolveFaint(Player fainted) {
        switch (fainted) {
            case BOTH:
                OutputHandler.outputText(board.getPlayer1ActivePokemon().getSpecies() + " fainted!");
                OutputHandler.outputText("The opposing " + board.getPlayer2ActivePokemon().getSpecies() + " fainted!");
                board.decrementPlayerCounsciousPokemon(Player.PLAYER1);
                board.decrementPlayerCounsciousPokemon(Player.PLAYER2);
                break;
            case PLAYER1:
                OutputHandler.outputText(board.getPlayer1ActivePokemon().getSpecies() + " fainted!");
                board.decrementPlayerCounsciousPokemon(Player.PLAYER1);

                break;
            case PLAYER2:
                OutputHandler.outputText("The opposing " + board.getPlayer2ActivePokemon().getSpecies() + " fainted!");
                board.decrementPlayerCounsciousPokemon(Player.PLAYER2);
                break;
        }
    }

    private Player isGameOver() {
        boolean player1HasConsciousPokemon = board.hasConsciousPokemon(Player.PLAYER1);
        boolean player2HasConsciousPokemon = board.hasConsciousPokemon(Player.PLAYER2);
        if((!player1HasConsciousPokemon && !player2HasConsciousPokemon) || board.turn == ENDLESS_BATTLE_THRESHOLD) {
            OutputHandler.outputText("Tie between player 1 and 2!");
            return Player.BOTH;
        }
        else if(!player1HasConsciousPokemon) {
            OutputHandler.outputText("Player 2 won the game!");
            return Player.PLAYER2;
        }
        else if(!player2HasConsciousPokemon) {
            OutputHandler.outputText("Player 1 won the game!");
            return Player.PLAYER1;
        }
        else
            return Player.NONE;
    }

}
