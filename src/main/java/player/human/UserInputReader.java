package player.human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputReader implements AutoCloseable {
    BufferedReader reader;
    public UserInputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public UserInputReader(BufferedReader reader)  { this.reader = reader; }

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

}
