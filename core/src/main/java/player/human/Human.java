package player.human;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * A Human player that asks for the move through the standard input.
 * @see Player
 */
public class Human implements Player {
    private final UserInputReader reader;
    private final ColoredPawn color;

    /**
     * Constructor for the Human class.
     * @param color The color, represented by a ColoredPawn, the human player will play as.
     */
    public Human(ColoredPawn color) {
        reader = new UserInputReader(new BufferedReader(new InputStreamReader(System.in)));
        this.color = color;
    }

    /**
     * Asks the GameController for the list of valid moves in the current state of the game and returns the one that the player wants to play.
     * @param gameController The GameController object that is used to get the list of valid moves.
     * @see ValidMove
     * @see GameController
     * @return The move, as a ValidMove object, that the player wants to play.
     * @throws QuitGameException if the player wants to quit the game
     * @throws UndoException if the player wants to undo the last move
     */
    public ValidMove askForAMove(GameController gameController) throws UndoException, QuitGameException {
        Optional<ValidMove> enteredMove = Optional.empty();
        while (enteredMove.isEmpty()) {
            String readInput = reader.readInput();
            enteredMove = gameController.getMove(readInput);
        }
        return enteredMove.get();
    }

    /**
     * Closes the Human object, needed because the Human player uses
     * a BufferedReader that needs to be closed.
     */
    public void close() {
        reader.close();
    }

    /**
     * Returns the color of the pawns the Human is playing as.
     * @return The color of the pawns the Human is playing as.
     */
    public ColoredPawn getPlayerColor(){
        return color;
    }
}
