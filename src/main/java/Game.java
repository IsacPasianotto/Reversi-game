import board.Board;
import board.ValidMove;
import board.coords.BoardTile;
import mechanics.ValidMovesChecker;
import player.human.UserInputReader;

import java.io.IOException;
import java.util.Optional;

public class Game {

    public void play(Board board, UserInputReader reader) {
        ValidMovesChecker movesChecker = new ValidMovesChecker(board);
        while (!board.isGameOver()) {
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                System.out.println("No valid moves for the current player. Changing turn.");
                board.changeTurn();
                continue;
            }
            ValidMove validMove = askForAMove(reader, movesChecker);
            board.makeMove(validMove);
        }
    }

    ValidMove askForAMove(UserInputReader reader, ValidMovesChecker movesChecker) {
        System.out.print("Enter your move: ");
        while (true) {
            try {
                return getMove(reader, movesChecker);
            } catch (IllegalArgumentException e) {
                movesChecker.printErrorMessage();
            }
        }
    }

    ValidMove getMove(UserInputReader reader, ValidMovesChecker movesChecker) throws IllegalArgumentException {
        String move = reader.readInput();
        BoardTile chosen = new BoardTile(move);
        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
        if (validMove.isEmpty()) {
            throw new IllegalArgumentException("Not acceptable move entered.");
        } else {
            return new ValidMove(chosen, validMove.get().getValidDirections());
        }
    }

    public static void main(String[] args) {
        System.out.println("There will be the start of the game");
        Board board = new Board();
        //Player bot = new Player();
        Game game = new Game();

        try (UserInputReader reader = new UserInputReader()) {
            game.play(board, reader);
        } catch (Exception e) {
            System.out.println("Game unexpectedly closed");
        }
    }
}
