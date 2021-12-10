package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class HighCritRateEffect extends Effect {
    public HighCritRateEffect() {
        super("High Critical Hit Rate Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        return NOTHING;
    }
}
