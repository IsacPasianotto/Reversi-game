package board.coords;

public class Direction implements Couple {
    public Direction(int x, int y){
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

    private final int x;
    private final int y;
}
