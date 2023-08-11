package logic.database.effects;

import logic.Rand;
import logic.database.statuses.ParalysisStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ParalyzeEffectTen extends Effect {

    public ParalyzeEffectTen() {
        super("Paralyze Side Effect 10", 10, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt <= 0) // Substitute
            return NOTHING;

        if (move_used.getType() != defender.getType1() && move_used.getType() != defender.getType2()) {
            if (Rand.itHappened(this.getProbablility())) {
                if (defender.getStatus() == null) {
                    defender.setStatus(new ParalysisStatus());
                    defender.setParalysisSpeedDropCounter(1);
                    return SUCCESFULLY_PARALYZED;
                }
            }
        }
        return NOTHING;
    }
}
