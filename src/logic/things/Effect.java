package logic.things;

public abstract class Effect {

    public static final int NOTHING = 0;
    public static final int ALREADY_PARALYZED = 1;
    public static final int SUCCESFULLY_PARALYZED = 2;
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
    public static final int SPEED_DROP = 13;
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
