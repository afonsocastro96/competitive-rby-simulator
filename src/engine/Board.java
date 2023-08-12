package engine;

import logic.Rand;
import logic.things.Pokemon;

import java.util.Arrays;
import java.util.Map;

public class Board {
    private Pokemon[] player1Party;
    private boolean[] player1revealeds;
    private Pokemon[] player2Party;
    private boolean[] player2revealeds;

    private int player1ActivePokemon = 0;
    private int player2ActivePokemon = 0;

    private int player1CounsciousPokemon;
    private int player2CounsciousPokemon;

    int turn = 1;

    public Board(Pokemon[] player1Party, Pokemon[] player2Party) {
        this.player1Party = player1Party;
        this.player1revealeds = new boolean[player1Party.length];
        this.player1CounsciousPokemon = player1Party.length;
        this.player2CounsciousPokemon = player2Party.length;
        Arrays.fill(player1revealeds, false);
        this.player2Party = player2Party;
        this.player2revealeds = new boolean[player2Party.length];
        Arrays.fill(player2revealeds, false);
    }

    public void setRevealed(Player player, int index) {
        if (player == Player.PLAYER1)
            player1revealeds[index] = true;
        else if(player == Player.PLAYER2)
            player2revealeds[index] = true;
        else throw new IllegalArgumentException();
    }

    public Pokemon[] getPlayerParty(Player player) {
        if(player == Player.PLAYER1)
            return player1Party;
        else if(player == Player.PLAYER2)
            return player2Party;
        else throw new IllegalArgumentException();
    }

    public Pokemon getPlayer1ActivePokemon() {
        return player1Party[player1ActivePokemon];
    }

    public Pokemon getPlayer2ActivePokemon() {
        return player2Party[player2ActivePokemon];
    }

    public int getTurn() { return turn; }

    public int getPartySize(Player player) {
        if(player == Player.PLAYER1)
            return player1Party.length;
        else if(player == Player.PLAYER2)
            return player2Party.length;
        else throw new IllegalArgumentException();
    }

    public Pokemon[] getPlayerRevealedParty(Player player) {
        if (player == Player.PLAYER1)
            return getPlayer1revealedParty();
        else if(player == Player.PLAYER2)
            return getPlayer2revealedParty();
        else throw new IllegalArgumentException();
    }

    public Player getPlayerToMoveFirst(Map<Player, String> moves) {
        updatePriorityModifier(moves);
        int player1priority = player1Party[player1ActivePokemon].getPriorityModifier();
        int player2priority = player2Party[player2ActivePokemon].getPriorityModifier();

        int player1speed = player1Party[player1ActivePokemon].getStatWithModifiers(Pokemon.SPEED);
        int player2speed = player2Party[player2ActivePokemon].getStatWithModifiers(Pokemon.SPEED);

        if(player1priority > player2priority)
            return Player.PLAYER1;
        else if(player2priority > player1priority)
            return Player.PLAYER2;
        else {
            if (player1speed > player2speed)
                return Player.PLAYER1;
            else if (player2speed > player1speed)
                return Player.PLAYER2;
            else
                return Rand.resolveSpeedTie();
        }
    }

    private void updatePriorityModifier(Map<Player, String> moves) {
        if(moves.get(Player.PLAYER1).equals("Quick Attack"))
            this.player1Party[player1ActivePokemon].setPriorityModifier(1);
        else if(moves.get(Player.PLAYER1).equals("Counter"))
            this.player1Party[player1ActivePokemon].setPriorityModifier(-1);

        if(moves.get(Player.PLAYER2).equals("Quick Attack"))
            this.player2Party[player2ActivePokemon].setPriorityModifier(1);
        else if(moves.get(Player.PLAYER2).equals("Counter"))
            this.player2Party[player2ActivePokemon].setPriorityModifier(-1);
    }

    private Pokemon[] getPlayer1revealedParty() {
        int countRevealed = 0;
        for(boolean b : player1revealeds) {
            if(b)
                ++countRevealed;
        }
        Pokemon[] ret = new Pokemon[countRevealed];
        int counter = 0;
        for(int i = 0; i < player1Party.length; ++i) {
            if(player1revealeds[i]) {
                ret[counter] = player1Party[i];
                ++counter;
            }

        }
        return ret;
    }

    private Pokemon[] getPlayer2revealedParty() {
        int countRevealed = 0;
        for(boolean b : player2revealeds) {
            if(b)
                ++countRevealed;
        }
        Pokemon[] ret = new Pokemon[countRevealed];
        int counter = 0;
        for(int i = 0; i < player2Party.length; ++i) {
            if(player2revealeds[i]) {
                ret[counter] = player2Party[i];
                ++counter;
            }

        }
        return ret;
    }

    public Pokemon getPlayerActivePokemon(Player player) {
        if (player == Player.PLAYER1)
            return player1Party[player1ActivePokemon];
        else
            return player2Party[player2ActivePokemon];
    }

    public void decrementPlayerCounsciousPokemon(Player player) {
        switch(player) {
            case PLAYER1:
                player1CounsciousPokemon--;
                break;
            default:
                player2CounsciousPokemon--;
        }
    }

    public boolean hasConsciousPokemon(Player player) {
        switch(player) {
            case PLAYER1:
                return player1CounsciousPokemon != 0;
            default:
                return player2CounsciousPokemon != 0;
        }
    }

    public Player checkFaintedPokemon() {
        boolean player1Pokemonfainted = false;
        boolean player2Pokemonfainted = false;
        if(getPlayer1ActivePokemon().getCurrentHp() == 0)
            player1Pokemonfainted = true;
        if(getPlayer2ActivePokemon().getCurrentHp() == 0)
            player2Pokemonfainted = true;
        if(player1Pokemonfainted && player2Pokemonfainted)
            return Player.BOTH;
        else if(player1Pokemonfainted)
            return Player.PLAYER1;
        else if (player2Pokemonfainted)
            return Player.PLAYER2;
        else
            return Player.NONE;
    }

}
