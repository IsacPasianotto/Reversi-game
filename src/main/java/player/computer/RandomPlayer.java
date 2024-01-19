package player.computer;

import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.Random;

public class RandomPlayer implements Player {

    public RandomPlayer () {
    }

    public ValidMove askForAMove(ValidMovesChecker checker) {
        int size = checker.getValidMoves().size();
        Random rnd = new Random();
        int extracted = rnd.nextInt(size);
        return checker.getValidMoves().get(extracted);
    }

    public void close() { }

}
