package logic.database.effects;

import logic.database.statuses.SleepStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class SleepEffect extends Effect {
    public SleepEffect() {
        super("Sleep Effect", 100, false);
    }

    public static final int MIN_SLEEP_TURNS = 1;
    public static final int MAX_SLEEP_TURNS = 7;

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(defender.getStatus() == null) {
            defender.setStatus(new SleepStatus(false));
            return SUCCESSFULLY_PUT_TO_SLEEP;
        }
        else
            return BUT_IT_FAILED;

    }
}
