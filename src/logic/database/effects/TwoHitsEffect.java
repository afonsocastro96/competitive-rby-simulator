package logic.database.effects;

import logic.Rand;
import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;
import output.OutputHandler;

public class TwoHitsEffect extends Effect {
    public TwoHitsEffect() {
        super("Two Hits Effect", 100, true);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        if(damage_dealt == 0 || defender.getCurrentHp() == 0) { // Substitute was broken or opponent fainted
            OutputHandler.outputText("The Pokémon was hit 1 time!");
            return NOTHING;
        } else {
            // damage_dealt comes negative if the damage was done to a substitute, and it survived
            int damage = defender.inflictDamage(Math.abs(damage_dealt));
            if(damage > 0)
                OutputHandler.outputText(defender.getSpecies() + " lost " + (int)((double)damage / defender.getHp() * 100) + "% of its health!");
            else if (damage < 0)
                OutputHandler.outputText("The substitute took damage for " + defender.getSpecies() + "!");
            else
                OutputHandler.outputText(defender.getSpecies() + "'s substitute broke!");

            OutputHandler.outputText("The Pokémon was hit 2 times!");
        }

        return NOTHING;
    }
}