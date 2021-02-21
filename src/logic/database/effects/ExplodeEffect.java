package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ExplodeEffect extends Effect {
    public ExplodeEffect() {
        super("Explode Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        // TODO: Add Substitute/Explosion interaction when Substitute is coded
        attacker.setCurrentHp(0);
        return NOTHING;
    }
}
