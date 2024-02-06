package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class is responsible for managing the game logic. It ensures that all the game rules are respected preventing
 * illegal moves to be approved in the Game class.
 * This class will compute all the valid moves regarding a certain given board and a certain player color.
 *
 * @see Game
 */
public class GameController {
    private final Board board;
    private final ArrayList<ValidMove> validMoves;

    /**
     * Constructor for the GameController class.
     *
     * @param board the board the controller consider for its computations.
     */
    public GameController(Board board) {
        this.board = board;
        validMoves = new ArrayList<>();
    }

    /**
     * This method will return a list of all the possible ValidMove objects for the current player.
     *
     * @return a list of all the valid moves for the current player.
     * @see ValidMove
     */
    public ArrayList<ValidMove> getValidMoves() {
        return new ArrayList<>(validMoves);
    }

    /**
     * Check if there is at least one valid move for the current player.
     *
     * @return true if there are no valid moves, false otherwise.
     */
    public boolean thereAreNoValidMoves() {
        return validMoves.isEmpty();
    }

    /**
     * This method will return the board the controller is considering for its computations.
     *
     * @return the board the controller has been initialized with.
     * @see Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Update the validMoves list with all the possible moves for the current player.
     *
     * @param currentPlayerColor the ColoredPawn of the current player (BLACK or WHITE values are expected).
     * @see ColoredPawn
     */
    public void computeValidMoves(ColoredPawn currentPlayerColor) {
        validMoves.clear();
        BoardTile currentPosition;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                currentPosition = new BoardTile(i, j);
                if (board.getPositionColor(currentPosition) == ColoredPawn.EMPTY)
                    checkPosition(currentPosition, currentPlayerColor);
            }
        }
    }

    /**
     * This method will return the ValidMove object corresponding to the input string if it represents a valid move.
     *
     * @param input String representing the move the player wants to make, should be in the format "A1" to "H8".
     * @return an Optional containing the ValidMove object if the input is a valid move, an empty Optional otherwise.
     */
    public Optional<ValidMove> getMove(String input) {
        BoardTile chosen = new BoardTile(input);
        return isValid(chosen);
    }

    /**
     * This method will return the ValidMove object corresponding to the input BoardTile if it represents a valid move, an empty Optional otherwise.
     *
     * @param chosen the BoardTile the player wants to move to.
     * @return an Optional containing the ValidMove object if the input is a valid move, an empty Optional otherwise.
     * @see BoardTile
     * @see ValidMove
     */
    protected Optional<ValidMove> isValid(BoardTile chosen) {
        return validMoves.stream().filter(validMove -> validMove.position().equals(chosen)).findAny();
    }

    /**
     * This method will apply the input move to the board.
     *
     * @param move the move to apply to the board.
     */
    protected void applyMoveToBoard(ValidMove move) {
        board.applyMoveToBoard(move);
    }

    /**
     * This method will update the controller's board with the given board.
     *
     * @param board the board to import from.
     */
    protected void importBoardFrom(Board board) {
        this.board.importBoardFrom(board);
    }

    /**
     * Compute the score for the requested player represented by the input ColoredPawn.
     *
     * @param playerColor the ColoredPawn representing the player to compute the score for, BLACK or WHITE values are expected.
     * @return the score for the requested player.
     */
    protected int computeScoreForPlayer(ColoredPawn playerColor) {
        return board.computeScoreForPlayer(playerColor);
    }

    private void checkPosition(BoardTile currentPosition, ColoredPawn currentPlayerColor) {
        ArrayList<Direction> directionsWithOppositeColor = findDirectionsWithOppositeColor(currentPosition, currentPlayerColor);
        if (directionsWithOppositeColor.isEmpty()) return;
        ArrayList<Direction> validDirections = computeValidDirections(currentPosition, directionsWithOppositeColor, currentPlayerColor);
        if (validDirections.isEmpty()) return;
        validMoves.add(new ValidMove(currentPosition, validDirections, currentPlayerColor));
    }

    ArrayList<Direction> findDirectionsWithOppositeColor(BoardTile currentPosition, ColoredPawn currentPlayerColor) {
        ArrayList<Direction> directionsWithOppositeColor = new ArrayList<>();
        Direction currentDirection;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                currentDirection = new Direction(i, j);
                if (hasTheRightColor(currentPosition, currentDirection, currentPlayerColor))
                    directionsWithOppositeColor.add(currentDirection);
            }
        }
        return directionsWithOppositeColor;
    }

    private boolean hasTheRightColor(BoardTile currentPosition, Direction currentDirection, ColoredPawn currentPlayerColor) {
        BoardTile neighbourToCheck = currentPosition.add(currentDirection);
        return neighbourToCheck.isInsideTheBoard() && (board.getPositionColor(neighbourToCheck) == currentPlayerColor.opposite());
    }

    private ArrayList<Direction> computeValidDirections(BoardTile currentPosition, ArrayList<Direction> directionsWithOppositeColor, ColoredPawn currentPlayerColor) {
        return directionsWithOppositeColor.stream()
                .filter(currentDirection -> isValidDirection(currentPosition, currentDirection, currentPlayerColor))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isValidDirection(BoardTile currentPosition, Direction currentDirection, ColoredPawn currentPlayerColor) {
        BoardTile tileCurrentlyOnCheck = currentPosition.add(currentDirection).add(currentDirection);
        while (tileCurrentlyOnCheck.isInsideTheBoard()) {
            if (board.getPositionColor(tileCurrentlyOnCheck) == ColoredPawn.EMPTY)
                return false;
            if (board.getPositionColor(tileCurrentlyOnCheck) == currentPlayerColor)
                return true;
            tileCurrentlyOnCheck = tileCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }
}