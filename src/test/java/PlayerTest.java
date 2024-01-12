import org.junit.jupiter.api.Test;
import player.Coords;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void a1IsZeroZero() {
        Coords coords = new Coords("a1");
        assertEquals(0, coords.getX());
        assertEquals(0, coords.getY());

    }

    @Test
    void inputTooLong() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new Coords("a11")).getMessage();
        assertEquals("Input coordinates should be a 2 characters string, eg. \"a1\"", exceptionMessage);
    }

    @Test
    void inputOutOfRange() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new Coords("a9")).getMessage();
        assertEquals("One or both of the coordinates are out of range", exceptionMessage);
    }

    @Test
    void swappedInput() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new Coords("1a")).getMessage();
        assertEquals("The coordinates should be a letter followed by a number", exceptionMessage);
    }


}

