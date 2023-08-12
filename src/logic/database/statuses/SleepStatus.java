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

    public void setSleepCounter(int sleepCounter) {this.sleep_counter = sleepCounter;}

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        sleep_counter--;
        if(sleep_counter == 0) {
            inflicted.setStatus(null);
            return WOKE_UP;
        }
        return FAST_ASLEEP;
    }
}
