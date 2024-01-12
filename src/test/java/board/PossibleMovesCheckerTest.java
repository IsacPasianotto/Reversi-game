package board;

import org.junit.jupiter.api.Test;
import player.Coords;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PossibleMovesCheckerTest {

    @Test
    void directionToCheckTwoTwoGoesThreeThree() {
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(new Board(), true);
        ArrayList<RelativeNeighbour> intrestingDirections = possibleMovesChecker.directionsToCheck(new Coords(2,2));
        assertEquals(1, intrestingDirections.size());
        assertEquals(3, intrestingDirections.get(0).position.getX());
        assertEquals(3, intrestingDirections.get(0).position.getY());
    }

    @Test
    void directionToCheckFourFiveGoesFourFour() {
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(new Board(), true);
        ArrayList<RelativeNeighbour> intrestingDirections = possibleMovesChecker.directionsToCheck(new Coords(4,5));
        assertEquals(1, intrestingDirections.size());
        assertEquals(4, intrestingDirections.get(0).position.getX());
        assertEquals(4, intrestingDirections.get(0).position.getY());
    }

    @Test
    void directionToCheckZeroZeroGoesEmpty() {
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(new Board(), true);
        ArrayList<RelativeNeighbour> intrestingDirections = possibleMovesChecker.directionsToCheck(new Coords(0,0));
        assertEquals(0, intrestingDirections.size());
    }

    @Test
    void getValidMovesOnStart() {
        PossibleMovesChecker possibleMovesChecker = new PossibleMovesChecker(new Board(), true);
        ArrayList<Coords> validMoves = possibleMovesChecker.getValidMoves();
        // Coords[] expected = {new Coords(2,3), new Coords(3,2), new Coords(4,5), new Coords(5,4)};
        assertEquals(4, validMoves.size());

    }

}
