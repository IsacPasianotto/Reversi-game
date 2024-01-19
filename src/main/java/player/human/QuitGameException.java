package player.human;

public class QuitGameException extends Exception {
    public QuitGameException() {
        super("Quitting game...\nThanks for playing!");
    }
}
