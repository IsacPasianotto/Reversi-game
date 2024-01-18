package board;

import board.coords.BoardTile;
import board.coords.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static positions.GamePositions.*;

public class BoardTest {

    @ParameterizedTest
    @MethodSource("positions.BoardTilePositions#emptyBoardTilesOnStart")
    void emptyBoardTilesOnStart(String tileName) {
        Board board = new Board();
        BoardTile tile = new BoardTile(tileName);
        Pawn pawnTile = board.getPositionValue(tile.getX(), tile.getY());
        assertEquals(Pawn.EMPTY, pawnTile);
    }

    @Test
    void startingPosition(){
        Board board = new Board();
        assertEquals(Pawn.WHITE, board.getPositionValue(3, 3));
        assertEquals(Pawn.WHITE, board.getPositionValue(4, 4));
        assertEquals(Pawn.BLACK, board.getPositionValue(3, 4));
        assertEquals(Pawn.BLACK, board.getPositionValue(4, 3));
    }

    @Test
    void blackToMoveOnStart(){
        Board board = new Board();
        assertTrue(board.isBlackToMove());
    }

    @Test
    void turnChangesAfterMove(){
        Board board = new Board();
        assertTrue(board.isBlackToMove());
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }});
        board.applyMoveToBoard(c4);
        assertFalse(board.isBlackToMove());
    }

    @Test
    void currentPlayerIsUpdated(){
        Board board = new Board();
        assertEquals(Pawn.BLACK, board.getCurrentPlayer());
        assertEquals(Pawn.WHITE, board.getCurrentOpponent());
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }});
        board.applyMoveToBoard(c4);
        assertEquals(Pawn.WHITE, board.getCurrentPlayer());
        assertEquals(Pawn.BLACK, board.getCurrentOpponent());
    }

    @Test
    void swapAlongAllDirections() {
        Board board = twoLinesFlippableOneMove();
        board.swapTurn();
        ArrayList<Direction> directions = new ArrayList<>() {{
            add(new Direction(-1, 0));
            add(new Direction(0, 1));
        }};
        ValidMove move = new ValidMove(new BoardTile("c5"), directions);
        board.applyMoveToBoard(move);
        Board expectedBoard = twoLinesFlippedOneMove();
        assertTrue(board.hasTheSamePositionOf(expectedBoard));
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#someScores")
    void scoreIsCorrect(Board board, int blackScore, int whiteScore) {
        assertEquals(blackScore, board.computeScoreForPlayer(Pawn.BLACK));
        assertEquals(whiteScore, board.computeScoreForPlayer(Pawn.WHITE));
    }

    @Test
    void copyFunctionsWorks() {
        Board board = impossibleToMovePosition();
        Board copiedBoard = board.copy();
        assertTrue(board.hasTheSamePositionOf(copiedBoard));
    }

}
