package input.simulation;

import engine.VisibleBoard;

public class PickMoveSnorlaxvsTauros {
    public static int i = 0;

    public static String pickplayer1Move(VisibleBoard board) {
        if(board.getTurn() == 1)
            return "Reflect";
        if(board.getOpponentActivePokemon().getCurrentHp() <= 110)
            return "Body Slam";
        if(board.getOwnActivePokemon().getCurrentHp() <= 180)
            return "Rest";
        return "Ice Beam";
    }

    public static String pickplayer2Move(VisibleBoard board) {
        if(board.getOpponentActivePokemon().getCurrentHp() <= 62)
            return "Body Slam";
        if(board.getOpponentActivePokemon().getCurrentHp() <= 121 && !board.getOpponentActivePokemon().hasVolatileStatus("Reflect"))
            return "Body Slam";
        if(board.getOpponentActivePokemon().getCurrentHp() <= 123 && board.getOpponentActivePokemon().hasVolatileStatus("Reflect"))
            return "Hyper Beam";
        if(board.getOpponentActivePokemon().getCurrentHp() <= 240 && !board.getOpponentActivePokemon().hasVolatileStatus("Reflect"))
            return "Hyper Beam";
        if(board.getOwnActivePokemon().getCurrentHp() <= 108)
            if(board.getOpponentActivePokemon().getCurrentHp() >= 415)
                return "Blizzard";
            else return "Hyper Beam";
        if(board.getOpponentActivePokemon().getCurrentHp() <= 100 && board.getOpponentActivePokemon().hasVolatileStatus("Reflect"))
            return "Blizzard";

        if(board.getOpponentActivePokemon().getStatus() != null)
            if(board.getOpponentActivePokemon().getStatus().getName().equals("Sleep"))
                return "Body Slam";

        return "Blizzard";
    }
}
