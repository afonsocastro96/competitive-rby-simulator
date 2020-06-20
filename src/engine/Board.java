package engine;

import logic.things.Pokemon;

public class Board {
    public static final int PLAYER1 = 0;
    public static final int PLAYER2 = 1;
    public static final int TIE = 2;
    public static final int SPEEDTIE = -1;

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
        for(int i = 0; i < player1revealeds.length; ++i){
            player1revealeds[i] = false;
        }
        this.player2Party = player2Party;
        this.player2revealeds = new boolean[player2Party.length];
        for(int i = 0; i < player2revealeds.length; ++i){
            player2revealeds[i] = false;
        }
    }

    public void setRevealed(int player, int index) {
        if (player == PLAYER1)
            player1revealeds[index] = true;
        else
            player2revealeds[index] = true;
    }

    public Pokemon[] getPlayerParty(int player) {
        if(player == PLAYER1)
            return player1Party;
        else
            return player2Party;
    }

    public Pokemon getPlayer1ActivePokemon() {
        return player1Party[player1ActivePokemon];
    }

    public Pokemon getPlayer2ActivePokemon() {
        return player2Party[player2ActivePokemon];
    }

    public int getTurn() {

        return turn;
    }

    public int getPartySize(int player) {
        if(player == PLAYER1)

            return player1Party.length;
        else
            return player2Party.length;
    }

    public Pokemon[] getPlayerRevealedParty(int player) {
        if (player == PLAYER1)
            return getPlayer1revealedParty();
        else
            return getPlayer2revealedParty();
    }

    public int getPlayerWithFasterPokemon() {
        int player1speed = player1Party[player1ActivePokemon].getStatWithModifiers(Pokemon.SPEED);
        int player2speed = player2Party[player2ActivePokemon].getStatWithModifiers(Pokemon.SPEED);

        if(player1speed > player2speed)
            return PLAYER1;
        else if(player2speed > player1speed)
            return PLAYER2;
        else
            return SPEEDTIE;
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

    public Pokemon getPlayerActivePokemon(int player) {
        if (player == PLAYER1)
            return player1Party[player1ActivePokemon];
        else
            return player2Party[player2ActivePokemon];
    }

    public void decrementPlayerCounsciousPokemon(int player) {
        switch(player) {
            case PLAYER1:
                player1CounsciousPokemon--;
                break;
            default:
                player2CounsciousPokemon--;
        }
    }

    public boolean hasConsciousPokemon(int player) {
        switch(player) {
            case PLAYER1:
                return player1CounsciousPokemon != 0;
            default:
                return player2CounsciousPokemon != 0;
        }
    }

    public int checkFaintedPokemon() {
        boolean player1Pokemonfainted = false;
        boolean player2Pokemonfainted = false;
        if(getPlayer1ActivePokemon().getCurrentHp() == 0)
            player1Pokemonfainted = true;
        if(getPlayer2ActivePokemon().getCurrentHp() == 0)
            player2Pokemonfainted = true;
        if(player1Pokemonfainted && player2Pokemonfainted)
            return 2;
        else if(player1Pokemonfainted)
            return PLAYER1;
        else if (player2Pokemonfainted)
            return PLAYER2;
        else
            return -1;
    }

}
