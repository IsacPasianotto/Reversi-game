package board;

import player.Coords;
import player.Direction;

import java.util.ArrayList;

public class PossibleMovesChecker {

    private final Board BOARD;
    private final boolean BLACK_TO_MOVE;

    public PossibleMovesChecker(Board board, boolean blackToMove) {
        this.BOARD = board;
        this.BLACK_TO_MOVE = blackToMove;
    }

    public ArrayList<Coords> getValidMoves() {
        ArrayList<Coords> validMoves = new ArrayList<>();
        for (int i = 0; i < BOARD.BOARD_SIZE; i++){
            for (int j = 0; j < BOARD.BOARD_SIZE; j++) {
                Coords currentTarget = new Coords(i, j);
                if (BOARD.board[i][j] == Pawn.EMPTY) {
                    ArrayList<Direction> possibleMoves = findDirectionsWithOppositeColor(currentTarget);

                    if (!possibleMoves.isEmpty()) {
                        // check if at least in one direction there is pawn of the player's color after a pawn of the opposite color
                        testAllValidDirections(possibleMoves, validMoves, currentTarget);
                    }
                }
            }
        }
        return validMoves;
    }

    public ArrayList<Direction> findDirectionsWithOppositeColor(Coords currentTarget) {
        ArrayList<Direction> possibleMoves = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    Coords neighbourToCheck = new Coords(currentTarget.getX() + i, currentTarget.getY() + j);
                    Pawn neighbourPawn = BOARD.getPositionValue(neighbourToCheck);
                    if ( (neighbourPawn == Pawn.BLACK && !BLACK_TO_MOVE) || (neighbourPawn == Pawn.WHITE && BLACK_TO_MOVE))
                        possibleMoves.add(new Direction(i, j));
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }
        return possibleMoves;
    }

    private void testAllValidDirections(ArrayList<Direction> possibleMoves, ArrayList<Coords> validMoves, Coords currentTarget) {
        for (Direction currentDirection : possibleMoves)
            followDirection(validMoves, currentTarget, currentDirection);
    }

    private void followDirection(ArrayList<Coords> validMoves, Coords currentTarget, Direction currentDirection) {
        Coords pointCurrentlyOnCheck= currentTarget.add(currentDirection).add(currentDirection);
        while (pointCurrentlyOnCheck.getX() >= 0 && pointCurrentlyOnCheck.getX() < BOARD.BOARD_SIZE && pointCurrentlyOnCheck.getY() >= 0 && pointCurrentlyOnCheck.getY() < BOARD.BOARD_SIZE) {
            Pawn pawnCurrentlyOnCheck = BOARD.getPositionValue(pointCurrentlyOnCheck);
            if ((pawnCurrentlyOnCheck == Pawn.BLACK && BLACK_TO_MOVE) || (pawnCurrentlyOnCheck == Pawn.WHITE && !BLACK_TO_MOVE))
                validMoves.add(currentTarget);
            pointCurrentlyOnCheck=pointCurrentlyOnCheck.add(currentDirection);
        }
    }
}