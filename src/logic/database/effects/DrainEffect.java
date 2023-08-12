package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class DrainEffect extends Effect {
    public DrainEffect() {
        super("Drain Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt == 0) // Substitute was broken
            return NOTHING;

        attacker.healDamage(damage_dealt/2);
        if(move_used.getName().equals("Dream Eater"))
            return DREAM_EATEN;
        return ENERGY_DRAINED;
    }
}
