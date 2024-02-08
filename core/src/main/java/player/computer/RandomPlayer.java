package player.computer;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * A Computer player that makes the move randomly. It is used as the easiest difficulty level.
 *
 * @see Player
 */
public class RandomPlayer implements Player {
    private final ColoredPawn color;

    /**
     * Constructor for the RandomPlayer class.
     *
     * @param color The color, represented by a ColoredPawn, the random player will play as.
     */
    public RandomPlayer(ColoredPawn color) {
        this.color = color;
    }

    /**
     * Asks the GameController for the list of valid moves int the current state of the game and returns a randomly chosen one.
     *
     * @param gameController The GameController object that is used to get the list of valid moves.
     * @return A randomly chosen ValidMove object from the list of all the possible moves.
     */
    @Override
    public ValidMove askForAMove(GameController gameController) {
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        Random rnd = new Random();
        int extracted = rnd.nextInt(validMoves.size());
        return validMoves.get(extracted);
    }

    /**
     * Checks if the player is a human player or not
     *
     * @return true if the player is a human player, false otherwise
     */
    @Override
    public boolean isHumanPlayer() {
        return false;
    }

    /**
     * Closes the RandomPlayer object.
     */
    @Override
    public void close() {
    }

    /**
     * Returns the color of the player as a ColoredPawn.
     *
     * @return The color of the player.
     */
    @Override
    public ColoredPawn getPlayerColor() {
        return color;
    }
}
