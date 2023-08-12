package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class BurnStatus extends Status {
    public BurnStatus() {
        super("Burn", "BRN", true, 0, 0, Status.Trigger.AFTER_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        // The bad poison counter also applies to burn, but does not get incremented
        // Only relevant when badly poisoned rests and then gets burned
        int damageToInflict = inflicted.getHp()*inflicted.getBadPoisonCounter() / 16 == 0? 1 : inflicted.getHp()*inflicted.getBadPoisonCounter()/16;
        inflicted.inflictDamage(damageToInflict);
        return HURT_BY_BURN;
    }
}
