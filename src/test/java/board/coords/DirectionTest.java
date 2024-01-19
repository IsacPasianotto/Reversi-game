package board.coords;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    void nothingIsNotEqualToSomething() {
        Direction direction = new Direction(-1, 1);
        assertNotEquals(direction, null);
    }

    @Test
    void directionIsRelativeToABoardTile() {
        IllegalArgumentException wrongDirectionFormat = assertThrows(IllegalArgumentException.class, () -> new Direction(5, -1));
        String exceptionMessage = wrongDirectionFormat.getMessage();
        assertEquals("Invalid direction, given x and y should be in range [-1, 1]", exceptionMessage);
    }

}
