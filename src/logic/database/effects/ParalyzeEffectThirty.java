package logic.database.effects;

import logic.Rand;
import logic.database.statuses.ParalysisStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ParalyzeEffectThirty extends Effect {

    public ParalyzeEffectThirty() {
        super("Paralyze Side Effect 30", 30, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt <= 0) // Substitute
            return NOTHING;

        // If the type of the move is the same as the type of the defender, the secondary effect is not applied
        if (move_used.getType() != defender.getType1() && move_used.getType() != defender.getType2()) {
            if (Rand.itHappened(this.getProbablility())) {
                if (defender.getStatus() == null) {
                    defender.setStatus(new ParalysisStatus());
                    defender.setParalysisSpeedDropCounter(1);
                    return SUCCESSFULLY_PARALYSED;
                }
            }
        }
        return NOTHING;
    }
}