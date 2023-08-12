package engine.states;

import engine.Board;
import engine.GameEngine;
import engine.Player;
import engine.State;
import output.OutputHandler;

public class StartState extends State {
    @Override
    public void action(Board board) {
        OutputHandler.outputText("Battle started between players 1 and 2!");
        OutputHandler.outputText("");
        OutputHandler.outputText("Go! " + board.getPlayerActivePokemon(Player.PLAYER1).getSpecies() + "!");
        OutputHandler.outputText("Player 2 sent out " + board.getPlayerActivePokemon(Player.PLAYER2).getSpecies() + "!");
        board.setRevealed(Player.PLAYER1, 0);
        board.setRevealed(Player.PLAYER2, 0);
    }

    @Override
    public int changeState() {
        return GameEngine.PICK_MOVES_STATE;
    }
}
