package player.human;

import board.ValidMove;
import board.coords.BoardTile;
import mechanics.ValidMovesChecker;
import player.Player;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Human implements Player {

    private final UserInputReader reader;

    public Human() {
        reader = new UserInputReader();
    }

    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws UndoException, QuitGameException {
        System.out.print("Enter your move: ");
        while (true) {
            try{
            Optional<ValidMove> validMove = getMove(validMovesChecker);
            if (validMove.isPresent())
                return validMove.get();
            else
                validMovesChecker.printErrorMessage();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Optional<ValidMove> getMove(ValidMovesChecker movesChecker) throws IllegalArgumentException, UndoException,QuitGameException {
        String readInput = reader.readInput();
        BoardTile chosen = new BoardTile(readInput);
        return movesChecker.IsValid(chosen);
//        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
//        if (validMove.isEmpty()) throw new IllegalArgumentException("Not acceptable move entered.");
//        else return validMove.get();
    }



    public void close() {
        reader.close();
    }
}
