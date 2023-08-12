package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class SpecialRaiseEffect extends Effect {
    public SpecialRaiseEffect() {
        super("Special Sharp Raise Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addStatModifier(Pokemon.SPECIAL, 1);
        applyStackingEffectGlitch(defender);
        return SPECIAL_SHARPLY_RAISED;
    }
}
