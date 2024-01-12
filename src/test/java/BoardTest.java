import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void a1isEmptyOnStart() {
        Board board = new Board();
        char a1 = board.getSquareValue(0, 0);
        // wrong on purpose to see circleci fail
        assertEquals(' ', a1);
    }

    @Test
    void startingPosition(){
        Board board = new Board();
        char d4 = board.getSquareValue(3, 3);
        char d5 = board.getSquareValue(3, 4);
        char e4 = board.getSquareValue(4, 3);
        char e5 = board.getSquareValue(4, 4);
        assertEquals(board.WHITE_SYMBOL, d4);
        assertEquals(board.BLACK_SYMBOL, d5);
        assertEquals(board.BLACK_SYMBOL, e4);
        assertEquals(board.WHITE_SYMBOL, e5);

    }


}
