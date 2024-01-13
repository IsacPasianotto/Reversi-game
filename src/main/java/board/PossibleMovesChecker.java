package board;

import player.Coords;

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
                    ArrayList<RelativeNeighbour> dirToCheck = findDirectionsWithOppositeColor(currentTarget);
                    if (!dirToCheck.isEmpty()) {
                        // check if at least in one direction there is pawn of the player's color after a pawn of the opposite color
                        testAllDirections(dirToCheck, validMoves, currentTarget);
                    }
                }
            }
        }
        return validMoves;
    }

    // given an input point, returns all the neighbours of that point which have a different color wrt the current player's color
    public ArrayList<RelativeNeighbour> findDirectionsWithOppositeColor(Coords currentTarget) {
        ArrayList<RelativeNeighbour> possibleMoves = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    Coords neighbourToCheck = new Coords(currentTarget.getX() + i, currentTarget.getY() + j);
                    Pawn neighbourPawn = BOARD.getPositionValue(neighbourToCheck);
                    if ( (neighbourPawn == Pawn.BLACK && !BLACK_TO_MOVE) ||
                            (neighbourPawn == Pawn.WHITE && BLACK_TO_MOVE)
                    ) {
                        Coords validDirection = new Coords(i, j);
                        RelativeNeighbour valid = new RelativeNeighbour(BOARD, currentTarget, validDirection);
                        System.out.println("Created a RelativeNeighbour object:");
                        System.out.println("startingPoint: " + valid.startingPoint.getX() + " " + valid.startingPoint.getY());
                        System.out.println("direction: " + valid.direction.getX() + " " + valid.direction.getY());
                        possibleMoves.add(valid);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }
        return possibleMoves;
    }

    private void testAllDirections(ArrayList<RelativeNeighbour> possibleMoves, ArrayList<Coords> validMoves, Coords center) {
        for (RelativeNeighbour neighbour : possibleMoves) {
            int xStart = neighbour.direction.getX() - neighbour.startingPoint.getX();
            int yStart = neighbour.direction.getY() - neighbour.startingPoint.getY();
            int xDirection = neighbour.startingPoint.getX();
            int yDirection = neighbour.startingPoint.getY();
            System.out.println("-----------------------------------------");
            System.out.println("xStart: " + xStart + " yStart: " + yStart);
            System.out.println("xDirection: " + xDirection + " yDirection: " + yDirection);

            followDirection(validMoves, center, xStart, yStart, xDirection, yDirection);
        }
    }

    private void followDirection(ArrayList<Coords> validMoves, Coords center, int xStart, int yStart, int xDirection, int yDirection) {
        while (xStart >= 0 && xStart < BOARD.BOARD_SIZE && yStart >= 0 && yStart < BOARD.BOARD_SIZE) {
            xStart += xDirection;
            yStart += yDirection;
            try {
                if (BOARD.board[xStart][yStart] == Pawn.EMPTY)
                    break;
                if (BOARD.board[xStart][yStart] == Pawn.BLACK && BLACK_TO_MOVE){
                    validMoves.add(center);
                    break;
                }
                if (BOARD.board[xStart][yStart] == Pawn.WHITE && !BLACK_TO_MOVE) {
                    validMoves.add(center);
                    break;
                }
            } catch (Exception e) {
                // Do nothing, just skip the exception
            }
        }
    }
}