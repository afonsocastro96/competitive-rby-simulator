package engine.states;

import engine.Board;
import engine.GameEngine;
import engine.State;

public class PickMovesState extends State {
    @Override
    public void action(Board board) {

    }

    @Override
    public int changeState() {
        return GameEngine.END;
    }
}
