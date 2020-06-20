package logic.database.effects;

import logic.Rand;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class SpecialDropEffectThirty extends Effect {
    public SpecialDropEffectThirty() {
        super("Special Drop Side Effect 30", 30, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if (Rand.itHappened(this.getProbablility())) {
            defender.addStatModifier(Pokemon.SPECIAL, -1);
            applyStackingEffectGlitch(defender);
            return SPECIAL_DROP;
        }
        return NOTHING;
    }
}
