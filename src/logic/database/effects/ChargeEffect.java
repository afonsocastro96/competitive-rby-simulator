package logic.database.effects;

import logic.things.Effect;
import logic.things.Move;
import logic.things.Pokemon;

public class ChargeEffect extends Effect {
    String chargingMessage;
    boolean appliesInvulnerability;

    public ChargeEffect(String message, boolean appliesInvulnerability) {
        super("Charge Effect", 100, false);
        this.chargingMessage = message;
        this.appliesInvulnerability = appliesInvulnerability;
    }

    public String getChargingMessage() {return chargingMessage;}

    public boolean appliesInvulnerability() {return appliesInvulnerability;}

    @Override
    public int resolveEffect(Pokemon attacker, Pokemon defender, Move move_used, int damage_dealt) {
        return NOTHING;
    }
}
