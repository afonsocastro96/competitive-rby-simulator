package logic;

import logic.things.Type;

public class TypeMatchups {
    // Stored as [attacker][defender]
    private static final double[][] matchups = new double[][]{
            {1, 1, 1, 1, 1, 0.5, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {2, 1, 0.5, 0.5, 1, 2, 0.5, 0, 1, 1, 1, 1, 0.5, 2, 1},
            {1, 2, 1, 1, 1, 0.5, 2, 1, 1, 1, 2, 0.5, 1, 1, 1},
            {1, 1, 1, 0.5, 0.5, 0.5, 2, 0.5, 1, 1, 2, 1, 1, 1, 1},
            {1, 1, 0, 2, 1, 2, 0.5, 1, 2, 1, 0.5, 2, 1, 1, 1},
            {1, 0.5, 2, 1, 0.5, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 0.5, 0.5, 2, 1, 1, 1, 1, 0.5, 1, 2, 1, 2, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0.5, 2, 1, 0.5, 0.5, 2, 1, 1, 2, 0.5},
            {1, 1, 1, 1, 2, 2, 1, 1, 2, 0.5, 0.5, 1, 1, 1, 0.5},
            {1, 1, 0.5, 0.5, 2, 2, 0.5, 1, 0.5, 2, 0.5, 1, 1, 1, 0.5},
            {1, 1, 2, 1, 0, 1, 1, 1, 1, 2, 0.5, 0.5, 1, 1, 0.5},
            {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, 1},
            {1, 1, 2, 1, 2, 1, 1, 1, 1, 0.5, 2, 1, 1, 0.5, 2},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}
    };

    public static double getMatchup(Type attacker, Type defender) {
        return matchups[attacker.getId()][defender.getId()];
    }

    public static boolean isSuperEffective(Type attacker, Type defender1, Type defender2) {
        return getMatchup(attacker, defender1) * getMatchup(attacker, defender2) > 1;
    }


    public static boolean isNotVeryEffective(Type attacker, Type defender1, Type defender2) {
        double effectiveness = getMatchup(attacker, defender1) * getMatchup(attacker, defender2);
        return effectiveness < 1 && effectiveness > 0;
    }

    public static boolean doesntAffect(Type attacker, Type defender1, Type defender2) {
        return getMatchup(attacker, defender1) * getMatchup(attacker, defender2) == 0;
    }

}
