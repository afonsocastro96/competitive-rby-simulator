package logic.database.effects;

import logic.database.statuses.LightScreenStatus;
import logic.database.statuses.MistStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class MistEffect extends Effect {

    public MistEffect() {
        super("Mist Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addVolatileStatus(new MistStatus());
        return SHROUDED_IN_MIST;
    }
}
