package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class FreezeStatus extends Status {
    public FreezeStatus() {
        super("Freeze", "FRZ", true, 0, 0, Status.Trigger.BEFORE_MOVING, 0);
    }

    private boolean thaw = false;

    public void thawPokemon(){this.thaw = true;}

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        if(thaw) {
            inflicted.setStatus(null);
            return THAWED;
        }
        return FROZEN_SOLID;
    }
}
