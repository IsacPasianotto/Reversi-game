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
                Coords center = new Coords(i, j);
                if (BOARD.board[i][j] == Pawn.EMPTY) {
                    ArrayList<RelativeNeighbour> dirToCheck = directionsToCheck(center);
                    if (!dirToCheck.isEmpty()) {
                        // check if at least in one direction there is pawn of the player's color after a pawn of the opposite color
                        testAllDirections(dirToCheck, validMoves, center);
                    }
                }
            }
        }
        return validMoves;
    }

    // given an input point, returns all the neighbours of that point which have a different color wrt the current player's color
    public ArrayList<RelativeNeighbour> directionsToCheck(Coords center) {
        ArrayList<RelativeNeighbour> possibleMoves = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (i == 0 && j == 0)
                        continue;
                    Coords toCheck = new Coords(center.getX() + i, center.getY() + j);
                    if (BOARD.board[toCheck.getX()][toCheck.getY()] == Pawn.EMPTY)
                        continue;
                    if (BOARD.board[toCheck.getX()][toCheck.getY()] == Pawn.BLACK && BLACK_TO_MOVE)
                        continue;
                    if (BOARD.board[toCheck.getX()][toCheck.getY()] == Pawn.WHITE && !BLACK_TO_MOVE)
                        continue;
                    Coords position = new Coords(i, j);
                    RelativeNeighbour valid = new RelativeNeighbour(BOARD, position, toCheck);
                    possibleMoves.add(valid);
                } catch (Exception e) {
                    // Do nothing, just skip the exception
                }
            }
        }
        return possibleMoves;
    }

    private void testAllDirections(ArrayList<RelativeNeighbour> possibleMoves, ArrayList<Coords> validMoves, Coords center) {
        for (RelativeNeighbour neighbour : possibleMoves) {
            int xStart = neighbour.position.getX() - neighbour.startingPoint.getX();
            int yStart = neighbour.position.getY() - neighbour.startingPoint.getY();
            int xDirection = neighbour.startingPoint.getX();
            int yDirection = neighbour.startingPoint.getY();
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