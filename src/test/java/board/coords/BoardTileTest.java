package board.coords;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTileTest {

    @Test
    void a1IsZeroZero() {
        BoardTile boardTile = new BoardTile("a1");
        assertEquals(0, boardTile.getX());
        assertEquals(0, boardTile.getY());

    }

    @Test
    void zeroZeroIsA1() {
        BoardTile boardTile = new BoardTile(0, 0);
        assertEquals("A1", boardTile.toString());
    }

    @Test
    void inputTooLong() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new BoardTile("a11")).getMessage();
        assertEquals("Input coordinates should be a 2 characters string, eg. \"a1\"", exceptionMessage);
    }

    @Test
    void inputOutOfRange() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new BoardTile("a9")).getMessage();
        assertEquals("One or both of the coordinates are out of range", exceptionMessage);
    }

    @Test
    void swappedInput() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new BoardTile("1a")).getMessage();
        assertEquals("The coordinates should be a letter followed by a number", exceptionMessage);
    }

}

