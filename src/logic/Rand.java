package logic;

import engine.Board;

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
    public static int resolveSpeedTie() {
        switch(r.nextInt(2)) {
            case 0:
                return Board.PLAYER1;
            default:
                return Board.PLAYER2;
        }
    }

    public static boolean criticalHit(int speed) {
        int t = speed/2;
        if(t > 255)
            t = 255;
        return generateRandom() < t;
    }

    public static boolean attackMissed(int accuracy) {
        return !itHappened(accuracy);
    }
}
