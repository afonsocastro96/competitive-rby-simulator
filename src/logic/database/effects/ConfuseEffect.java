package logic.database.effects;

import logic.database.statuses.ConfusionStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ConfuseEffect extends Effect {
    public ConfuseEffect() {
        super("Confuse Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(defender.getSubstituteHp() > 0)
            return BUT_IT_FAILED;

        defender.addVolatileStatus(new ConfusionStatus());
        return BECAME_CONFUSED;
    }
}
