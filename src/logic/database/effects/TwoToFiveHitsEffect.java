package logic.database.effects;

import logic.Rand;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;
import output.OutputHandler;

public class TwoToFiveHitsEffect extends Effect {
    public TwoToFiveHitsEffect() {
        super("Multi Hit Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        int n = Rand.numberOfMultiHits();
        int moveCount = 1;
        int damage = damage_dealt;
        while(n != moveCount && defender.getCurrentHp() > 0 && damage != 0) { //damage_dealt comes at 0 if it broke a substitute
            // damage_dealt comes negative if the damage was done to a substitute, and it survived
            damage = defender.inflictDamage(Math.abs(damage_dealt));
            if(damage > 0)
                OutputHandler.outputText(defender.getSpecies() + " lost " + (int)((double)damage / defender.getHp() * 100) + "% of its health!");
            else if (damage < 0)
                OutputHandler.outputText("The substitute took damage for " + defender.getSpecies() + "!");
            else
                OutputHandler.outputText(defender.getSpecies() + "'s substitute broke!");
            moveCount++;
        }
        OutputHandler.outputText("The Pokémon was hit " + moveCount + " times!");
        return NOTHING;
    }
}
