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

    public static int frozen = 0;
    public static int notFrozen = 0;


    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt <= 0) // Substitute
            return NOTHING;

        if (move_used.getType() != defender.getType1() && move_used.getType() != defender.getType2()) {
            if (Rand.itHappened(this.getProbablility())) {
                if (defender.getStatus() == null) {
                    defender.setStatus(new FreezeStatus());
                    frozen++;
                    return SUCCESSFULLY_FROZEN;
                }
                else frozen++;
            }
            else notFrozen++;
        }
        return NOTHING;
    }
}