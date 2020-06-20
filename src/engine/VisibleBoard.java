package engine;

import logic.things.Pokemon;

public class VisibleBoard {
    Pokemon[] ownParty;
    Pokemon[] opponentParty;
    int opponentPartySize;
    int turn;
    Pokemon ownActivePokemon;
    Pokemon opponentActivePokemon;

    public Pokemon[] getOwnParty() {
        return ownParty;
    }

    public Pokemon[] getOpponentParty() {
        return opponentParty;
    }

    public int getOpponentPartySize() {
        return opponentPartySize;
    }

    public int getTurn() {
        return turn;
    }

    public Pokemon getOwnActivePokemon() {
        return ownActivePokemon;
    }

    public Pokemon getOpponentActivePokemon() {
        return opponentActivePokemon;
    }

    public VisibleBoard(Pokemon[] ownParty, Pokemon[] opponentParty, int opponentPartySize, int turn, Pokemon ownActivePokemon, Pokemon opponentActivePokemon) {

        this.ownParty = ownParty;
        this.opponentParty = opponentParty;
        this.opponentPartySize = opponentPartySize;
        this.turn = turn;
        this.ownActivePokemon = ownActivePokemon;
        this.opponentActivePokemon = opponentActivePokemon;
    }
}
