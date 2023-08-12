package logic;

import engine.Board;
import engine.Player;
import logic.database.Pokemons;
import logic.things.Move;
import logic.things.Pokemon;

import java.util.Random;

public class Rand {
    public static int crits = 0;
    public static int nonCrits = 0;
    private static Random r = new Random();
    private static int generateRandom() {
        return r.nextInt(256);
    }
    private static int generateRandom(int bound) {
        return r.nextInt(bound);
    }

    public static boolean itHappened(double probability) {
        int value = (int)(255 * probability / 100);
        if(value > 255)
            value = 255;
        // For this to be mathematically correct, it was supposed to be <=. However, every
        // probability calc is wrong in RBY, having 1/256 less chances of happening.
        return generateRandom() < value;
    }

    // Generates a random number between 217 and 255 for the damage formula.
    public static int getDamageFormulaRandomModifier() {
        return r.nextInt(39)+217;
    }

    // Randomize player to move first when a speed tie happens
    public static Player resolveSpeedTie() {
        switch(r.nextInt(2)) {
            case 0:
                return Player.PLAYER1;
            default:
                return Player.PLAYER2;
        }
    }

    public static boolean criticalHit(Move move, Pokemon attacker) {
        int speed = Pokemons.getPokemon(attacker.getSpecies()).getBase_speed();
        int t = speed/2;
        if(move.getEffect().getName().equals("High Critical Hit Rate Effect"))
            t *= 8;
        if(attacker.hasVolatileStatus("Focus Energy Effect"))
            t /= 4; // Focus Energy is glitched in RBY - quarters critical hit rate instead of quadrupling it like intended
        if(t > 255)
            t = 255;
        return generateRandom() < t;
    }

    public static boolean attackMissed(int accuracy, Pokemon attacker, Pokemon defender) {
        if(accuracy == -1) // Bypasses accuracy check (Recovery moves, setup moves, Swift and Bide, etc)
            return false;
        if(defender.isInvulnerable()) // If opponent is mid Fly/Dig and move does not bypass accuracy, it misses
            return true;
        int accuracyAfterModifiers = accuracy *
                Pokemon.convertModifierToFraction(attacker.getAccuracyModifier()) *
                Pokemon.convertModifierToFraction(-defender.getEvasionModifier());

        return !itHappened(accuracyAfterModifiers);
    }

    public static int psywaveDamage(int attackerLevel) {
        int bound = (int)Math.ceil(attackerLevel*1.5);
        return generateRandom(bound);
    }

    // 3/8 2 hits, 3/8 3 hits, 1/8 4 hits, 1/8 5 hits
    public static int numberOfMultiHits() {
        int r = generateRandom();
        if(r<256*3/8)
            return 2;
        else if(r<256*6/8)
            return 3;
        else if(r<256*7/8)
            return 4;
        else
            return 5;
    }

}
