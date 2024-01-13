package player;

public class Direction implements Couple{
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
    private final int x;
    private final int y;
}
