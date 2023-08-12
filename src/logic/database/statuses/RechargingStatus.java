package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class RechargingStatus extends Status {

    public RechargingStatus() {
        super("Recharging", "", false, 1, 1, Status.Trigger.BEFORE_MOVING, 1);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        inflicted.removeVolatileStatus("Recharging");
        inflicted.removeVolatileStatus("Flinch");
        return RECHARGE;
    }
}
