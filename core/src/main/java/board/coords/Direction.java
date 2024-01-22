package board.coords;

public record Direction(int x, int y) implements Couple {
    public Direction {
        if (Math.abs(x) > 1 || Math.abs(y) > 1)
            throw new IllegalArgumentException("Invalid direction, given x and y should be in range [-1, 1]");
    }

    // used only for debugging purposes
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
