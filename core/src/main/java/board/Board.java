package board;


import board.coords.BoardTile;
import board.coords.Direction;

import java.util.Arrays;
import java.util.stream.Stream;

public class Board {
    /**
     * The size of the board (8x8).
     */
    public static final int BOARD_SIZE = 8;
    /**
     * The board as a 2D array of ColoredPawn objects.
     */
    private final ColoredPawn[][] board;


    /**
     * Initializes the board with the starting position of the game.
     */
    public Board() {
        board = new ColoredPawn[BOARD_SIZE][BOARD_SIZE];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, ColoredPawn.EMPTY));
        Arrays.asList("d4", "e5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        Arrays.asList("e4", "d5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
    }

    /**
     * Returns the number of pawns of the given color on the board.
     * @param move the move to apply to the board
     */
    public void applyMoveToBoard(ValidMove move) {
        BoardTile startingPosition = move.position();
        ColoredPawn currentColor = move.currentColor();
        move.validDirections().forEach(direction -> flipLineOfPawns(startingPosition, direction, currentColor));
        setPositionColor(startingPosition, currentColor);
    }

    /**
     * Flips the pawns in the given direction starting from the given position.
     * @param startingPosition the position from which to start flipping
     * @param direction the direction in which to flip the pawns
     * @param currentPlayerColor the color of the player who is making the move
     */
    private void flipLineOfPawns(BoardTile startingPosition, Direction direction, ColoredPawn currentPlayerColor) {
        Stream.iterate(startingPosition
                        .add(direction), position -> getPositionColor(position) != currentPlayerColor, position -> position.add(direction))
                .forEach(position -> setPositionColor(position, currentPlayerColor));
    }

    /**
     * Returns the number of pawns of the given color on the board.
     * @param another the board from which to import the position
     */
    public void importBoardFrom(Board another) {
        for (int i = 0; i < BOARD_SIZE; i++)
            System.arraycopy(another.board[i], 0, board[i], 0, BOARD_SIZE);
    }

    /**
     * Returns a copy of the board.
     * @return a copy of the board as a new object
     */
    public Board copy() {
        Board newBoard = new Board();
        newBoard.importBoardFrom(this);
        return newBoard;
    }

    /**
     * Returns the color of the pawn at the given position.
     * @param position the position of the pawn as a BoardTile object
     * @return the color of the pawn at the given position
     */
    public ColoredPawn getPositionColor(BoardTile position) {
        return board[position.x()][position.y()];
    }

    /**
     * Returns the color of the pawn at the given position.
     * @param x the row coordinate of the position
     * @param y the column coordinate of the position
     * @return the color of the pawn at the given position
     */
    public ColoredPawn getPositionColor(int x, int y) {
        return board[x][y];
    }

    /**
     * Sets the color of the pawn at the given position
     * @param position the position of the pawn as a BoardTile object
     * @param color the color to set the pawn to
     */
    public void setPositionColor(BoardTile position, ColoredPawn color) {
        board[position.x()][position.y()] = color;
    }

    /**
     * checks if a given Board is equal to the current one
     * @param other the board to compare to
     * @return true if the two boards are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Board otherBoard)) return false;
        return Arrays.deepEquals(board, otherBoard.board);
    }

    /**
     * Returns the current score of the given player.
     * @param player the player for which to compute the score
     * @return the score of the given player (the number of pawns of the given color on the board)
     */
    public int computeScoreForPlayer(ColoredPawn player) {
        return Arrays.stream(board).mapToInt(row -> (int) Arrays.stream(row).filter(pawn -> pawn == player).count()).sum();
    }

    /**
     * Returns a string representation of the board, used to print the board to the console.
     * @return a string representation of the board
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
}


