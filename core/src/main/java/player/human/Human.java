package player.human;

import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.Optional;

public class Human implements Player {
    private final UserInputReader reader;

    public Human() {
        reader = new UserInputReader();
    }

    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws UndoException, QuitGameException {
        System.out.print("Enter your move: ");
        Optional<ValidMove> enteredMove = Optional.empty();
        while (enteredMove.isEmpty())
            enteredMove = getMove(validMovesChecker);
        return enteredMove.get();
    }

    private Optional<ValidMove> getMove(ValidMovesChecker validMovesChecker) throws QuitGameException, UndoException {
        try{
            String readInput = reader.readInput();
            BoardTile chosen = new BoardTile(readInput);
            Optional<ValidMove> enteredMove = validMovesChecker.IsValid(chosen);
            if (enteredMove.isEmpty()) getInvalidMoveMessage(validMovesChecker);
            return enteredMove;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.print("Enter your move: ");
        }
        return Optional.empty();
    }

    private void getInvalidMoveMessage(ValidMovesChecker validMovesChecker) {
        System.out.println("Invalid move entered. Valid moves are: ");
        System.out.println(validMovesChecker.printValidMoves());
        System.out.print("Enter your move: ");
    }

    public void close() {
        reader.close();
    }
}
