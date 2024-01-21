package player.human;

import board.ValidMove;
import board.coords.BoardTile;
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
        System.out.print("Enter your move: ");
        Optional<ValidMove> enteredMove = Optional.empty();
        while (enteredMove.isEmpty())
            enteredMove = getMove(validMovesChecker);
        return enteredMove.get();
    }

    private Optional<ValidMove> getMove(ValidMovesChecker validMovesChecker) throws QuitGameException, UndoException {
        try {
            String readInput = reader.readInput();
            BoardTile chosen = new BoardTile(readInput);
            Optional<ValidMove> enteredMove = validMovesChecker.IsValid(chosen);
            if (enteredMove.isEmpty())
                validMovesChecker.getInvalidMoveMessage();
            return enteredMove;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.print("Enter your move: ");
        }
        return Optional.empty();
    }

    public void close() {
        reader.close();
    }
}
