package logic.database.effects;

import logic.Rand;
import logic.database.statuses.FreezeStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class FreezeEffectTen extends Effect {

    public FreezeEffectTen() {
        super("Freeze Side Effect Ten", 10, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if (move_used.getType() != defender.getType1() && move_used.getType() != defender.getType2()) {
            if (Rand.itHappened(this.getProbablility())) {
                if (defender.getStatus() == null) {
                    defender.setStatus(new FreezeStatus());
                    return SUCCESFULLY_FROZEN;
                }
            }
        }
        return NOTHING;
    }
}