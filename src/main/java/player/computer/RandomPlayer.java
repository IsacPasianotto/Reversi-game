package player.computer;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player {

    public ValidMove askForAMove(ValidMovesChecker checker) {
        ArrayList<ValidMove> validMoves = checker.getValidMoves();
        int numberOfValidMoves = validMoves.size();
        Random rnd = new Random();
        int extracted = rnd.nextInt(numberOfValidMoves);
        return validMoves.get(extracted);
    }

    public void close() { }

}
