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
        while (!board.isGameOver()) {
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                System.out.println("No valid moves for the current player. Changing turn.");
                board.changeTurn();
                continue;
            }
            ValidMove validMove = askForAMove(board, bot, reader, movesChecker);
            bot.makeMove(board, validMove);
        }
    }

    ValidMove askForAMove(Board board, Player bot, InputReader reader, ValidMovesChecker movesChecker) {
        System.out.print("Enter your move: ");
        String move;
        BoardTile chosen = null;
        Optional<ValidMove> validMove = Optional.empty();
//        while (chosen == null || validMove.isEmpty()) {
        while (true) {
            try {
                return getMove(board, bot, reader, movesChecker);
            } catch (IllegalArgumentException e) {
                System.out.println("Not acceptable move entered.");
            }
        }
//        return null; // this line is never reached
    }

    ValidMove getMove(Board board, Player bot, InputReader reader, ValidMovesChecker movesChecker) throws IllegalArgumentException {
        String move = reader.readInput();
        BoardTile chosen = new BoardTile(move);
        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
        if (validMove.isEmpty()) {
            movesChecker.printErrorMessage();
            throw new IllegalArgumentException("Not acceptable move entered.");
        } else {
            return new ValidMove(chosen, validMove.get().getValidDirections());
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
