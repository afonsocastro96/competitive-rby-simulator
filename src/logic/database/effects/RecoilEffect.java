package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class RecoilEffect extends Effect {
    public RecoilEffect() {
        super("Struggle Recoil Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt == 0) //Substitute was broken or against a Ghost
            return NOTHING;
        int recoil;
        if(move_used.getName().equals("Struggle"))
            recoil = damage_dealt / 2;
        else
            recoil = damage_dealt / 4;
        recoil = (recoil == 0 ? 1 : recoil);
        attacker.inflictDamage(recoil);
        return RECOIL_SUCCESSFUL;
    }
}
