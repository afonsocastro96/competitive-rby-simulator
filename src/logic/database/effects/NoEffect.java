package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class NoEffect extends Effect {
    public NoEffect() {
        super("No Effect", 0, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        return NOTHING;
    }
}
