package player;

import board.ValidMove;
import mechanics.ValidMovesChecker;

public interface Player {
    ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws Exception;

    void close();


}
