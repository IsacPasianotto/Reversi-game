package player.computer;

import board.ValidMove;
import mechanics.ValidMovesChecker;

import java.util.Optional;
import java.util.Random;

public class RandomPlayer {

    public RandomPlayer () {
    }

    public ValidMove chooseMove (ValidMovesChecker checker) throws Exception {
        int size = checker.getValidMoves().size();
        Random rnd = new Random();
        int extracted = rnd.nextInt(0, size);
        // wait a second
        // wait(1500);
        Optional<ValidMove> randomMove = Optional.ofNullable(checker.getValidMoves().get(extracted));
        if (randomMove.isEmpty())
            throw new Exception("empty");
        else
            return randomMove.get();
    }

}
