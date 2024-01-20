package positions;

import board.Board;
import board.ColoredPawn;
import board.coords.BoardTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class GamePositions {
    public static Board impossibleToMovePosition() {
        // Hassan 3 – 17 Verstuyft J. (European Grand Prix Ghent 2017)
        // See: https://en.wikipedia.org/wiki/Reversi
        Board board = new Board();
        ArrayList<String> whitePositions = new ArrayList<>(Arrays.asList("e1", "e2", "f2", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "c4", "d4", "e4", "f4", "c5", "d5", "e5"));
        ArrayList<String> blackPositions = new ArrayList<>(Arrays.asList("h3", "h4", "h5"));
        whitePositions.forEach(s -> board.setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        blackPositions.forEach(s -> board.setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
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
        ArrayList<String> whitePositions = new ArrayList<>(Arrays.asList("c3","e5"));
        ArrayList<String> blackPositions = new ArrayList<>(Arrays.asList("d3", "c4", "d4", "e4", "d5"));
        whitePositions.forEach(s -> board.setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        blackPositions.forEach(s -> board.setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
        return board;
    }

    public static Board twoLinesFlippedOneMove () {
        // equivalent to play: C4-C3-D3-C5
        Board board = new Board();
        ArrayList<String> whitePositions = new ArrayList<>(Arrays.asList("c3", "c4", "c5", "d5", "e5"));
        ArrayList<String> blackPositions = new ArrayList<>(Arrays.asList("d3", "d4", "e4"));
        whitePositions.forEach(s -> board.setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        blackPositions.forEach(s -> board.setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
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
