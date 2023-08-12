package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class BadPoisonStatus extends Status {
    public BadPoisonStatus() {
        super("Bad Poison", "PSN", true, 0, 0, Status.Trigger.AFTER_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon inflicted, Pokemon opponent) {
        int poisonDamage;
        poisonDamage = inflicted.getHp() * inflicted.getBadPoisonCounter() / 16;
        inflicted.inflictDamage(poisonDamage);
        inflicted.incrementBadPoisonCounter();
        return HURT_BY_POISON;

    }
}
