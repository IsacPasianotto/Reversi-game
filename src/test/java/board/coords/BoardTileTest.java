package board.coords;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTileTest {
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
        assertEquals(x, tile.getX());
        assertEquals(y, tile.getY());
    }

    @Test
    void inputTooLong() {
        String fakeInput = "A42";
        IllegalArgumentException tooLong = assertThrows(IllegalArgumentException.class, () -> new BoardTile(fakeInput));
        String exceptionMessage = tooLong.getMessage();
        assertEquals("Input coordinates should be a 2 characters string, eg. \"a1\"", exceptionMessage);
    }

    private static Stream<String> provideBoardTileOutOfRange() { return Stream.of("a0", "a9", "i1", "i9");  }

    @ParameterizedTest
    @MethodSource("provideBoardTileOutOfRange")
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

