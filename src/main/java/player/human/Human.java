package player.human;

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
        while (true) {
            ValidMove enteredMove = getMove(validMovesChecker);
            if (enteredMove != null) return enteredMove;
        }
    }

    private ValidMove getMove(ValidMovesChecker validMovesChecker) throws QuitGameException, UndoException {
        try{
            String readInput = reader.readInput();
            BoardTile chosen = new BoardTile(readInput);
            Optional<ValidMove> enteredMove = validMovesChecker.IsValid(chosen);
            if (enteredMove.isPresent()) return enteredMove.get();
            else {
                System.out.println("Invalid move entered. Valid moves are: ");
                System.out.println(validMovesChecker.printValidMoves());
                System.out.print("Enter your move: ");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.print("Enter your move: ");
        }
        return null;
    }

    public void close() {
        reader.close();
    }
}
