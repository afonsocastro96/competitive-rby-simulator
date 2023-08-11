package logic.database.effects;

import logic.Rand;
import logic.database.statuses.FlinchStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class FlinchEffectTen extends Effect {
    public FlinchEffectTen() {
        super("Flinch Side Effect 10", 10, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt > 0 /*Substitute*/ && Rand.itHappened(this.getProbablility()))
            defender.setStatus(new FlinchStatus());
        return NOTHING;
    }
}
