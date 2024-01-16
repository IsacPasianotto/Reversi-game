package player.human;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

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


}
