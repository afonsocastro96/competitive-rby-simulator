package input.simulation;

import engine.VisibleBoard;

public class PickMove {
    public static int i = 0;

    public static String pickplayer1Move(VisibleBoard board) {
        if(board.getOpponentActivePokemon().getCurrentHp() <=100) return "Seismic Toss";
        if(board.getOwnActivePokemon().getCurrentHp() < 0.60*703 && board.getOwnActivePokemon().getMovePP("Softboiled") != 0)
            return "Softboiled";
        return "Seismic Toss";
    }

    public static String pickplayer2Move(VisibleBoard board) {
        return "Skull Bash";
        /*if(board.getOwnActivePokemon().getCurrentHp() == 63 && board.getOpponentActivePokemon().getCurrentHp() <= 370)
            return "Explosion";
        if(board.getOwnActivePokemon().getMovePP("Earthquake") != 0)
            return "Earthquake";
        if(board.getOwnActivePokemon().getMovePP("Rock Slide") != 0)
            return "Rock Slide";
        if(board.getOwnActivePokemon().getMovePP("Body Slam") != 0)
            return "Body Slam";
        else
            return "Struggle";*/
    }
}
