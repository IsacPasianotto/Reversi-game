package terminal;

import board.Board;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.Human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TerminalMain {
    public static void main(String[] args) {
        String begin = """
                mechanics.Game started!
                ######################################################################################
                Welcome to Reversi!
                This is the list of possible commands:

                - Enter a letter (upper or lower case) followed by a number to place a pawn in the corresponding box (examples: a1, A1)
                If the move is not valid you will be asked to enter another one, and the system will show you the available ones.

                - quit: quit the game

                - undo: undo the last move you have done (it will also undo bot last move if you are playing solo)
                ######################################################################################
                """;
        System.out.println(begin);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Player firstPlayer = new Human();
        Player secondPlayer = new Human();

        int difficulty;
        int start;
        int chosenMode = chooseGameMode(reader);
        if (chosenMode != 1) {
            difficulty = chooseDifficulty(reader);
            Player bot = (difficulty == 1 ? new RandomPlayer() : new SmartPlayer());
            if (chosenMode == 2) {
                start = choosePlayerStarting(reader);
                firstPlayer = (start == 1 ? new Human() : bot);
                secondPlayer = (start == 2 ? new Human() : bot);
            } else {
                firstPlayer = bot;
                secondPlayer = bot;
            }
        }

        GameTerminal game = new GameTerminal(new Board(), firstPlayer, secondPlayer);
        game.play();
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while closing the reader.");
        }
        firstPlayer.close();
        secondPlayer.close();
    }

    private static int chooseGameMode(BufferedReader reader) {
        int chosenMode = 0;
        System.out.print("Select the game mode:\n1: human vs human\n2: human vs computer\n3: computer vs computer\nYour choice (q to quit): ");
        while (chosenMode != 1 && chosenMode != 2 && chosenMode != 3) {
            chosenMode = findUserInput(reader);
            if (chosenMode != 1 && chosenMode != 2 && chosenMode != 3)
                System.out.print("Error, select a valid mode.\nYour choice (q to quit): ");
        }
        return chosenMode;
    }

    private static int chooseDifficulty(BufferedReader reader) {
        int difficulty = 0;
        System.out.print("Select bot difficulty:\n1: random bot\n2: smart bot\nYour choice (q to quit): ");
        while (difficulty != 1 && difficulty != 2) {
            difficulty = findUserInput(reader);
            if (difficulty != 1 && difficulty != 2)
                System.out.print("Error, select a valid difficulty.\nYour choice (q to quit): ");
        }
        return difficulty;
    }

    private static int choosePlayerStarting(BufferedReader reader) {
        int start = 0;
        System.out.print("Select which player starts playing:\n1: you\n2: bot\nYour choice (q to quit): ");
        while (start != 1 && start != 2) {
            start = findUserInput(reader);
            if (start != 1 && start != 2)
                System.out.print("Error, select one of the two choices.\nYour choice (q to quit): ");
        }
        return start;
    }

    protected static int findUserInput(BufferedReader reader) {
        String input = null;
        int intInput = 0;
        try {
            input = reader.readLine();
            intInput = Integer.parseInt(input);
        } catch (NumberFormatException | IOException e) {
            // Do nothing, skip the attempt and try again
        }
        if ((input == null) || input.equals("q")) {
            System.out.println("Quitting the game...Bye!");
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error while closing the reader.");
            }
            System.exit(0);
        }
        return intInput;
    }

}