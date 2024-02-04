package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameController {
    protected final Board board;
    private final ArrayList<ValidMove> validMoves;

    public GameController(Board board) {
        this.board = board;
        validMoves = new ArrayList<>();
    }

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

    public Optional<ValidMove> getMove(String input) {
        BoardTile chosen = new BoardTile(input);
        return isValid(chosen);
    }

    protected Optional<ValidMove> isValid(BoardTile chosen) {
        return validMoves.stream().filter(validMove -> validMove.position().equals(chosen)).findAny();
    }

    protected void applyMoveToBoard(ValidMove move) {
        board.applyMoveToBoard(move);
    }

    protected void importBoardFrom(Board board) {
        this.board.importBoardFrom(board);
    }

    protected int computeScoreForPlayer(ColoredPawn playerColor) {
        return board.computeScoreForPlayer(playerColor);
    }

    public boolean thereAreNoValidMoves() {
        return validMoves.isEmpty();
    }

    public ArrayList<ValidMove> getValidMoves() {
        return new ArrayList<>(validMoves);
    }

    public Board getBoard() {
        return board;
    }
}