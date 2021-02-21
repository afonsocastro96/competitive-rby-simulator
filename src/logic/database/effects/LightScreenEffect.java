package logic.database.effects;

import logic.database.statuses.LightScreenStatus;
import logic.database.statuses.ReflectStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class LightScreenEffect extends Effect {
    public LightScreenEffect() {
        super("Light Screen Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addVolatileStatus(new LightScreenStatus());
        return LIGHT_SCREEN_PROTECTED;
    }
}
