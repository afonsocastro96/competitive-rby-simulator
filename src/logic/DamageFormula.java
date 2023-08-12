package logic;

import logic.things.Move;
import logic.things.Pokemon;
import logic.things.Type;

public class DamageFormula {
    public static int calcDamage(Pokemon attacker, Pokemon defender, Move move, boolean criticalHit) {

        // Seismic Toss and Night Shade
        if(move.getName().equals("Seismic Toss") || move.getName().equals("Night Shade"))
            return attacker.getLevel();

        if(move.getName().equals("Psywave"))
            return Rand.psywaveDamage(attacker.getLevel());

        // Super Fang
        if(move.getName().equals("Super Fang"))
            return defender.getCurrentHp()==1? 1: defender.getCurrentHp()/2;

        // Dragon Rage and Sonicboom
        if(move.getName().equals("Dragon Rage"))
            return 40;
        if(move.getName().equals("Sonicboom"))
            return 20;

        // One hit KO moves
        if(move.getName().equals("Horn Drill") || move.getName().equals("Guillotine")) {
            if(defender.getType1() == Type.GHOST || defender.getType2() == Type.GHOST)
                return 0;
            else return 65535;
        }
        if(move.getName().equals("Fissure")) {
            if(defender.getType1() == Type.FLYING || defender.getType2() == Type.FLYING)
                return 0;
            else return 65535;
        }

        int attackerLevel = criticalHit? attacker.getLevel() * 2 : attacker.getLevel();
        int attackerAttack;
        int attackPower = move.getPower();
        int defenderDefence;

        if(move.isPhysical()){
            if(criticalHit) {
                defenderDefence = defender.getDefence();
                attackerAttack = attacker.getAttack();
            }
            else {
                attackerAttack = attacker.getStatWithModifiers(Pokemon.ATTACK);
                defenderDefence = defender.getStatWithModifiers(Pokemon.DEFENCE);
                if (defender.hasVolatileStatus("Reflect"))
                    defenderDefence *= 2;
            }
        }
        else {
            if(criticalHit) {
                defenderDefence = defender.getSpecial();
                attackerAttack = attacker.getSpecial();
            }
            else {
                attackerAttack = attacker.getStatWithModifiers(Pokemon.SPECIAL);
                defenderDefence = defender.getStatWithModifiers(Pokemon.SPECIAL);
                if (defender.hasVolatileStatus("Light Screen"))
                    defenderDefence *= 2;
            }
        }

        double stab = attacker.getType1() == move.getType() || attacker.getType2() == move.getType() ? 1.5 : 1;
        //If it has 2 types, take into consideration both types. Otherwise, just the single type.
        double typeModifier;
        if(move.getType() == Type.TYPELESS)
            typeModifier = 1;
        else
            typeModifier = (defender.getType1() == defender.getType2() ?
                    TypeMatchups.getMatchup(move.getType(), defender.getType1()) :
                    TypeMatchups.getMatchup(move.getType(), defender.getType1()) * TypeMatchups.getMatchup(move.getType(), defender.getType2()));

        int random_modifier = move.getName().equals("Confusion Damage")? 255 : Rand.getDamageFormulaRandomModifier();

        int part1 = 2 * attackerLevel / 5 + 2;
        int part2 = part1 * attackerAttack * attackPower;
        int part3 = part2 / defenderDefence;
        int part4 = part3 / 50;
        int part5 = part4 + 2;
        int part6 = (int)(part5 * stab); // STAB
        int part7 = (int)(part6 * typeModifier); //Type modifier
        int part8 = part7 * random_modifier/255; // Random modifier

        return part8 == 0? 1 : part8;
    }
}
