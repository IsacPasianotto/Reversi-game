package player.human;

public class QuitGameException extends Throwable {
    public QuitGameException() {
        super("Quitting game...\nThanks for playing!");
    }
}
