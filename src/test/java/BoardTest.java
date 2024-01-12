import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void a1isEmptyOnStart() {
        Board board = new Board();
        char a1 = board.getSquareValue(0, 0);
        // wrong on purpose to see circleci fail
        assertEquals('X', a1);
    }
}
