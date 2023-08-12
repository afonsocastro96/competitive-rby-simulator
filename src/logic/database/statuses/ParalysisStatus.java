package logic.database.statuses;

import logic.Rand;
import logic.things.Pokemon;
import logic.things.Status;

public class ParalysisStatus extends Status {
    public ParalysisStatus() {
        super("Paralysis", "PRZ", true, 0, 0, Status.Trigger.BEFORE_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        if(Rand.itHappened(25)) {
            inflicted.clearMoveBeingCharged(); // If the pokemon is charging a move, cancel it out
            // Note: Game Freak forgot to remove invulnerable status for paralysis, so if the Pokemon fully paralyses
            // in the middle of invulnerability (Fly/Dig), it will stay that way until it switches or completes Dig/Fly.
            return FULLY_PARALYZED;
        }
        return NOTHING;
    }
}
