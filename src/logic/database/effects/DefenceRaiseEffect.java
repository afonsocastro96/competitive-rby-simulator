package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class DefenceRaiseEffect extends Effect {

    public DefenceRaiseEffect() {
        super("Defense Raise Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addStatModifier(Pokemon.DEFENCE, 1);
        applyStackingEffectGlitch(defender);
        return DEFENCE_RAISED;
    }
}
