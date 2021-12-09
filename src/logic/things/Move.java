package logic.things;

public class Move {
    private String name;
    private int max_pp;
    private Type type;
    private int power;
    private int accuracy;
    private Effect effect;
    private boolean high_crit_rate;

    public boolean isPhysical(){
        switch(type) {
            case BUG:
            case ROCK:
            case GHOST:
            case FLYING:
            case GROUND:
            case NORMAL:
            case POISON:
            case FIGHTING:
                return true;
            default:
                return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getMax_pp() {
        return max_pp;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Effect getEffect() {
        return effect;
    }

    public boolean isHighCritRateMove() {return this.high_crit_rate; }

    public Move(String name, int max_pp, Type type, int power, int accuracy, Effect effect) {
        this.name = name;
        this.max_pp = max_pp;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.effect = effect;
        this.high_crit_rate = false;
    }

    public Move(String name, int max_pp, Type type, int power, int accuracy, Effect effect, boolean high_crit_rate) {
        this.name = name;
        this.max_pp = max_pp;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.effect = effect;
        this.high_crit_rate = high_crit_rate;
    }
}
