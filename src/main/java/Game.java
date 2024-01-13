import board.Board;
import board.PossibleMovesChecker;
import board.ValidMove;
import player.Player;

import java.util.ArrayList;

public class Game {




    public static void main(String[] args) {
        System.out.println("There will be the start of the game");
        Board board = new Board();
        System.out.println("------------------------------------------\n\n");
        board.printBoard();
        System.out.println("\n\n\n----------------------------------------");
        PossibleMovesChecker movesChecker = new PossibleMovesChecker(board, board.isBlackToMove());
        ArrayList<ValidMove> validMoves = movesChecker.getValidMoves();

        Player umano = new Player();
        umano.makeMove(board, validMoves.get(0));
        movesChecker = new PossibleMovesChecker(board, board.isBlackToMove());
        validMoves = movesChecker.getValidMoves();
        umano.makeMove(board, validMoves.get(0));

        board.printBoard();




    }

    //boolean blackHasToPlay = true;
}
