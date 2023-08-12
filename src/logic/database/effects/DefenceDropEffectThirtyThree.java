package logic.database.effects;

import logic.Rand;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class DefenceDropEffectThirtyThree extends Effect {
    public DefenceDropEffectThirtyThree() {
        super("Defence Drop Side Effect 33", 33.33, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt <= 0) // Substitute
            return NOTHING;
        if (Rand.itHappened(this.getProbablility())) {
            defender.addStatModifier(Pokemon.DEFENCE, -1);
            applyStackingEffectGlitch(defender);
            return DEFENCE_DROP;
        }
        return NOTHING;
    }
}
