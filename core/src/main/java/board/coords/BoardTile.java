package board.coords;

import board.Board;

/**
 * This class represents a single tile on the board.
 * It can be created from a string, e.g. "a1", or from two integers, e.g. (0, 0).
 */
public class BoardTile implements Couple {
    private final int x;
    private final int y;

    /**
     * Creates a new BoardTile from the given coordinates.
     * @param x the row coordinate of the tile
     * @param y the column coordinate of the tile
     */
    public BoardTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new BoardTile from the given string.
     * @param inputCoords the string to create the BoardTile from. It should be a 2 characters string, with the first character being a letter and the second a number, e.g. "a1"
     * @throws IllegalArgumentException if the input string is not in the correct format
     */
    public BoardTile(String inputCoords) throws IllegalArgumentException {
        if (inputCoords.length() != 2)
            throw new IllegalArgumentException("Format error: Input coordinates should be a 2 characters string, eg. \"a1\"");
        String inputCoordsLowerCase = inputCoords.toLowerCase();
        char first = inputCoordsLowerCase.charAt(0);
        char second = inputCoordsLowerCase.charAt(1);
        if (second < '0' || second > '9' || first < 'a' || first > 'z')
            throw new IllegalArgumentException("The coordinates should be a letter followed by a number");
        if (first > 'h' || second < '1' || second > '8')
            throw new IllegalArgumentException("One or both of the coordinates are out of range");
        x = Integer.parseInt(String.valueOf(second)) - 1;
        y = first - 'a';
    }
    /**
     * Returns the BoardTile that results from adding the given direction to the current one. The returned BoardTile is a new object, and it's a tile in the neighborhood of the current one.
     * @param other the direction to add
     * @return a new BoardTile object that is the result of adding the given direction to the current one
     */
    public BoardTile add(Direction other) {
        return new BoardTile(x + other.x(), y + other.y());
    }
    /**
     * Check if an object of this class has coordinates that are inside the board hence representing a valid position. This is needed because the <code>add</code> method does can return tiles that do not exist on the real board.
     * @see #add(Direction)
     * @return a new BoardTile object that is the result of adding the given direction to the current one
     */
    public boolean isInsideTheBoard() {
        return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
    }

    /**
     * Check if the current tile is equal to a given one.
     * @param other the tile to compare to
     * @return true if the two tiles are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BoardTile otherBoardTile = (BoardTile) other;
        return x == otherBoardTile.x() && y == otherBoardTile.y();
    }

    /**
     * Returns a string representation of the current tile, in the format "xy", where x is a letter and y is a number.
     * @return a string representation of the current tile
     */
    @Override
    public String toString() {
        String row = "ABCDEFGH";
        String col = "12345678";
        return "" + row.charAt(y) + col.charAt(x);
    }
    /**
     * Returns the row coordinate of the tile.
     * @return the row coordinate of the tile
     */
    public int x() {
        return x;
    }
    /**
     * Returns the column coordinate of the tile.
     * @return the column coordinate of the tile
     */
    public int y() {
        return y;
    }
}
