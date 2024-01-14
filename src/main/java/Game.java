import board.Board;
import board.PossibleMovesChecker;
import board.ValidMove;
import player.Player;

public class Game {




    public static void main(String[] args) {
        System.out.println("There will be the start of the game");
        Board board = new Board();
        System.out.println("------------------------------------------\n\n");
        board.printBoard();
        System.out.println("\n\n\n----------------------------------------");
        PossibleMovesChecker movesChecker = new PossibleMovesChecker(board);
        movesChecker.computeValidMoves();

        Player bot = new Player();
        ValidMove chosen = movesChecker.getValidMoves().get(0);

        bot.makeMove(board, chosen);

        board.printBoard();

        movesChecker.computeValidMoves();

        chosen = movesChecker.getValidMoves().get(0);

        bot.makeMove(board, chosen);

        board.printBoard();



        //ArrayList<ValidMove> validMoves = movesChecker.computeValidMoves();

//        Player umano = new Player();
//        umano.makeMove(board, validMoves.get(0));
//        movesChecker = new PossibleMovesChecker(board);
//        validMoves = movesChecker.computeValidMoves();
//        umano.makeMove(board, validMoves.get(0));
//
//        board.printBoard();




    }

    //boolean blackHasToPlay = true;
}
