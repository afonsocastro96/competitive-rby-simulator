package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class LightScreenStatus extends Status {
    public LightScreenStatus() {
        super("Light Screen", "", false, 0, 0, null, 0);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        return NOTHING;
    }
}
