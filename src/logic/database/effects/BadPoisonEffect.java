package logic.database.effects;

import logic.database.statuses.BadPoisonStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;
import logic.things.Type;

public class BadPoisonEffect extends Effect {
    public BadPoisonEffect() {
        super("Bad Poison Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(defender.getType1()== Type.POISON || defender.getType2() == Type.POISON)
            return BUT_IT_FAILED;
        if(defender.getSubstituteHp() == 0)
            return BUT_IT_FAILED;
        if(defender.getStatus() == null) {
            defender.setStatus(new BadPoisonStatus());
            return SUCCESSFULLY_BADLY_POISONED;
        }
        return BUT_IT_FAILED;
    }
}
