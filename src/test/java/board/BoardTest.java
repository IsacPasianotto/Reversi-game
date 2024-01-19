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
        ColoredPawn coloredPawnTile = board.getPositionColor(tile.getX(), tile.getY());
        assertEquals(ColoredPawn.EMPTY, coloredPawnTile);
    }

    @Test
    void checkStartingPosition(){
        Board board = new Board();
        assertEquals(ColoredPawn.WHITE, board.getPositionColor(3, 3));
        assertEquals(ColoredPawn.WHITE, board.getPositionColor(4, 4));
        assertEquals(ColoredPawn.BLACK, board.getPositionColor(3, 4));
        assertEquals(ColoredPawn.BLACK, board.getPositionColor(4, 3));
    }

    @Test
    void blackToMoveOnStart(){
        Board board = new Board();
        assertTrue(board.isBlackToMove());
    }

    @Test
    void turnChangesAfterMove(){
        Board board = new Board();
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }});
        board.applyMoveToBoard(c4);
        assertFalse(board.isBlackToMove());
    }

    @Test
    void playersAreUpdated(){
        Board board = new Board();
        assertEquals(ColoredPawn.BLACK, board.getCurrentPlayerColor());
        assertEquals(ColoredPawn.WHITE, board.getCurrentOpponentColor());
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }});
        board.applyMoveToBoard(c4);
        assertEquals(ColoredPawn.WHITE, board.getCurrentPlayerColor());
        assertEquals(ColoredPawn.BLACK, board.getCurrentOpponentColor());
    }

    @Test
    void swapAlongAllDirections() {
        Board board = thereAreTwoFlippableLinesInOneMove();
        board.swapTurn();
        ArrayList<Direction> directions = new ArrayList<>() {{
            add(new Direction(-1, 0));
            add(new Direction(0, 1));
        }};
        ValidMove move = new ValidMove(new BoardTile("c5"), directions);
        board.applyMoveToBoard(move);
        Board expectedBoard = twoLinesFlippedOneMove();
        assertEquals(board, expectedBoard);
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#someScores")
    void scoreIsCorrect(Board board, int blackScore, int whiteScore) {
        assertEquals(blackScore, board.computeScoreForPlayer(ColoredPawn.BLACK));
        assertEquals(whiteScore, board.computeScoreForPlayer(ColoredPawn.WHITE));
    }

    @Test
    void copyFunctionsWorks() {
        Board board = impossibleToMovePosition();
        Board copiedBoard = board.copy();
        assertTrue(board.equals(copiedBoard));
    }

}
