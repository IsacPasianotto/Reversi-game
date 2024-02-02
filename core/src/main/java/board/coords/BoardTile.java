package board.coords;

import board.Board;

public class BoardTile implements Couple {
    private final int x;
    private final int y;

    public BoardTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public BoardTile add(Direction other) {
        return new BoardTile(x + other.x(), y + other.y());
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BoardTile otherBoardTile = (BoardTile) other;
        return x() == otherBoardTile.x() && y() == otherBoardTile.y();
    }

    @Override
    public String toString() {
        String row = "ABCDEFGH";
        String col = "12345678";
        return "" + row.charAt(y) + col.charAt(x);
    }

    public boolean isInsideTheBoard() {
        return x() >= 0 && x() < Board.BOARD_SIZE && y() >= 0 && y() < Board.BOARD_SIZE;
    }
}
