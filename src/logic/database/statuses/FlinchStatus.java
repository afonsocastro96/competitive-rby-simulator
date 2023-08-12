package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class FlinchStatus extends Status {
    public FlinchStatus() {
        super("Flinch", "", false, 1, 1, Status.Trigger.BEFORE_MOVING, 2);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        inflicted.removeVolatileStatus("Flinch");
        inflicted.removeVolatileStatus("Recharging");
        return FLINCH;
    }
}
