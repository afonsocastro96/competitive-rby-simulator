package logic.database.effects;

import logic.Rand;
import logic.database.statuses.ConfusionStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ConfuseEffectTen extends Effect {

    public ConfuseEffectTen() {
        super("Confusion Drop Side Effect 10", 10, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt <= 0) // Substitute broken
            return NOTHING;

        if (Rand.itHappened(this.getProbablility())) {
            defender.addVolatileStatus(new ConfusionStatus());
            return BECAME_CONFUSED;
        }
        return NOTHING;
    }
}
