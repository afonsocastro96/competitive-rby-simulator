package engine.states;

import engine.Board;
import engine.GameEngine;
import engine.State;
import output.OutputHandler;

public class StartState extends State {
    @Override
    public void action(Board board) {
        OutputHandler.outputText("Battle started between players 1 and 2!");
        OutputHandler.outputText("");
        OutputHandler.outputText("Go! " + board.getPlayerActivePokemon(Board.PLAYER1).getSpecies() + "!");
        OutputHandler.outputText("Player 2 sent out " + board.getPlayerActivePokemon(Board.PLAYER2).getSpecies() + "!");
        board.setRevealed(Board.PLAYER1, 0);
        board.setRevealed(Board.PLAYER2, 0);
    }

    @Override
    public int changeState() {
        return GameEngine.PICK_MOVES_STATE;
    }
}
