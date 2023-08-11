package logic.database.effects;

import logic.Rand;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class DefenceDropEffect extends Effect {

    public DefenceDropEffect() {
        super("Defense Drop Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        defender.addStatModifier(Pokemon.DEFENCE, -1);
        applyStackingEffectGlitch(defender);
        return DEFENCE_DROP;

    }
}
