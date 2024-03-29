package logic.things;

public abstract class Effect {

    public static final int NOTHING = 0;
    public static final int ALREADY_PARALYZED = 1;
    public static final int SUCCESSFULLY_PARALYSED = 2;
    public static final int BUT_IT_FAILED = 3;
    public static final int SUCCESSFULLY_FROZEN = 4;
    public static final int SUCCESSFULLY_BADLY_POISONED = 5;
    public static final int RECOVERY_SUCCESSFUL = 6;
    public static final int SPEED_SHARPLY_RAISED = 7;
    public static final int RECOIL_SUCCESSFUL = 8;
    public static final int SUCCESSFULLY_PUT_TO_SLEEP = 9;
    public static final int SUCCESSFULLY_BURNED = 10;
    public static final int SPECIAL_DROP = 11;
    public static final int SPECIAL_SHARPLY_RAISED = 12;
    public static final int SPEED_DROPPED = 13;
    public static final int ENERGY_DRAINED = 14;
    public static final int LIGHT_SCREEN_PROTECTED = 15;
    public static final int REFLECT_GAINED_ARMOUR = 16;
    public static final int REST_SUCCESSFUL = 17;
    public static final int ATTACK_SHARPLY_RAISED = 18;
    public static final int COINS_SCATTERED = 19;
    public static final int ATTACK_RAISED = 20;
    public static final int BECAME_CONFUSED = 21;
    public static final int PUT_UP_SUBSTITUTE = 22;
    public static final int DEFENCE_DROP = 23;
    public static final int SHROUDED_IN_MIST = 24;
    public static final int DEFENCE_SHARPLY_RAISED = 25;
    public static final int DEFENCE_RAISED = 26;
    public static final int ATTACK_DROPPED = 27;
    public static final int DEFENCE_SHARPLY_DROPPED = 28;
    public static final int SUCCESSFULLY_POISONED = 29;
    public static final int GETTING_PUMPED = 30;
    public static final int STATUS_CHANGES_ELIMINATED = 31;
    public static final int CONVERTED_TYPE = 32;
    public static final int DREAM_EATEN = 33;
    public static final int BUT_NOTHING_HAPPENED = 34;
    public static final int ONE_HIT_KO = 35;
    public static final int WAS_SEEDED = 36;
    public static final int ACCURACY_DROPPED = 37;
    public static final int EVASION_RAISED = 38;

    private String name;
    private boolean effectAppliedAfterOpponentFainted;

    public String getName() {
        return name;
    }

    public double getProbablility() {
        return probablility;
    }

    private double probablility;

    public Effect(String name, double probability, boolean effectAppliedAfterOpponentFainted) {
        this.name = name;
        this.probablility = probability;
        this.effectAppliedAfterOpponentFainted = effectAppliedAfterOpponentFainted;
    }

    public boolean isEffectAppliedAfterOpponentFainted() {
        return effectAppliedAfterOpponentFainted;
    }

    public abstract int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt);

    protected void applyStackingEffectGlitch(Pokemon defender) {
        if(defender.getStatus() == null)
            return;
        if(defender.getStatus().getName().equals("Paralysis"))
            defender.addParalysisSpeedDropCounter(1);
        else if(defender.getStatus().getName().equals("Burn"))
            defender.addBurnAttackDropCounter(1);
    }
}
