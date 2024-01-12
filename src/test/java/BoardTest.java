import board.Board;
import board.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void a1isEmptyOnStart() {
        Board board = new Board();
        Pawn a1 = board.getSquareValue(0, 0);
        assertEquals(Pawn.EMPTY, a1);
    }

    @Test
    void startingPosition(){
        Board board = new Board();
        assertEquals(Pawn.WHITE, board.getSquareValue(3, 3));
        assertEquals(Pawn.WHITE, board.getSquareValue(4, 4));
        assertEquals(Pawn.BLACK, board.getSquareValue(3, 4));
        assertEquals(Pawn.BLACK, board.getSquareValue(4, 3));
    }
}
