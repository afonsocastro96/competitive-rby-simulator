package input.simulation;

import engine.VisibleBoard;

public class PickMove {
    public static String pickplayer1Move(VisibleBoard board) {
        if (board.getOwnActivePokemon().getSpecialModifier() == 0 && board.getOwnActivePokemon().getCurrentHp() <= 171)
            return "Recover";
        if (board.getOwnActivePokemon().getSpecialModifier() == -1 && board.getOwnActivePokemon().getCurrentHp() <= 207)
            return "Recover";
        if(board.getOpponentActivePokemon().getStatus() == null)
            return "Thunder Wave";
        return "Surf";
    }

    public static String pickplayer2Move(VisibleBoard board) {
        if(board.getOwnActivePokemon().getCurrentHp() <= 190)
            return "Recover";
        if(board.getOpponentActivePokemon().getCurrentHp() <= 100)
            return "Seismic Toss";
        if(board.getOpponentActivePokemon().getStatus() == null)
            return "Thunder Wave";
        else return "Psychic";
    }
}
