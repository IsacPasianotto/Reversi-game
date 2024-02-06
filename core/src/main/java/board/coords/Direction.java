package board.coords;

/**
 * Record class to represent a direction on the board. Directions are relative to a position contained in a BoardTile,
 * hence they are represented by a couple of integers (x, y) where x and y are in the range [-1, 1].
 * @see BoardTile
 * @see Couple
 * @param x the row coordinate of the direction
 * @param y the column coordinate of the direction
 */
public record Direction(int x, int y) implements Couple {
    /**
     * Creates a new Direction from the given coordinates.
     * @param x the row coordinate of the direction
     * @param y the column coordinate of the direction
     * @throws IllegalArgumentException if the given x or y are not in the range [-1, 1]
     */
    public Direction {
        if (Math.abs(x) > 1 || Math.abs(y) > 1)
            throw new IllegalArgumentException("Invalid direction, given x and y should be in range [-1, 1]");
    }

    /**
     * Returns a string representation of the current direction.
     * @return a string representation of the current direction
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
