package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidMovesChecker {
    private final Board board;
    private final ArrayList<ValidMove> validMoves;
    private boolean blackToMove;


    public ValidMovesChecker(Board board) {
        validMoves = new ArrayList<>();
        this.board = board;
        blackToMove = true;
    }

    public ArrayList<ValidMove> getValidMoves() {
        return validMoves;
    }

    public ColoredPawn getCurrentPlayerColor() {
        return blackToMove ? ColoredPawn.BLACK : ColoredPawn.WHITE;
    }

    public ColoredPawn getOppositePlayerColor() {
        return blackToMove ? ColoredPawn.WHITE : ColoredPawn.BLACK;
    }

    public Board getBoard() {
        return board.copy();
    }

    public boolean isBlackToMove() {
        return blackToMove;
    }

    public void swapTurn() {
        blackToMove = !blackToMove;
    }

    public int numberOfValidMoves() {
        return validMoves.size();
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

    public ArrayList<Direction> findDirectionsWithOppositeColor(BoardTile currentPosition) {
        ArrayList<Direction> directionsWithOppositeColor = new ArrayList<>();
        Direction currentDirection;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                currentDirection = new Direction(i, j);
                BoardTile neighbourToCheck = currentPosition.add(currentDirection);
                if (board.isInsideTheBoard(neighbourToCheck)) {
                    ColoredPawn neighbourColor = board.getPositionColor(neighbourToCheck);
                    if (neighbourColor == getOppositePlayerColor())
                        directionsWithOppositeColor.add(currentDirection);
                }
            }
        }
        return directionsWithOppositeColor;
    }

    private ArrayList<Direction> computeValidDirections(BoardTile currentPosition, ArrayList<Direction> directionsWithOppositeColor) {
        ArrayList<Direction> validDirections = new ArrayList<>();
        for (Direction currentDirection : directionsWithOppositeColor) {
            if (isValidDirection(currentPosition, currentDirection))
                validDirections.add(currentDirection);
        }
        return validDirections;
    }

    private boolean isValidDirection(BoardTile currentPosition, Direction currentDirection) {
        BoardTile tileCurrentlyOnCheck = currentPosition.add(currentDirection).add(currentDirection);
        while (board.isInsideTheBoard(tileCurrentlyOnCheck)) {
            if (board.getPositionColor(tileCurrentlyOnCheck) == ColoredPawn.EMPTY)
                return false;
            if (board.getPositionColor(tileCurrentlyOnCheck) == getCurrentPlayerColor())
                return true;
            tileCurrentlyOnCheck = tileCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }

    public Optional<ValidMove> IsValid(BoardTile move) {
        return validMoves.stream().filter(validMove -> validMove.getPosition().equals(move)).findAny();
    }

    public void getInvalidMoveMessage() {
        System.out.println("Invalid move entered. Valid moves are: ");
        String moves = validMoves.stream().map(validMove -> validMove.getPosition() + " ").collect(Collectors.joining());
        System.out.println(moves);
        System.out.print("Enter your move: ");
    }
}