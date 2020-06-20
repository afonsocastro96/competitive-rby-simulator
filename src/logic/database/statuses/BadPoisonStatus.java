package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class BadPoisonStatus extends Status {
    private boolean switchedOut;
    private int poisonCounter;

    public BadPoisonStatus() {
        super("Bad Poison", "PSN", true, 0, 0, Status.Trigger.AFTER_MOVING, 0);
        this.switchedOut = false;
        this.poisonCounter = 1;
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        if(switchedOut) {
            pokemon.inflictDamage(pokemon.getHp() / 16);
            pokemon.setStatus(new PoisonStatus());
            return HURT_BY_POISON;
        }
        else {
            int poisonDamage;
            poisonDamage = pokemon.getHp() * poisonCounter / 16;
            pokemon.inflictDamage(poisonDamage);
            ++poisonCounter;
            return HURT_BY_POISON;
        }
    }
}
