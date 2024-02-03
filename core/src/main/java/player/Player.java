package player;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

public interface Player {
    ValidMove askForAMove(GameController gameController) throws QuitGameException, UndoException;

    static boolean isHumanPlayer(Player player) {
        return player.getClass().equals(Human.class);
    }

    void close();

    ColoredPawn getPlayerColor();
}
