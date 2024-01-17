package mechanics;

import board.Board;
import board.Pawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;
import java.util.Optional;

public class ValidMovesChecker {
    private Board board;
    private ArrayList<ValidMove> validMoves;

    public ValidMovesChecker(Board board) {
        this.board = board;
        this.validMoves = new ArrayList<>();
    }

    public ArrayList<ValidMove> getValidMoves() {
        return validMoves;
    }

    public Optional<ValidMove> IsValid(BoardTile move) {
        return validMoves.stream().findAny().filter(validMove -> validMove.getPosition().equals(move));
//        for (ValidMove validMove : validMoves) {
//            if (validMove.getPosition().equals(move))
//                return Optional.of(validMove);
//        }
//        return Optional.empty();
    }

    public void computeValidMoves() {
        this.validMoves.clear();
        for (int i = 0; i < Board.BOARD_SIZE; i++){
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                BoardTile currentBoardTile = new BoardTile(i, j);
                if (board.getPositionValue(currentBoardTile) == Pawn.EMPTY) {
                    checkBoardTile(currentBoardTile);
                }
            }
        }
    }

    private void checkBoardTile(BoardTile currentBoardTile) {
        ArrayList<Direction> possiblyValidDirections = findDirectionsWithOppositeColor(currentBoardTile);
        if (possiblyValidDirections.isEmpty()) { return; }
        ArrayList<Direction> validDirections = computeValidDirections(possiblyValidDirections, currentBoardTile);
        if (validDirections.isEmpty()) { return; }
        validMoves.add(new ValidMove(currentBoardTile, validDirections));
    }

    public ArrayList<Direction> findDirectionsWithOppositeColor(BoardTile currentBoardTile) {
        ArrayList<Direction> possiblyValidDirections = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Direction currentDirection = new Direction(i, j);
                try {
                    BoardTile neighbourToCheck = currentBoardTile.add(currentDirection);
                    Pawn neighbourPawn = board.getPositionValue(neighbourToCheck);
                    if (neighbourPawn == board.getCurrentOpponent())
                        possiblyValidDirections.add(currentDirection);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }
        return possiblyValidDirections;
    }

    private ArrayList<Direction> computeValidDirections(ArrayList<Direction> possiblyValidDirections, BoardTile currentBoardTile) {
        ArrayList<Direction> validDirections = new ArrayList<>();
        for (Direction currentDirection : possiblyValidDirections) {
            if (isValidDirection(currentBoardTile, currentDirection))
                validDirections.add(currentDirection);
        }
        return validDirections;
    }

    private boolean isValidDirection(BoardTile currentBoardTile, Direction currentDirection) {
        BoardTile pointCurrentlyOnCheck= currentBoardTile.add(currentDirection).add(currentDirection);
        while (pointCurrentlyOnCheck.isInsideTheBoard()) {
            if (board.getPositionValue(pointCurrentlyOnCheck) == Pawn.EMPTY)
                return false;
            if (board.getPositionValue(pointCurrentlyOnCheck) == board.getCurrentPlayer())
                return true;
            pointCurrentlyOnCheck = pointCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }

    public void printErrorMessage() {
        StringBuilder validMoves = new StringBuilder("Invalid move entered.\nValid moves are: ");
        for (ValidMove validMove : getValidMoves()) {
            validMoves.append(validMove.getPosition()).append(" ");
        }
        System.out.println(validMoves);
    }

    public Board getBoard() {
        return board;
    }
}