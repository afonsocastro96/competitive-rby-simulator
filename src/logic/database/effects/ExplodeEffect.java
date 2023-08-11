package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ExplodeEffect extends Effect {
    public ExplodeEffect() {
        super("Explode Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt != 0) { // A substitute was broken
            attacker.setCurrentHp(0);
        }
        return NOTHING;
    }
}
