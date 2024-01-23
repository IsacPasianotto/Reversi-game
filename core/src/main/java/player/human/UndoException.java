package player.human;

public class UndoException extends Exception {
    public UndoException() {
        super("Undoing last move.");
    }
}
