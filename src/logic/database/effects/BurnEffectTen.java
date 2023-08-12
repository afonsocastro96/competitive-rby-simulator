package logic.database.effects;

import logic.Rand;
import logic.database.statuses.BurnStatus;
import logic.database.statuses.FreezeStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class BurnEffectTen extends Effect {
    public BurnEffectTen() {
        super("Burn Side Effect 10", 10, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if (defender.getStatus().getName().equals("Freeze")) {
            FreezeStatus fs = (FreezeStatus) defender.getStatus();
            fs.thawPokemon();
        }

        if(damage_dealt <= 0) // Substitute
            return NOTHING;

        // If the type of the move is the same as the type of the defender, the secondary effect is not applied
        if (move_used.getType() != defender.getType1() && move_used.getType() != defender.getType2()) {
            if (Rand.itHappened(this.getProbablility())) {
                if (defender.getStatus() == null) {
                    defender.setStatus(new BurnStatus());
                    defender.setBurnAttackDropCounter(1);
                    return SUCCESSFULLY_BURNED;
                }
            }
        }
        return NOTHING;
    }
}
