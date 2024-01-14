import board.Board;
import board.ValidMove;
import board.coords.BoardTile;
import mechanics.ValidMovesChecker;
import player.Player;
import player.human.InputReader;

import java.util.Optional;

public class Game {

    public void play(Board board, Player bot) {
        ValidMovesChecker movesChecker = new ValidMovesChecker(board);
        InputReader reader = new InputReader();
        System.out.println("Welcome to the game of Reversi!");
        while (!board.isGameOver()) {
            System.out.println("Turn; "+board.isBlackToMove());
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                System.out.println("No valid moves for the current player. Changing turn.");
                board.changeTurn();
                continue;
            }
            System.out.println("Enter your move: ");
            String move;
            BoardTile chosen = null;
            while (chosen == null) {
                try {
                    move = reader.readInput();
                    chosen = new BoardTile(move);
                } catch (IllegalArgumentException e) {
                    System.out.println("Not acceptable move entered.");
                }
            }
            Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
            if (validMove.isEmpty()) {
                movesChecker.printErrorMessage();
            } else {
                bot.makeMove(board, validMove.get());
            }
            System.out.println("New turn");
        }
    }

    public static void main(String[] args) {
        System.out.println("There will be the start of the game");
        Board board = new Board();
        Player bot = new Player();
        Game game = new Game();
        game.play(board, bot);
    }
}
