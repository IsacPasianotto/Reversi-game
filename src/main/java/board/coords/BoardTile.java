package board.coords;

public class BoardTile implements Couple {

    public BoardTile(String inputCoords) throws IllegalArgumentException {
        try {
            if (inputCoords.length() != 2) {
                throw new IllegalArgumentException("Input coordinates should be a 2 characters string, eg. \"a1\"");
            }
            inputCoords = inputCoords.toLowerCase();

            char first = inputCoords.charAt(0);
            char second = inputCoords.charAt(1);

            if (second < '0' || second > '9' || first < 'a' || first > 'z')
                throw new IllegalArgumentException("The coordinates should be a letter followed by a number");
            if (first > 'h' || second < '1' || second > '8')
                throw new IllegalArgumentException("One or both of the coordinates are out of range");

            x = Integer.parseInt(String.valueOf(second)) - 1;
            y = first - 'a';

        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    public BoardTile(int x, int y){
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

    public BoardTile add(Direction other) {
        return new BoardTile(this.x + other.getX(), this.y + other.getY());
    }

    private final int x;
    private final int y;
}
