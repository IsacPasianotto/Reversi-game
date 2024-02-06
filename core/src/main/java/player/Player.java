package player;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

/**
 * Interface for the player classes. It defines the methods that both the human and computer players must implement.
 * @see Human
 * @see player.computer.SmartPlayer
 * @see player.computer.RandomPlayer
 */
public interface Player {
    /**
     * Retrieve from the player a move to be played
     * @param gameController The GameController object that is used to get the list of valid moves.
     * @return A ValidMove object that represents the move the player wants to play.
     * @throws QuitGameException if the player (Human) wants to quit the game
     * @throws UndoException if the player (Human) wants to undo the last move
     */
    ValidMove askForAMove(GameController gameController) throws QuitGameException, UndoException;

    /**
     * Checks if the player is a human player or not
     * @return true if the player is a human player, false otherwise
     */
    boolean isHumanPlayer();

    /**
     * Closes the Player object, needed because the Human player uses a BufferedReader that needs to be closed.
     * Computer players will do a do-nothing implementation of this method.
     */
    void close();

    /**
     * Returns the color of pawn the player is playing as.
     * @see ColoredPawn
     * @return The color of the player.
     */
    ColoredPawn getPlayerColor();
}
