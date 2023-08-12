package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class EvasionRaiseEffect extends Effect {
    public EvasionRaiseEffect() {
        super("Evasion Raise Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addStatModifier(Pokemon.EVASION, 1);
        applyStackingEffectGlitch(defender);
        return EVASION_RAISED;
    }
}
