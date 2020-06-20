package input.simulation;

import engine.VisibleBoard;

public class PickMoveJolteonvsTauros {
    public static String pickplayer1Move(VisibleBoard board) {
        if(board.getOpponentActivePokemon().getCurrentHp() == board.getOpponentActivePokemon().getHp())
            return "Body Slam";
        if(board.getOpponentActivePokemon().getCurrentHp() < 202)
            return "Earthquake";
        else return "Hyper Beam";
    }

    public static String pickplayer2Move(VisibleBoard board) {
        /*if(board.getOpponentActivePokemon().getStatus() == null)
            return "Thunder Wave";*/
        return "Thunderbolt";
    }
}
