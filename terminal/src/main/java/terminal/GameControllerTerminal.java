package terminal;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import mechanics.GameController;

import java.util.Optional;
import java.util.stream.Collectors;

public class GameControllerTerminal extends GameController {

    public GameControllerTerminal(Board board) {
        super(board);
    }

    private String getValidMovesInCurrentStatusAsString() {
        return validMoves.stream().map(validMove -> validMove.position() + " ").collect(Collectors.joining());
    }

    @Override
    public Optional<ValidMove> getMove(String readInput)  {
        try {
            BoardTile chosen = new BoardTile(readInput);
            Optional<ValidMove> enteredMove = isValid(chosen);
            if (enteredMove.isEmpty())
                printInvalidMoveMessage();
            return enteredMove;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.print("Enter your move: ");
        }
        return Optional.empty(); // not reached
    }


    private void printInvalidMoveMessage() {
        System.out.println("Invalid move entered. Valid moves are: ");
        String moves = getValidMovesInCurrentStatusAsString();
        System.out.println(moves);
        System.out.print("Enter your move: ");
    }

    public void printFinalScores() {
        System.out.println(getBoard());
        int whiteScore = computeScoreForPlayer(ColoredPawn.WHITE);
        int blackScore = computeScoreForPlayer(ColoredPawn.BLACK);
        System.out.println("FINAL SCORE: " + ColoredPawn.WHITE + ": " + whiteScore + ", " + ColoredPawn.BLACK + ": " + blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
    }
}