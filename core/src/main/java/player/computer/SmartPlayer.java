package player.computer;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartPlayer implements Player {

    private final ColoredPawn color;

    public SmartPlayer(ColoredPawn color) {
        this.color = color;
    }
    @Override
    public ValidMove askForAMove(GameController gameController) {
        List<Integer> futureScores = new ArrayList<>(0);
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        for (ValidMove validMove : validMoves) {
            Board fakeBoard = gameController.getBoard().copy();
            fakeBoard.applyMoveToBoard(validMove);
            futureScores.add(fakeBoard.computeScoreForPlayer(color));
        }
        int maxScore = Collections.max(futureScores);
        return validMoves.get(futureScores.indexOf(maxScore));
    }

    public void close() {
    }

    public ColoredPawn getPlayerColor(){
        return color;
    }

}
