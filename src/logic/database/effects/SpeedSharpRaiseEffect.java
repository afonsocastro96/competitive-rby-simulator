package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class SpeedSharpRaiseEffect extends Effect {
    public SpeedSharpRaiseEffect() {
        super("Speed Sharp Raise Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addStatModifier(Pokemon.SPEED, 2);
        if(attacker.getStatus().getName().equals("Paralysis"))
            attacker.setParalysisSpeedDropCounter(0);
        applyStackingEffectGlitch(defender);
        return SPEED_SHARPLY_RAISED;
    }
}
