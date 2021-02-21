package logic.database.effects;

import logic.database.statuses.ReflectStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ReflectEffect extends Effect {
    public ReflectEffect() {
        super("Reflect Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addVolatileStatus(new ReflectStatus());
        return REFLECT_GAINED_ARMOUR;
    }
}
