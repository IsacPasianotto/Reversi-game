package board.coords;

public class Direction implements Couple {
    private final int x;
    private final int y;
    public Direction(int x, int y) throws IllegalArgumentException {
        if ( (x < -1) || (x > 1) || (y < -1) || (y > 1) )
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
        return this.getX() == otherDirection.getX() && this.getY() == otherDirection.getY();
    }

    // used only for debugging purposes
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
