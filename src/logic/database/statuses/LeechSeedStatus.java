package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class LeechSeedStatus extends Status {
    public LeechSeedStatus() {
        super("Leech Seed", "", false, 0, 0, Status.Trigger.AFTER_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        // In RBY, the bad poison counter is also used and incremented in Leech Seed
        int damageToInflict = inflicted.getHp()*inflicted.getBadPoisonCounter() / 16 == 0? 1 : inflicted.getHp()*inflicted.getBadPoisonCounter()/16;
        inflicted.inflictDamage(damageToInflict);
        opponent.healDamage(damageToInflict);
        inflicted.incrementBadPoisonCounter();
        return ENERGY_DRAINED;
    }
}
