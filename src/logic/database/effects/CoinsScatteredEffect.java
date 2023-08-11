package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class CoinsScatteredEffect extends Effect {
    public CoinsScatteredEffect() {
        super("Coins Scattered Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt == 0) // Substitute was broken, no coins scattered
            return NOTHING;
        return COINS_SCATTERED;
    }
}
