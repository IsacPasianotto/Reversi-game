package positions;

import board.Board;
import board.Pawn;
import board.coords.BoardTile;

public class GamePositions {

    public static Board impossibleToMovePosition() {
        // Hassan 3 – 17 Verstuyft J. (European Grand Prix Ghent 2017)
        // See: https://en.wikipedia.org/wiki/Reversi
        Board board = new Board();

        board.setPositionValue(new BoardTile("e1"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("e2"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("f2"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("a3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("b3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("c3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("d3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("e3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("f3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("g3"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("c4"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("d4"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("e4"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("f4"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("c5"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("d5"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("e5"), Pawn.WHITE);
        board.setPositionValue(new BoardTile("h3"), Pawn.BLACK);
        board.setPositionValue(new BoardTile("h4"), Pawn.BLACK);
        board.setPositionValue(new BoardTile("h5"), Pawn.BLACK);

        return board;
    }

    public static Board emptyTileBetweenTwoPawns() {
        Board board = new Board();
        board.setPositionValue(new BoardTile("d2"), Pawn.WHITE);
        return board;
    }

}
