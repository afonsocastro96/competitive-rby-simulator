package engine;

public abstract class State {
    public abstract void action(Board board);
    public abstract int changeState();
}
