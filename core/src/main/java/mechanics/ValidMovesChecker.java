package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;
import java.util.Optional;

public class ValidMovesChecker{
    final Board board;
    private final ArrayList<ValidMove> validMoves;
    protected boolean blackToMove;


    public ValidMovesChecker(Board board) {
        this.validMoves = new ArrayList<>();
        this.board = board;
        this.blackToMove = true;
    }

    public ArrayList<ValidMove> getValidMoves() {
        return validMoves;
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
    public Optional<ValidMove> IsValid(BoardTile move) {
        for (ValidMove validMove : validMoves) {
            if (validMove.getPosition().equals(move))
                return Optional.of(validMove);
        }
        return Optional.empty();
        //return validMoves.stream().findAny().filter(validMove -> validMove.getPosition().equals(move));
    }

    public ColoredPawn getCurrentPlayerColor(){
        return blackToMove ? ColoredPawn.BLACK : ColoredPawn.WHITE;
    }
    public ColoredPawn getOppositePlayerColor(){
        return blackToMove ? ColoredPawn.WHITE : ColoredPawn.BLACK;
    }

    public void computeValidMoves() {
        this.validMoves.clear();
        for (int i = 0; i < Board.BOARD_SIZE; i++){
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                BoardTile currentPosition = new BoardTile(i, j);
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
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Direction currentDirection = new Direction(i, j);
                try {
                    BoardTile neighbourToCheck = currentPosition.add(currentDirection);
                    ColoredPawn neighbourColor = board.getPositionColor(neighbourToCheck);
                    if (neighbourColor == getOppositePlayerColor())
                        directionsWithOppositeColor.add(currentDirection);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Edge of the board, skip the direction
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
        while (tileCurrentlyOnCheck.isInsideTheBoard()) {
            if (board.getPositionColor(tileCurrentlyOnCheck) == ColoredPawn.EMPTY)
                return false;
            if (board.getPositionColor(tileCurrentlyOnCheck) == getCurrentPlayerColor())
                return true;
            tileCurrentlyOnCheck = tileCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }

    public String printValidMoves() {
        StringBuilder validMoves = new StringBuilder();
        for (ValidMove validMove : getValidMoves()) {
            validMoves.append(validMove.getPosition()).append(" ");
        }
        return validMoves.toString();
    }

    public Board getBoard() {
        return board;
    }
}