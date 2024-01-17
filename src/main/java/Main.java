import board.Board;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.Human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        String begin = "Game started!\n" +
                       "######################################################################################\n" +
                       "Welcome to Reversi!\n" +
                       "This is the list of possible commands:\n\n" +
                       "- Enter a letter (upper or lower case) followed by a number to place a pawn in the corresponding box (examples: a1, A1)\n" +
                       "If the move is not valid you will be asked to enter another one, and the system will show you the available ones.\n\n" +
                       "- quit: quit the game\n\n" +
                       "- undo: undo the last move you have done (it will also undo bot's last move if you are playing solo)\n" +
                       "######################################################################################\n";
        System.out.println(begin);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int start = 0;
        int difficulty = 0;
        int chosenMode = chooseGameMode(reader);
        if (chosenMode == 2 || chosenMode == 3) {
            difficulty = chooseDifficulty(reader);
            if (chosenMode == 2)
                start = choosePlayerStarting(reader);
        }
        Player human = new Human();
        Player smartBot = new SmartPlayer();
        Player randomBot = new RandomPlayer();
        Player firstPlayer = (chosenMode == 1 || start == 1 ? human : (difficulty == 1 ? randomBot : smartBot));
        Player secondPlayer = (chosenMode == 1 || start == 2 ? human : (difficulty == 1 ? randomBot : smartBot));
        Game game = new Game(new Board(), firstPlayer, secondPlayer);
        game.play();

//        int smartWon = 0;
//        for(int i = 0; i < 150; i++) {
//            game = new Game(new Board(), bot, bot2);
//            game.play();
//            int nBlack = game.board.computeScoreForPlayer(Pawn.BLACK);
//            int nWhite = game.board.computeScoreForPlayer(Pawn.WHITE);
//            if (nBlack > nWhite)
//                smartWon++;
//        }
//        System.out.println("------------------------------------------------");
//        System.out.println("Smart won " + smartWon + " times out of 150 games.");

    }

    private static int choosePlayerStarting(BufferedReader reader) {
        String input = "";
        int start = 0;
        System.out.print("Select which player starts playing:\n1: you\n2: bot\nYour choice: ");
        while (start != 1 && start != 2) {
            start = findUserInput(reader);
            if (start != 1 && start != 2)
                System.out.println("Error, select one of the two choices");
        }
        return start;
    }

    private static int chooseDifficulty(BufferedReader reader) {
        String input = "";
        int difficulty = 0;
        System.out.print("Select bot difficulty:\n1: random bot\n2: smart bot\nYour choice: ");
        while (difficulty != 1 && difficulty != 2) {
            difficulty = findUserInput(reader);
            if (difficulty != 1 && difficulty != 2)
                System.out.println("Error, select a valid difficulty");

        }
        return difficulty;
    }

    private static int chooseGameMode(BufferedReader reader) {
        System.out.print("Select the game mode:\n1: human vs human\n2: human vs computer\n3: computer vs computer\nYour choice: ");
        int chosenMode = 0;
        while (chosenMode != 1 && chosenMode != 2 && chosenMode != 3) {
            chosenMode = findUserInput(reader);
            if (chosenMode != 1 && chosenMode != 2 && chosenMode != 3)
                System.out.println("Error, select a valid mode");
        }
        return chosenMode;
    }


    private static int findUserInput(BufferedReader reader) {
        String input = "";
        int intInput = 0;
        try {
            input = reader.readLine();
            intInput = Integer.parseInt(input);
        } catch (NumberFormatException n) {

        } catch (IOException e) {
            System.out.println("Unknown error, exit.");
            System.exit(0);
        }
        if (input == null) System.exit(0);
        return intInput;
    }

}