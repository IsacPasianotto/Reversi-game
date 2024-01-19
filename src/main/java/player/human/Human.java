package player.human;

import board.ValidMove;
import board.coords.BoardTile;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.Optional;

public class Human implements Player {

    private UserInputReader reader;

    public Human() {
        reader = new UserInputReader();
    }

    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws UndoException, QuitGameException {
        System.out.print("Enter your move: ");
        while (true) {
            try {
                return getMove(validMovesChecker);
            } catch (IllegalArgumentException e) {
                validMovesChecker.printErrorMessage();
            }
        }
    }

    public ValidMove getMove(ValidMovesChecker movesChecker) throws IllegalArgumentException,
            UndoException,
            QuitGameException
    {
        String readInput = reader.readInput();
        BoardTile chosen = new BoardTile(readInput);
        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
        if (validMove.isEmpty()) {
            throw new IllegalArgumentException("Not acceptable move entered.");
        } else  {
            return validMove.get();
        }
    }



    public void close() {
        reader.close();
    }
}
