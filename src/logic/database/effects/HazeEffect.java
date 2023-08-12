package logic.database.effects;

import logic.database.statuses.FreezeStatus;
import logic.database.statuses.PoisonStatus;
import logic.database.statuses.SleepStatus;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class HazeEffect extends Effect {
    public HazeEffect() {
        super("Haze Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.setParalysisSpeedDropCounter(0);
        attacker.setBurnAttackDropCounter(0);

        defender.setParalysisSpeedDropCounter(0);
        defender.setBurnAttackDropCounter(0);

        attacker.setStatModifier(Pokemon.ATTACK, 0);
        attacker.setStatModifier(Pokemon.DEFENCE, 0);
        attacker.setStatModifier(Pokemon.SPECIAL, 0);
        attacker.setStatModifier(Pokemon.SPEED, 0);

        defender.setStatModifier(Pokemon.ATTACK, 0);
        defender.setStatModifier(Pokemon.DEFENCE, 0);
        defender.setStatModifier(Pokemon.SPECIAL, 0);
        defender.setStatModifier(Pokemon.SPEED, 0);

        if(attacker.getStatus() != null)
            if(attacker.getStatus().getName().equals("Bad Poison")) {
                attacker.setStatus(new PoisonStatus());
                attacker.setBadPoisonCounter(1);
            }

        if(defender.getStatus() != null) {  // Haze only removes the status of the opponent, doesn't remove the user's
            if (defender.getStatus().getName().equals("Bad Poison")) {
                defender.setStatus(new PoisonStatus());
                defender.setBadPoisonCounter(1);
            }
            // Sleep and freeze are implemented this way because the opponent is not supposed to move this turn even if their status gets cleared
            else if (defender.getStatus().getName().equals("Sleep")) {
                SleepStatus ss = (SleepStatus) defender.getStatus();
                ss.setSleepCounter(0);
            }
            else if (defender.getStatus().getName().equals("Freeze")) {
                FreezeStatus fs = (FreezeStatus) defender.getStatus();
                fs.thawPokemon();
            }
            else
                defender.setStatus(null);
        }


        attacker.removeVolatileStatus("Focus Energy Status");
        attacker.removeVolatileStatus("Reflect Status");
        attacker.removeVolatileStatus("Light Screen Status");
        attacker.removeVolatileStatus("Leech Seed Status");
        attacker.removeVolatileStatus("Confusion Status");
        //TODO: Add Disable here when it's implemented

        defender.removeVolatileStatus("Focus Energy Status");
        defender.removeVolatileStatus("Reflect Status");
        defender.removeVolatileStatus("Light Screen Status");
        defender.removeVolatileStatus("Leech Seed Status");
        defender.removeVolatileStatus("Confusion Status");
        //TODO: Add Disable here when it's implemented

        return STATUS_CHANGES_ELIMINATED;
    }
}
