package board.coords;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTileTest {
    @ParameterizedTest
    @MethodSource("positions.BoardTilePositions#getXYBoardTiles")
    void fromXYToName(int x, int y, String tileName) {
        BoardTile tile = new BoardTile(x, y);
        assertEquals(tileName, tile.toString());
    }

    @ParameterizedTest
    @MethodSource("positions.BoardTilePositions#getXYBoardTiles")
    void fromNameToXY(int x, int y, String tileName) {
        BoardTile tile = new BoardTile(tileName);
        assertEquals(x, tile.x());
        assertEquals(y, tile.y());
    }

    @Test
    void inputTooLong() {
        String fakeInput = "A42";
        IllegalArgumentException tooLong = assertThrows(IllegalArgumentException.class, () -> new BoardTile(fakeInput));
        String exceptionMessage = tooLong.getMessage();
        assertEquals("Format error: Input coordinates should be a 2 characters string, eg. \"a1\"", exceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("positions.BoardTilePositions#provideBoardTileOutOfRange")
    void inputOutOfRange(String input) {
        IllegalArgumentException outOfRange = assertThrows(IllegalArgumentException.class, () -> new BoardTile(input));
        String exceptionMessage = outOfRange.getMessage();
        assertEquals("One or both of the coordinates are out of range", exceptionMessage);
    }

    @Test
    void swappedInput() {
        IllegalArgumentException wrongInput = assertThrows(IllegalArgumentException.class, () -> new BoardTile("1a"));
        String exceptionMessage = wrongInput.getMessage();
        assertEquals("The coordinates should be a letter followed by a number", exceptionMessage);
    }
}
