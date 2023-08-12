package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class DefenceSharpDropEffect extends Effect {

    public DefenceSharpDropEffect() {
        super("Defense Sharp Drop Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(defender.hasVolatileStatus("Mist"))
            return BUT_IT_FAILED;
        else {
            defender.addStatModifier(Pokemon.DEFENCE, -2);
            applyStackingEffectGlitch(defender);
            return DEFENCE_SHARPLY_DROPPED;
        }
    }
}
