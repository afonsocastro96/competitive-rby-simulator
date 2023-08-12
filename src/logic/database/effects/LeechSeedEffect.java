package logic.database.effects;

import logic.database.statuses.LeechSeedStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class LeechSeedEffect extends Effect {

    public LeechSeedEffect() {
        super("Leech Seed Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        defender.addVolatileStatus(new LeechSeedStatus());
        return WAS_SEEDED;
    }
}
