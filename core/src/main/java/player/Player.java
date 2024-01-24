package player;

import board.ValidMove;
import mechanics.GameController;
import player.human.QuitGameException;
import player.human.UndoException;

public interface Player {
    ValidMove askForAMove(GameController gameController) throws QuitGameException, UndoException;

    void close();


}
