package player.computer;

import board.Board;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.ArrayList;

public class SmartPlayer implements Player {


    ArrayList<Integer> futureScores;

    @Override
    public ValidMove askForAMove(ValidMovesChecker validMovesChecker) throws Exception {
        for (int i = 0; i == validMovesChecker.getValidMoves().size(); i++) {
            Board board = new Board();
            board.copy(validMovesChecker.getBoard());
            board.makeMove(validMovesChecker.getValidMoves().get(i));
            futureScores.add(board.computeScoreForPlayer(validMovesChecker.getBoard().getCurrentPlayer()));
        }
        //find the max future score
        int max =  futureScores.get(0);
        for (int i = 1; i < futureScores.size(); i++) {
            if (futureScores.get(i) > max) {
                max = futureScores.get(i);
            }
        }
        return validMovesChecker.getValidMoves().get(futureScores.indexOf(max));
    }


    public void close()  { }

}
