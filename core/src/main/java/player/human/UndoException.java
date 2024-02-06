package player.human;

/**
 * Exception thrown when the player wants to quit the game.
 */
public class UndoException extends Exception {
    /**
     * Constructor for the UndoException class, the message is hardcoded.
     */
    public UndoException() {
        super("Undoing last move.");
    }
}
