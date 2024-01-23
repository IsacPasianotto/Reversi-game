package player.human;

import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class Human implements Player {
    private final UserInputReader reader;

    public Human() {
        reader = new UserInputReader(new BufferedReader(new InputStreamReader(System.in)));
    }

    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws UndoException, QuitGameException {
        Optional<ValidMove> enteredMove = Optional.empty();
        while (enteredMove.isEmpty()) {
            String readInput = reader.readInput();
            enteredMove = validMovesChecker.getMoveInTerminal(readInput);
        }
        return enteredMove.get();
    }

    public void close() {
        reader.close();
    }
}
