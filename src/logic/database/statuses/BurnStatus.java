package logic.database.statuses;

import logic.things.Pokemon;
import logic.things.Status;

public class BurnStatus extends Status {
    public BurnStatus() {
        super("Burn", "BRN", true, 0, 0, Status.Trigger.AFTER_MOVING, 0);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        pokemon.inflictDamage(pokemon.getHp() / 16);
        return HURT_BY_BURN;
    }
}
