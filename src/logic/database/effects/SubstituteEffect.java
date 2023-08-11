package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class SubstituteEffect extends Effect {

    public SubstituteEffect() {
        super("Substitute Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        int substituteHp = attacker.getHp()/4+1;
        // In later gens this would be <=, but in gen 1, if a substitute leaves the defender with 0HP, it
        // successfully creates the substitute and then faints
        if(attacker.getCurrentHp() < substituteHp - 1)
            return BUT_IT_FAILED;
        attacker.inflictDamage(substituteHp-1);
        attacker.setSubstituteHp(substituteHp);
        return PUT_UP_SUBSTITUTE;
    }
}
