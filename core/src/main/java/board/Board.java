package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * A class to represent the game board. The board is a 8x8 grid of ColoredPawn objects, where each cell can be empty or
 * contain a black or white pawn. The board is initialized with 4 pawns in the center, 2 for each player, according to
 * the standard Othello starting position.
 * @see ColoredPawn
 * @see BoardTile
 */
public class Board {

    /**
     * The size of the board, which is a square grid of this size.
     */
    public static final int BOARD_SIZE = 8;
    private final ColoredPawn[][] board;

    /**
     * Creates a new Board object, initializing the board with the standard Othello starting position.
     */
    public Board() {
        board = new ColoredPawn[BOARD_SIZE][BOARD_SIZE];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, ColoredPawn.EMPTY));
        Arrays.asList("d4", "e5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        Arrays.asList("e4", "d5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
    }

    /**
     * Applies a given move to the board, adding a new pawn of the given color to the given position and flipping the
     * pawns in all the possible directions
     * @see ValidMove
     * @param move the move to apply to the board
     */
    public void applyMoveToBoard(ValidMove move) {
        BoardTile startingPosition = move.position();
        ColoredPawn currentColor = move.currentColor();
        move.validDirections().forEach(direction -> flipLineOfPawns(startingPosition, direction, currentColor));
        setPositionColor(startingPosition, currentColor);
    }

    /**
     * Modifies the current board to be the same as the one given as parameter.
     * @param another the board to import from
     */
    public void importBoardFrom(Board another) {
        for (int i = 0; i < BOARD_SIZE; i++)
            System.arraycopy(another.board[i], 0, board[i], 0, BOARD_SIZE);
    }

    /**
     * Creates a new Board object that is a copy of the current one.
     * @return a new Board object equal to the current one
     */
    public Board copy() {
        Board newBoard = new Board();
        newBoard.importBoardFrom(this);
        return newBoard;
    }

    /**
     * Returns the value of the enum ColoredPawn that is in the given position.
     * @see ColoredPawn
     * @param position the position to check
     * @return the value of the enum ColoredPawn that is in the given position
     */
    public ColoredPawn getPositionColor(BoardTile position) {
        return board[position.x()][position.y()];
    }

    /**
     * Returns the value of the enum ColoredPawn that is in the given position.
     * @see ColoredPawn
     * @param x the row coordinate of the position
     * @param y the column coordinate of the position
     * @return the value of the enum ColoredPawn that is in the given position
     */
    public ColoredPawn getPositionColor(int x, int y) {
        return board[x][y];
    }

    /**
     * Sets the value of the given position to the given color.
     * @param position the position to set
     * @param color the color to set
     */
    public void setPositionColor(BoardTile position, ColoredPawn color) {
        board[position.x()][position.y()] = color;
    }

    /**
     * check if the current object Board is equal to a given one.
     * @param other the board to compare to
     * @return true if the two Board objects represent the same board, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Board otherBoard)) return false;
        return Arrays.deepEquals(board, otherBoard.board);
    }

    /**
     * Return the number of pawns of the given color that are currently on the board.
     * @see ColoredPawn
     * @param player the color of the pawns to count
     * @return the count of pawns of the given color that are currently on the board
     */
    public int computeScoreForPlayer(ColoredPawn player) {
        return Arrays.stream(board).mapToInt(row -> (int) Arrays.stream(row).filter(pawn -> pawn == player).count()).sum();
    }

    /**
     * Returns a string representation of the current board, with the pawns and the score of the two players.
     * @return a string representation of the current board
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++) {
            result.append(" ").append(i + 1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++)
                result.append("  ").append(getPositionColor(i, j)).append((j == BOARD_SIZE - 1) ? "  ┃" : "  ╎");
            result.append((i == BOARD_SIZE - 1) ? "\n" : "\n   ┣-----+-----+-----+-----+-----+-----+-----+-----┫\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        result.append("Current score: ")
                .append(ColoredPawn.WHITE).append(" ").append(computeScoreForPlayer(ColoredPawn.WHITE))
                .append(" - ")
                .append(computeScoreForPlayer(ColoredPawn.BLACK)).append(" ").append(ColoredPawn.BLACK);
        return result.toString();
    }

    private void flipLineOfPawns(BoardTile startingPosition, Direction direction, ColoredPawn currentPlayerColor) {
        Stream.iterate(startingPosition
                        .add(direction), position -> getPositionColor(position) != currentPlayerColor, position -> position.add(direction))
                        .forEach(position -> setPositionColor(position, currentPlayerColor));
    }
}


