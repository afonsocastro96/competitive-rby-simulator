package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class LeechSeedStatus extends Status {
    public LeechSeedStatus() {
        super("Leech Seed", "", false, 0, 0, Status.Trigger.AFTER_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        return 0;
    }
}
