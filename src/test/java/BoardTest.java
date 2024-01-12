import board.Board;
import board.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void a1isEmptyOnStart() {
        Board board = new Board();
        Pawn a1 = board.getPositionValue(0, 0);
        assertEquals(Pawn.EMPTY, a1);
    }

    @Test
    void startingPosition(){
        Board board = new Board();
        assertEquals(Pawn.WHITE, board.getPositionValue(3, 3));
        assertEquals(Pawn.WHITE, board.getPositionValue(4, 4));
        assertEquals(Pawn.BLACK, board.getPositionValue(3, 4));
        assertEquals(Pawn.BLACK, board.getPositionValue(4, 3));
    }
}
