package logic.database.effects;

import logic.database.statuses.FocusEnergyStatus;
import logic.database.statuses.MistStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class FocusEnergyEffect extends Effect {

    public FocusEnergyEffect() {
        super("Focus Energy Status Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addVolatileStatus(new FocusEnergyStatus());
        return GETTING_PUMPED;
    }
}
