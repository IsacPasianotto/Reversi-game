import board.Board;
import board.coords.BoardTile;
import mechanics.InputMoveValidator;
import mechanics.PossibleMovesChecker;
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
        // ValidMove chosen = movesChecker.getValidMoves().get(0);

        BoardTile chosen = new BoardTile("a1");
        InputMoveValidator validator = new InputMoveValidator();
        validator.IsValid(movesChecker.getValidMoves(), chosen);


//        bot.makeMove(board, chosen);
//
//        board.printBoard();
//
//        movesChecker.computeValidMoves();
//
//        chosen = movesChecker.getValidMoves().get(0);
//
//        bot.makeMove(board, chosen);
//
//        board.printBoard();
//



    }

    //boolean blackHasToPlay = true;
}
