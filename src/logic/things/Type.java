package logic.things;

public enum Type {
    NORMAL(0), FIGHTING(1), FLYING(2), POISON(3), GROUND(4), ROCK(5), BUG(6), GHOST(7), FIRE(8), WATER(9), GRASS(10), ELECTRIC(11), PSYCHIC(12), ICE(13), DRAGON(14), TYPELESS(15);

    private final int id;

    Type(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
