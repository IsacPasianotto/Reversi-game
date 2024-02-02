package player.computer;

import board.Board;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartPlayer implements Player {
    @Override
    public ValidMove askForAMove(GameController gameController) {
        List<Integer> futureScores = new ArrayList<>(0);
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        for (ValidMove validMove : validMoves) {
            //System.out.println("current board: ");
            //System.out.println(gameController.getBoard());
            Board board = gameController.getBoard();
            board.applyMoveToBoard(validMove);
            futureScores.add(board.computeScoreForPlayer(gameController.getCurrentPlayerColor()));
        }
        int maxScore = Collections.max(futureScores);
        return validMoves.get(futureScores.indexOf(maxScore));
    }

    public void close() {
    }

}
