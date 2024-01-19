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
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new BoardTile(fakeInput)).getMessage();
        assertEquals("Input coordinates should be a 2 characters string, eg. \"a1\"", exceptionMessage);
    }

    private static Stream<String> provideBoardTileOutOfRange() { return Stream.of("a0", "a9", "i1", "i9");  }

    @ParameterizedTest
    @MethodSource("provideBoardTileOutOfRange")
    void inputOutOfRange(String input) {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new BoardTile(input)).getMessage();
        assertEquals("One or both of the coordinates are out of range", exceptionMessage);
    }

    @Test
    void swappedInput() {
        String exceptionMessage = assertThrows(IllegalArgumentException.class, () -> new BoardTile("1a")).getMessage();
        assertEquals("The coordinates should be a letter followed by a number", exceptionMessage);
    }

}

