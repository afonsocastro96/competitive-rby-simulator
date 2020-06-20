package logic.database.statuses;

import logic.Rand;
import logic.things.Pokemon;
import logic.things.Status;

public class ParalysisStatus extends Status {
    public ParalysisStatus() {
        super("Paralysis", "PRZ", true, 0, 0, Status.Trigger.BEFORE_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        if(Rand.itHappened(25))
            return FULLY_PARALYZED;
        return NOTHING;
    }
}
