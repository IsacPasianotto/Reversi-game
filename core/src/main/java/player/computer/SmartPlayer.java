package player.computer;

import board.Board;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartPlayer implements Player {
    @Override
    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) {
        List<Integer> futureScores = new ArrayList<>(0);
        ArrayList<ValidMove> validMoves = validMovesChecker.getValidMoves();
        for (ValidMove validMove : validMoves) {
            Board board = validMovesChecker.getBoard();
            board.applyMoveToBoard(validMove);
            futureScores.add(board.computeScoreForPlayer(validMovesChecker.getCurrentPlayerColor()));
        }
        int maxScore = Collections.max(futureScores);
        return validMoves.get(futureScores.indexOf(maxScore));
    }

    public void close() {
    }

}
