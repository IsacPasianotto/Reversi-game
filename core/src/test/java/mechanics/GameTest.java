package mechanics;

import board.Board;
import board.ValidMove;
import org.junit.jupiter.api.Test;
import player.computer.RandomPlayer;
import player.human.Human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static positions.GamePositions.impossibleToMovePosition;

class GameTest {

    @Test
    void bothPlayersCantMove() {
        Board board = impossibleToMovePosition();
        Game game = new Game(board, new RandomPlayer(), new RandomPlayer());
        game.play();
        assertTrue(game.isGameOver());
    }

    @Test
    void undoToInitialPosition() {
        Board board = new Board();
        Game game = new Game(board, new Human(), new Human());
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove move = validMovesChecker.getValidMoves().getFirst();
        board.applyMoveToBoard(move);
        game.previousSteps.add(board.copy());
        game.undoLastMove();
        assertEquals(new Board(), board);
    }

    @Test
    void undoTwoMovesIfHumanVsBot() {
        Board board = new Board();
        Game game = new Game(board, new Human(), new RandomPlayer());
        ValidMovesChecker checker = new ValidMovesChecker(board);

        checker.computeValidMoves();
        ValidMove move1 = checker.getValidMoves().getFirst();
        board.applyMoveToBoard(move1);
        game.previousSteps.add(board.copy());

        checker.computeValidMoves();
        ValidMove move2 = checker.getValidMoves().getFirst();
        board.applyMoveToBoard(move2);
        game.previousSteps.add(board.copy());
        game.undoLastMove();
        assertEquals(new Board(), board);
    }

}
