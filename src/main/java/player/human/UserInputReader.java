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

    public String readInput() throws QuitGameException, UndoException{
        String input;
        try {
            input = reader.readLine();
            if (input.equalsIgnoreCase("quit")) throw new QuitGameException();
            if (input.equalsIgnoreCase("undo")) throw new UndoException();
        } catch (IOException e) {
            input = "";
        }
        return input;
    }

    @Override
    public void close() {
        try {
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error while closing the reader.");
        }
    }

    public ValidMove getMove(ValidMovesChecker movesChecker) throws IllegalArgumentException,
                                                                    UndoException,
                                                                    QuitGameException
    {
        String readInput = readInput();

        BoardTile chosen = new BoardTile(readInput);
        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
        if (validMove.isEmpty()) {
            throw new IllegalArgumentException("Not acceptable move entered.");
        } else  {
            return validMove.get();
        }
    }
}
