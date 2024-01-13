package board;

import board.coords.BoardTile;
import board.coords.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PossibleMovesCheckerTest {

    @ParameterizedTest
    @MethodSource("provideCoordinatesAndExpectedResults")
    void findDirectionsWithOppositeColor(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(board, true);
        ArrayList<Direction> possiblyValidDirections = possibleMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(1, possiblyValidDirections.size());
        assertEquals(expectedX, possiblyValidDirections.get(0).getX());
        assertEquals(expectedY, possiblyValidDirections.get(0).getY());
    }
    private static Stream<Object[]> provideCoordinatesAndExpectedResults() {
        return Stream.of(
                new Object[]{"c3", 1, 1},
                new Object[]{"e6", -1, 0},
                new Object[]{"f5", 0, -1}
        );
    }

    @ParameterizedTest
    @MethodSource("provideEmptyArrayLists")
    void findDirectionsWithOppositeColorImpossible(String inputCoords) {
        Board board = new Board();
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(board, true);
        ArrayList<Direction> possiblyValidDirections = possibleMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(0, possiblyValidDirections.size());
    }
    private static Stream<Object[]> provideEmptyArrayLists() {
        return Stream.of(
                new Object[]{"a1"},
                new Object[]{"h8"},
                new Object[]{"c6"}
        );
    }

    @Test
    void onStartThereAreFourValidMoves() {
        Board board = new Board();
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(board, true);
        ArrayList<ValidMove> validMoves = possibleMovesChecker.getValidMoves();
        assertEquals(4, validMoves.size());
    }

    @ParameterizedTest
    @MethodSource("provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(BoardTile TruePosition, int i) {
        Board board = new Board();
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(board, true);
        ArrayList<ValidMove> validMoves = possibleMovesChecker.getValidMoves();
        assertEquals(TruePosition, validMoves.get(i).getPosition());
    }
    private static Stream<Object[]> provideCoordinatesForValidMovesOnStart() {
        return Stream.of(
                new Object[]{new BoardTile("d3"), 0},
                new Object[]{new BoardTile("c4"), 1},
                new Object[]{new BoardTile("f5"), 2},
                new Object[]{new BoardTile("e6"), 3}
        );
    }

    @ParameterizedTest
    @MethodSource("provideDirectionsForValidMovesOnStart")
    void onStartValidDirectionForValidMovesAreCorrect(ArrayList<Direction> TrueDirections, int i) {
        Board board = new Board();
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(board, true);
        ArrayList<ValidMove> validMoves = possibleMovesChecker.getValidMoves();
        assertEquals(1, validMoves.get(i).getValidDirections().size());
        assertEquals(TrueDirections, validMoves.get(i).getValidDirections());

    }

    private static Stream<Object[]> provideDirectionsForValidMovesOnStart() {
        return Stream.of(
                new Object[]{new ArrayList<Direction>(){{add(new Direction(1, 0));}} , 0},
                new Object[]{new ArrayList<Direction>(){{add(new Direction(0, 1));}} , 1},
                new Object[]{new ArrayList<Direction>(){{add(new Direction(0, -1));}} , 2},
                new Object[]{new ArrayList<Direction>(){{add(new Direction(-1, 0));}} , 3}
        );
    }



}
