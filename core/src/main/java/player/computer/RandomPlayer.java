package player.computer;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player {
    private final ColoredPawn color;
    public RandomPlayer(ColoredPawn color) {
        this.color = color;
    }
    public ValidMove askForAMove(GameController gameController) {
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        Random rnd = new Random();
        int extracted = rnd.nextInt(validMoves.size());
        return validMoves.get(extracted);
    }

    public void close() { }

    public ColoredPawn getPlayerColor(){
        return color;
    }
}
