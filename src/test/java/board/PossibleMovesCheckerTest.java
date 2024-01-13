package board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import player.Coords;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PossibleMovesCheckerTest {

    @ParameterizedTest
    @MethodSource("provideCoordinatesAndExpectedResults")
    void findDirectionsWithOppositeColor(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(board, true);
        ArrayList<RelativeNeighbour> possiblyValidDirections = possibleMovesChecker.findDirectionsWithOppositeColor(new Coords(inputCoords));
        assertEquals(1, possiblyValidDirections.size());
        assertEquals(expectedX, possiblyValidDirections.get(0).direction.getX());
        assertEquals(expectedY, possiblyValidDirections.get(0).direction.getY());
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
        ArrayList<RelativeNeighbour> possiblyValidDirections = possibleMovesChecker.findDirectionsWithOppositeColor(new Coords(inputCoords));
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
    void getValidMovesOnStart() {
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(new Board(), true);
        ArrayList<Coords> validMoves = possibleMovesChecker.getValidMoves();
        Coords[] expected = {new Coords(2,3), new Coords(3,2), new Coords(4,5), new Coords(5,4)};
        assertEquals(4, validMoves.size());
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i].getX(), validMoves.get(i).getX());
            assertEquals(expected[i].getY(), validMoves.get(i).getY());
        }
    }

}
