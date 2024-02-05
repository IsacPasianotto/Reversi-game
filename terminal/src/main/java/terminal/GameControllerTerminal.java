package terminal;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import mechanics.GameController;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  GameController class tuned for the terminal version of the game.
 *  This class adds the prints in the terminal to show the game status, the request for the move and the final scores.
 * @see GameController
 */
public class GameControllerTerminal extends GameController {

    /**
     * Constructor for the GameControllerTerminal class.
     * @param board the board to be used in the game
     */
    public GameControllerTerminal(Board board) {
        super(board);
    }

    /**
     * Method to get the move from the player, asking for the input in the terminal.
     * @param readInput String representing the move the player wants to make, should be in the format "A1" to "H8".
     * @return an Optional containing the move as a ValidMove object if the move is valid, an empty Optional otherwise.
     * @see GameController#getMove(String)
     * @see ValidMove
     */
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

    /**
     * Prints the final scores of the game in the terminal and declares the winner.
     */
    protected void printFinalScores() {
        System.out.println(getBoard());
        int whiteScore = computeScoreForPlayer(ColoredPawn.WHITE);
        int blackScore = computeScoreForPlayer(ColoredPawn.BLACK);
        System.out.println("FINAL SCORE: " + ColoredPawn.WHITE + ": " + whiteScore + ", " + ColoredPawn.BLACK + ": " + blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
    }

    private void printInvalidMoveMessage() {
        System.out.println("Invalid move entered. Valid moves are: ");
        String moves = getValidMovesInCurrentStatusAsString();
        System.out.println(moves);
        System.out.print("Enter your move: ");
    }

    private String getValidMovesInCurrentStatusAsString() {
        return getValidMoves().stream().
                map(validMove -> validMove.position() + " ").
                collect(Collectors.joining());
    }

}