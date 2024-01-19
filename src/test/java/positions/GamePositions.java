package positions;

import board.Board;
import board.ColoredPawn;
import board.coords.BoardTile;

import java.util.stream.Stream;

public class GamePositions {

    public static Board impossibleToMovePosition() {
        // Hassan 3 – 17 Verstuyft J. (European Grand Prix Ghent 2017)
        // See: https://en.wikipedia.org/wiki/Reversi
        Board board = new Board();

        board.setPositionColor(new BoardTile("e1"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("e2"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("f2"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("a3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("b3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("c3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("d3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("e3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("f3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("g3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("c4"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("d4"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("e4"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("f4"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("c5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("d5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("e5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("h3"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("h4"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("h5"), ColoredPawn.BLACK);

        return board;
    }

    public static Board emptyTileBetweenTwoPawns() {
        Board board = new Board();
        board.setPositionColor(new BoardTile("d2"), ColoredPawn.WHITE);
        return board;
    }

    public static Board thereAreTwoFlippableLinesInOneMove(){
      // equivalent to play: C4-C3-D3
        Board board = new Board();
        board.setPositionColor(new BoardTile("c3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("e5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("d3"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("c4"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("d4"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("e4"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("d5"), ColoredPawn.BLACK);
        return board;
    }

    public static Board twoLinesFlippedOneMove () {
        // equivalent to play: C4-C3-D3-C5
        Board board = new Board();
        board.setPositionColor(new BoardTile("c3"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("c4"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("c5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("d5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("e5"), ColoredPawn.WHITE);
        board.setPositionColor(new BoardTile("d3"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("d4"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("e4"), ColoredPawn.BLACK);
        return board;
    }

    public static Board d3IsPlayed () {
        Board board = new Board();
        board.setPositionColor(new BoardTile("d3"), ColoredPawn.BLACK);
        board.setPositionColor(new BoardTile("d4"), ColoredPawn.BLACK);
        return board;
    }

    public static Stream<Board> getAllPositions() {
        return Stream.of(
                new Board(),
                emptyTileBetweenTwoPawns(),
                thereAreTwoFlippableLinesInOneMove(),
                twoLinesFlippedOneMove(),
                d3IsPlayed()
        );
    }

    public static Stream<Object> someScores() {
        return Stream.of(
                new Object[]{new Board(), 2, 2},
                new Object[]{impossibleToMovePosition(), 3, 17},
                new Object[]{thereAreTwoFlippableLinesInOneMove(), 5, 2},
                new Object[]{twoLinesFlippedOneMove(), 3, 5},
                new Object[]{d3IsPlayed(), 4, 1}
        );
    }
}
