package player.human;

import board.ValidMove;
import board.coords.BoardTile;
import mechanics.ValidMovesChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class UserInputReader implements AutoCloseable {
    BufferedReader reader;
    public UserInputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInput() {
        String input;
        try {
            input = reader.readLine();
            if (input.equalsIgnoreCase("quit")) throw new QuitGameException();
        } catch (IOException e) {
            input = "";
        } catch (QuitGameException e) {
            System.out.println(e.getMessage());
            System.exit(0);
            input = "";
        }
        return input;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }


    public ValidMove askForAMove(ValidMovesChecker movesChecker) throws UndoException{
        System.out.print("Enter your move: ");
        while (true) {
            try {
                return getMove(movesChecker);
            } catch (IllegalArgumentException e) {
                movesChecker.printErrorMessage();
            }
        }
    }

    public ValidMove getMove(ValidMovesChecker movesChecker) throws IllegalArgumentException, UndoException {
        String readInput = readInput();
        if (readInput.equalsIgnoreCase("undo")){
            throw new UndoException();
        }
        BoardTile chosen = new BoardTile(readInput);
        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
        if (validMove.isEmpty()) {
            throw new IllegalArgumentException("Not acceptable move entered.");
        } else  {
            return validMove.get();
        }
    }
}
