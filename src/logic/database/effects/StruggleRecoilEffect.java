package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class StruggleRecoilEffect extends Effect {
    public StruggleRecoilEffect() {
        super("Struggle Recoil Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        int recoil = damage_dealt / 2;
        recoil = (recoil == 0 ? 1 : recoil);
        if(attacker.getCurrentHp() > recoil)
            attacker.setCurrentHp(attacker.getCurrentHp() - recoil);
        else
            attacker.setCurrentHp(0);
        return RECOIL_SUCCESSFUL;
    }
}
