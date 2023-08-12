package logic.database.effects;

import logic.database.statuses.SleepStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class RecoverEffect extends Effect {


    public RecoverEffect() {
        super("Recover Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        int currentHp = attacker.getCurrentHp();
        int maxHp = attacker.getHp();

        int hpDifference = maxHp - currentHp;
        int amountToRecover;
        if(move_used.getName().equals("Rest"))
            amountToRecover = maxHp;
        else
            amountToRecover = maxHp / 2;

        if(hpDifference == 0 || hpDifference == 255 || hpDifference == 511)
            return BUT_IT_FAILED;

        if(amountToRecover > hpDifference)
            attacker.setCurrentHp(attacker.getHp());
        else
            attacker.setCurrentHp(attacker.getCurrentHp()+amountToRecover);

        if(move_used.getName().equals("Rest")) {
            // Note that Rest does not reset the bad poison counter, the speed drop from paralysis or the attack burn from burn
            attacker.setStatus(new SleepStatus(true));
            return REST_SUCCESSFUL;
        }
        return RECOVERY_SUCCESSFUL;
    }
}
