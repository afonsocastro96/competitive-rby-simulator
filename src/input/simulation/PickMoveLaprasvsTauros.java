package input.simulation;

import engine.VisibleBoard;

public class PickMoveLaprasvsTauros {
    public static String pickplayer1Move(VisibleBoard board) {
        if(board.getOpponentActivePokemon().getCurrentHp() < 109)
            return "Body Slam";
        if(board.getOpponentActivePokemon().getCurrentHp() > 427)
            return "Body Slam";
        if(board.getOwnActivePokemon().getCurrentHp() < 159) {
            if (board.getOpponentActivePokemon().getStatus() != null && board.getOpponentActivePokemon().getCurrentHp() < 271)
                return "Body Slam";
            return "Hyper Beam";
        }
        if(board.getOpponentActivePokemon().getCurrentHp() < 187)
            return "Hyper Beam";
        else return "Body Slam";
    }

    public static String pickplayer2Move(VisibleBoard board) {
        if(board.getOpponentActivePokemon().getCurrentHp() < 86)
            return "Thunderbolt";
        if(board.getOpponentActivePokemon().getCurrentHp() < 174)
            return "Blizzard";
        if(board.getOpponentActivePokemon().getStatus() == null)
            return "Sing";
        else return "Blizzard";
    }
}
