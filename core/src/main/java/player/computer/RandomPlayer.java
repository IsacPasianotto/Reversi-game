package player.computer;

import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player {
    public ValidMove askForAMove(GameController gameController) {
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        Random rnd = new Random();
        int extracted = rnd.nextInt(validMoves.size());
        return validMoves.get(extracted);
    }

    public void close() { }
}
