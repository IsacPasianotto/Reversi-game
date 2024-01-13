package player;

import org.junit.jupiter.api.Test;
import board.coords.BoardTile;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void a1IsZeroZero() {
        BoardTile boardTile = new BoardTile("a1");
        assertEquals(0, boardTile.getX());
        assertEquals(0, boardTile.getY());

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

