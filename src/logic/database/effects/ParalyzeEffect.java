package logic.database.effects;

import logic.database.statuses.ParalysisStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ParalyzeEffect extends Effect {

    public ParalyzeEffect() {
        super("Paralyze Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(defender.getStatus() == null) {
            defender.setStatus(new ParalysisStatus());
            defender.setParalysisSpeedDropCounter(1);
            return SUCCESFULLY_PARALYZED;
        }
        else if(defender.getStatus().getName().equals("Paralysis"))
            return ALREADY_PARALYZED;
        else
            return BUT_IT_FAILED;

    }
}
