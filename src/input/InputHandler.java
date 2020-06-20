package input;

import engine.Board;
import engine.VisibleBoard;
import input.simulation.PickMove;
import input.simulation.SendTeam;
import logic.things.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    public static final int SIMULATION = 1;

    private static int inputMode;

    public static void setMode(int mode) {
        inputMode = mode;
    }

    public static List<String> getMoves(Board board) {
        List<String> ret = new ArrayList<>();
        VisibleBoard player1VisibleBoard = new VisibleBoard(
                board.getPlayerParty(Board.PLAYER1),
                board.getPlayerRevealedParty(Board.PLAYER2),
                board.getPartySize(Board.PLAYER2),
                board.getTurn(),
                board.getPlayer1ActivePokemon(),
                board.getPlayer2ActivePokemon());
        VisibleBoard player2VisibleBoard = new VisibleBoard(
                board.getPlayerParty(Board.PLAYER2),
                board.getPlayerRevealedParty(Board.PLAYER1),
                board.getPartySize(Board.PLAYER1),
                board.getTurn(),
                board.getPlayer2ActivePokemon(),
                board.getPlayer1ActivePokemon());
        switch (inputMode) {
            case SIMULATION:
                String player1Move = PickMove.pickplayer1Move(player1VisibleBoard);
                String player2Move = PickMove.pickplayer2Move(player2VisibleBoard);
                ret.add(player1Move);
                ret.add(player2Move);
                break;
        }
        return ret;
    }

    public static List<Pokemon[]> getTeams() {
        List<Pokemon[]> ret = new ArrayList<>();
        switch (inputMode) {
            case SIMULATION:
                ret.add(SendTeam.sendPlayer1Team());
                ret.add(SendTeam.sendPlayer2Team());
        }
        return ret;
    }

}
