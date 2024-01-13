package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

public class PossibleMovesChecker {

    private final Board BOARD;
    private final boolean BLACK_TO_MOVE;
    public PossibleMovesChecker(Board board, boolean blackToMove) {
        this.BOARD = board;
        this.BLACK_TO_MOVE = blackToMove;
    }

    public ArrayList<ValidMove> getValidMoves() {
        ArrayList<ValidMove> validMoves = new ArrayList<>();
        for (int i = 0; i < BOARD.BOARD_SIZE; i++){
            for (int j = 0; j < BOARD.BOARD_SIZE; j++) {
                BoardTile currentBoardTile = new BoardTile(i, j);
                checkBoardTile(i, j, currentBoardTile, validMoves);
            }
        }
        return validMoves;
    }

    private void checkBoardTile(int i, int j, BoardTile currentBoardTile, ArrayList<ValidMove> validMoves) {
        if (BOARD.getPositionValue(i, j) == Pawn.EMPTY) {
            ArrayList<Direction> possiblyValidDirections = findDirectionsWithOppositeColor(currentBoardTile);
            if (!possiblyValidDirections.isEmpty()) {
                ArrayList<Direction> validDirections = computeValidDirections(possiblyValidDirections, currentBoardTile);
                if (!validDirections.isEmpty())
                    validMoves.add(new ValidMove(currentBoardTile, validDirections));
            }
        }
    }

    public ArrayList<Direction> findDirectionsWithOppositeColor(BoardTile currentBoardTile) {
        ArrayList<Direction> possiblyValidDirections = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    BoardTile neighbourToCheck = new BoardTile(currentBoardTile.getX() + i, currentBoardTile.getY() + j);
                    Pawn neighbourPawn = BOARD.getPositionValue(neighbourToCheck);
                    if ( (neighbourPawn == Pawn.BLACK && !BLACK_TO_MOVE) || (neighbourPawn == Pawn.WHITE && BLACK_TO_MOVE))
                        possiblyValidDirections.add(new Direction(i, j));
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
        while (pointCurrentlyOnCheck.getX() >= 0 && pointCurrentlyOnCheck.getX() < BOARD.BOARD_SIZE && pointCurrentlyOnCheck.getY() >= 0 && pointCurrentlyOnCheck.getY() < BOARD.BOARD_SIZE) {
            Pawn pawnCurrentlyOnCheck = BOARD.getPositionValue(pointCurrentlyOnCheck);
            if ( (pawnCurrentlyOnCheck == Pawn.BLACK && BLACK_TO_MOVE) || (pawnCurrentlyOnCheck == Pawn.WHITE && !BLACK_TO_MOVE) )
                return true;
            pointCurrentlyOnCheck=pointCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }
}