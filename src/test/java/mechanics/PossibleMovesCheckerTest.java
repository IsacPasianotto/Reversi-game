package mechanics;

import board.Board;
import board.ValidMove;
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
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> possiblyValidDirections = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
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
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> possiblyValidDirections = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
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
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        int nMovesFound = validMovesChecker.getValidMoves().size();
        assertEquals(4, nMovesFound);
    }

    @ParameterizedTest
    @MethodSource("provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(BoardTile TruePosition, int i) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove ithValidMove = validMovesChecker.getValidMoves().get(i);
        assertEquals(TruePosition, ithValidMove.getPosition());
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
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove ithValidMove = validMovesChecker.getValidMoves().get(i);
        ArrayList<Direction> validDirections = ithValidMove.getValidDirections();
        assertEquals(1, validDirections.size());
        assertEquals(TrueDirections, validDirections);
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
