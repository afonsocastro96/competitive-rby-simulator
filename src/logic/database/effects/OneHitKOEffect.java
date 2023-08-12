package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class OneHitKOEffect extends Effect {

    public OneHitKOEffect() {
        super("One Hit KO effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        return ONE_HIT_KO;
    }
}
