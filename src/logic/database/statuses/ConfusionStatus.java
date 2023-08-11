package logic.database.statuses;

import logic.DamageFormula;
import logic.Rand;
import logic.database.Moves;
import logic.things.Pokemon;
import logic.things.Status;
import output.OutputHandler;

import java.util.concurrent.ThreadLocalRandom;

public class ConfusionStatus extends Status {

    private int confusionCounter;

    public ConfusionStatus() {
        super("Confusion", "", false, 1, 4, Status.Trigger.BEFORE_MOVING, 3);
        this.confusionCounter = ThreadLocalRandom.current().nextInt(minDuration, maxDuration+1);
    }

    @Override
    public int resolveStatus(Pokemon pokemon) {
        --confusionCounter;
        if(this.confusionCounter == 0)
            return SNAPPED_OUT_CONFUSION;
        else {
            OutputHandler.outputText(pokemon.getSpecies() + " is confused!");
            if(Rand.itHappened(50)) {
                if(pokemon.getSubstituteHp() == 0)
                    pokemon.inflictDamage(DamageFormula.calcDamage(pokemon, pokemon, Moves.getMove("Confusion Damage"), false));
                OutputHandler.outputText("(Note: if the confused Pokemon has a substitute, it does not take damage from confusion, despite the message showing up anyway.)");
                return HURT_ITSELF_CONFUSION;
            }
            else return NOTHING;
        }
    }
}
