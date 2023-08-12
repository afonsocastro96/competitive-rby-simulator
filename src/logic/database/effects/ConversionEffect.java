package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ConversionEffect extends Effect {
    public ConversionEffect() {
        super("Conversion Effect", 100, false);
    }

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        attacker.setType1(defender.getType1());
        attacker.setType2(defender.getType2());
        return CONVERTED_TYPE;
    }
}
