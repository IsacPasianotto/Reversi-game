package player;

import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.Optional;

public interface Player {
    ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws QuitGameException, UndoException;

    void close();


}
