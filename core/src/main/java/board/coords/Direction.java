package board.coords;

public class Direction implements Couple {
    private final int x;
    private final int y;

    public Direction(int x, int y) throws IllegalArgumentException {
        if (Math.abs(x) > 1 || Math.abs(y) > 1)
            throw new IllegalArgumentException("Invalid direction, given x and y should be in range [-1, 1]");
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Direction otherDirection = (Direction) other;
        return x == otherDirection.x && y == otherDirection.y;
    }

    // used only for debugging purposes
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
