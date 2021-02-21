package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

import java.util.concurrent.ThreadLocalRandom;

public class SleepStatus extends Status {

    private int sleep_counter;

    public SleepStatus(boolean isRestSleep) {
        super("Sleep", "SLP", true, 1, 7, Trigger.BEFORE_MOVING, 0);
        if(isRestSleep)
            this.sleep_counter = 2;
        else
            this.sleep_counter = ThreadLocalRandom.current().nextInt(minDuration, maxDuration+1);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        sleep_counter--;
        if(sleep_counter == 0) {
            pokemon.setStatus(null);
            return WOKE_UP;
        }
        return FAST_ASLEEP;
    }
}
