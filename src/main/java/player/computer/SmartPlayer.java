package player.computer;

import board.Board;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class SmartPlayer implements Player {

    @Override
    public ValidMove askForAMove(ValidMovesChecker validMovesChecker)  {
        ArrayList<Integer> futureScores = new ArrayList<>();
        ArrayList<ValidMove> validMoves = validMovesChecker.getValidMoves();
        for (ValidMove validMove : validMoves) {
            Board board = validMovesChecker.getBoard().copy();
            board.applyMoveToBoard(validMove);
            futureScores.add(board.computeScoreForPlayer(board.getCurrentOpponentColor()));
        }
        int maxScore = Collections.max(futureScores);
        return validMoves.get(futureScores.indexOf(maxScore));
    }

    public void close()  { }

}
