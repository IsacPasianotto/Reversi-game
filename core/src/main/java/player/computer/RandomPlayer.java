package player.computer;

import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player {

    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) {
        ArrayList<ValidMove> validMoves = validMovesChecker.getValidMoves();
        Random rnd = new Random();
        int extracted = rnd.nextInt(validMovesChecker.numberOfValidMoves());
        return validMoves.get(extracted);
    }

    public void close() {
    }
}
