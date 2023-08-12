package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class PoisonStatus extends Status {
    public PoisonStatus() {
        super("Poison", "PSN", true, 0, 0, Status.Trigger.AFTER_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        // The bad poison counter also applies to poison, but does not get incremented.
        // Normally not relevant because the most common way of getting rid of bad poison is switching, which resets
        // the bad poison counter back to 1, and normal poison does not increment it.
        // Only relevant when badly poisoned rests and then gets poisoned again.
        int damageToInflict = inflicted.getHp()*inflicted.getBadPoisonCounter() / 16 == 0? 1 : inflicted.getHp()*inflicted.getBadPoisonCounter()/16;
        inflicted.inflictDamage(damageToInflict);
        return HURT_BY_POISON;
    }
}
