package player.human;

/**
 * Exception thrown when the player wants to quit the game.
 */
public class QuitGameException extends Exception {
    /**
     * Constructor for the QuitGameException class, the message is hardcoded.
     */
    public QuitGameException() {
        super("Quitting game...\nThanks for playing!");
    }
}
