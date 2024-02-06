package board.coords;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {
    @Test
    void validDirection() {
        assertDoesNotThrow(() -> new Direction(-1, 1));
    }

    @Test
    void invalidDirection() {
        IllegalArgumentException wrongDirectionFormat = assertThrows(IllegalArgumentException.class, () -> new Direction(5, -1));
        String exceptionMessage = wrongDirectionFormat.getMessage();
        assertEquals("Invalid direction, given x and y should be in range [-1, 1]", exceptionMessage);
    }
}
