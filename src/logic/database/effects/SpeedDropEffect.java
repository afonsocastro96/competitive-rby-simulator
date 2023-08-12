package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class SpeedDropEffect extends Effect {

    public SpeedDropEffect() {
        super("Speed Drop Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(defender.hasVolatileStatus("Mist"))
            return BUT_IT_FAILED;
        else {
            defender.addStatModifier(Pokemon.SPEED, -1);
            applyStackingEffectGlitch(defender);
            return SPEED_DROPPED;
        }
    }
}
