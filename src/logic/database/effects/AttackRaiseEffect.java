package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class AttackRaiseEffect extends Effect {
    public AttackRaiseEffect() {
        super("Attack Sharp Raise Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.addStatModifier(Pokemon.ATTACK, 1);
        if(attacker.getStatus() != null)
            if(attacker.getStatus().getName().equals("Burn"))
                attacker.setBurnAttackDropCounter(0);
        applyStackingEffectGlitch(defender);
        return ATTACK_RAISED;
    }
}
