package logic.things;

public abstract class Status{

    public static final int NOTHING = 0;
    public static final int FULLY_PARALYZED = 1;
    public static final int FROZEN_SOLID = 2;
    public static final int RECHARGE = 3;
    public static final int HURT_ITSELF_CONFUSION = 4;
    public static final int SNAPPED_OUT_CONFUSION = 5;
    public static final int FLINCH = 6;
    public static final int FAST_ASLEEP = 7;
    public static final int WOKE_UP = 8;
    public static final int HURT_BY_POISON = 9;
    public static final int HURT_BY_BURN = 10;

    public enum Trigger {BEFORE_MOVING, AFTER_MOVING}

    private String name;
    private String abrv;
    private boolean permanent;
    protected int minDuration;
    protected int maxDuration;
    private Trigger trigger;
    private int priority;

    public Status(String name, String abrv, boolean permanent, int minDuration, int maxDuration, Trigger trigger, int priority) {
        this.name = name;
        this.abrv = abrv;
        this.permanent = permanent;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.trigger = trigger;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public abstract int resolveStatus(Pokemon pokemon);

    public Trigger getTrigger() {
        return trigger;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Status && this.name.equals(((Status) obj).getName());

    }


}
