package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

public class PossibleMovesChecker {

    private Board board;


    private ArrayList<ValidMove> validMoves;

    public PossibleMovesChecker(Board board) {
        this.board = board;
        this.validMoves = new ArrayList<>();
    }

    public void computeValidMoves() {
        this.validMoves.clear();
        for (int i = 0; i < board.BOARD_SIZE; i++){
            for (int j = 0; j < board.BOARD_SIZE; j++) {
                BoardTile currentBoardTile = new BoardTile(i, j);
                checkBoardTile(i, j, currentBoardTile, this.validMoves);
            }
        }
    }

    private void checkBoardTile(int i, int j, BoardTile currentBoardTile, ArrayList<ValidMove> validMoves) {
        if (board.getPositionValue(i, j) == Pawn.EMPTY) {
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
                    Pawn neighbourPawn = board.getPositionValue(neighbourToCheck);
                    if ( (neighbourPawn == Pawn.BLACK && !(board.isBlackToMove())) || (neighbourPawn == Pawn.WHITE && board.isBlackToMove()))
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
        while (pointCurrentlyOnCheck.getX() >= 0 && pointCurrentlyOnCheck.getX() < board.BOARD_SIZE && pointCurrentlyOnCheck.getY() >= 0 && pointCurrentlyOnCheck.getY() < board.BOARD_SIZE) {
            Pawn pawnCurrentlyOnCheck = board.getPositionValue(pointCurrentlyOnCheck);
            if ( (pawnCurrentlyOnCheck == Pawn.BLACK && board.isBlackToMove()) || (pawnCurrentlyOnCheck == Pawn.WHITE && !(board.isBlackToMove()) ) )
                return true;
            pointCurrentlyOnCheck=pointCurrentlyOnCheck.add(currentDirection);
        }
        return false;
    }

    public ArrayList<ValidMove> getValidMoves() {
        return validMoves;
    }

}