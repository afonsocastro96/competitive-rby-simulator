package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class FreezeStatus extends Status {
    public FreezeStatus() {
        super("Freeze", "FRZ", true, 0, 0, Status.Trigger.BEFORE_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        return FROZEN_SOLID;
    }
}
