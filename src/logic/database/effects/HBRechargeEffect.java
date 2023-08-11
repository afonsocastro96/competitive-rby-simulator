package logic.database.effects;

import logic.database.statuses.RechargingStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class HBRechargeEffect extends Effect {
    public HBRechargeEffect() {
        super("Hyper Beam Recharge Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt != 0) // A substitute was broken
            attacker.addVolatileStatus(new RechargingStatus());
        return NOTHING;
    }
}
