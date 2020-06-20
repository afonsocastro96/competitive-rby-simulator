package logic.database.statuses;

import logic.DamageFormula;
import logic.Rand;
import logic.database.Moves;
import logic.things.Pokemon;
import logic.things.Status;

public class ConfusionStatus extends Status {

    private int confusionCounter;

    public ConfusionStatus(int confusionCounter) {
        super("Confusion", "", false, 1, 4, Status.Trigger.BEFORE_MOVING, 3);
        this.confusionCounter = confusionCounter;
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        --confusionCounter;
        if(this.confusionCounter == 0)
            return SNAPPED_OUT_CONFUSION;
        else if(Rand.itHappened(50)) {
            pokemon.inflictDamage(DamageFormula.calcDamage(pokemon, pokemon, Moves.getMove("Confusion Damage"), false));
            return HURT_ITSELF_CONFUSION;
        }
        else return NOTHING;
    }
}
