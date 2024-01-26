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
    private final Board board;
    protected final ArrayList<ValidMove> validMoves;
    protected boolean blackToMove;

    public GameController(Board board) {
        this.board = board;
        validMoves = new ArrayList<>();
        blackToMove = true;
    }

    public void computeValidMoves() {
        validMoves.clear();
        BoardTile currentPosition;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                currentPosition = new BoardTile(i, j);
                if (board.getPositionColor(currentPosition) == ColoredPawn.EMPTY)
                    checkPosition(currentPosition);
            }
        }
    }

    private void checkPosition(BoardTile currentPosition) {
        ArrayList<Direction> directionsWithOppositeColor = findDirectionsWithOppositeColor(currentPosition);
        if (directionsWithOppositeColor.isEmpty()) return;
        ArrayList<Direction> validDirections = computeValidDirections(currentPosition, directionsWithOppositeColor);
        if (validDirections.isEmpty()) return;
        validMoves.add(new ValidMove(currentPosition, validDirections, getCurrentPlayerColor()));
    }

    protected ArrayList<Direction> findDirectionsWithOppositeColor(BoardTile currentPosition) {
        ArrayList<Direction> directionsWithOppositeColor = new ArrayList<>();
        Direction currentDirection;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                currentDirection = new Direction(i, j);
                if (hasTheRightColor(currentPosition, currentDirection))
                    directionsWithOppositeColor.add(currentDirection);
            }
        }
        return directionsWithOppositeColor;
    }

    private boolean hasTheRightColor(BoardTile currentPosition, Direction currentDirection) {
        BoardTile neighbourToCheck = currentPosition.add(currentDirection);
        return neighbourToCheck.isInsideTheBoard() && (board.getPositionColor(neighbourToCheck) == getCurrentPlayerColor().opposite());
    }

    private ArrayList<Direction> computeValidDirections(BoardTile currentPosition, ArrayList<Direction> directionsWithOppositeColor) {
        return directionsWithOppositeColor.stream()
                .filter(currentDirection -> isValidDirection(currentPosition, currentDirection))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isValidDirection(BoardTile currentPosition, Direction currentDirection) {
        BoardTile tileCurrentlyOnCheck = currentPosition.add(currentDirection).add(currentDirection);
        while (tileCurrentlyOnCheck.isInsideTheBoard()) {
            if (board.getPositionColor(tileCurrentlyOnCheck) == ColoredPawn.EMPTY)
                return false;
            if (board.getPositionColor(tileCurrentlyOnCheck) == getCurrentPlayerColor())
                return true;
            tileCurrentlyOnCheck = tileCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }

    protected Optional<ValidMove> isValid(BoardTile chosen) {
        return validMoves.stream().filter(validMove -> validMove.position().equals(chosen)).findAny();
    }

    public Optional<ValidMove> getMove(String input) {
        BoardTile chosen = new BoardTile(input);
        return isValid(chosen);
    }

    public ArrayList<ValidMove> getValidMoves() {
        return new ArrayList<>(this.validMoves);
    }

    public Board getBoard() {
        return board.copy();
    }

    public ColoredPawn getCurrentPlayerColor() {
        return blackToMove ? ColoredPawn.BLACK : ColoredPawn.WHITE;
    }

    public boolean isBlackToMove() {
        return blackToMove;
    }

    public void swapTurn() {
        blackToMove = !blackToMove;
    }


    public boolean isBoardFull() {
        return board.isFull();
    }

    public void applyMoveToBoard(ValidMove move) {
        board.applyMoveToBoard(move);
    }

    public void importBoardFrom(Board board) {
        this.board.importBoardFrom(board);
    }

    public int computeScoreForPlayer(ColoredPawn playerColor) {
        return board.computeScoreForPlayer(playerColor);
    }

    public int numberOfValidMoves() {
        return validMoves.size();
    }

    public void printFinalScores(){ }
}