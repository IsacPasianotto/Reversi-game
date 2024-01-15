package player.human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    BufferedReader reader;
    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInput() {
        String input;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            input = "";
        }
        return input;
    }
}
