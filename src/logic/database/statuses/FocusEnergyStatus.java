package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class FocusEnergyStatus extends Status {
    public FocusEnergyStatus() {
        super("Focus Energy", "", false, 0, 0, null, 0);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        return NOTHING;
    }
}
