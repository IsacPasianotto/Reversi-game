package player.human;

import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

public class Human implements Player {

    private UserInputReader reader;
    public void makeMove() {

    }

    public Human() {
        reader = new UserInputReader();
    }

    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws UndoException,
                                                                            QuitGameException
    {
        System.out.print("Enter your move: ");
        while (true) {
            try {
                return reader.getMove(validMovesChecker);
            } catch (IllegalArgumentException e) {
                validMovesChecker.printErrorMessage();
            }
        }
    }


    public void close() {
        reader.close();
    }
}
