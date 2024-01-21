package mechanics;

import board.Board;
import board.ValidMove;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.computer.RandomPlayer;
import player.human.Human;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static positions.GamePositions.impossibleToMovePosition;

class GameTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void bothPlayersCantMove() {
        Board board = impossibleToMovePosition();
        Game game = new Game(board, new RandomPlayer(), new RandomPlayer());
        game.play();
        assertTrue(outputStreamCaptor.toString().trim().contains("No valid moves for both players. Game over."));
        assertTrue(game.isGameOver());
    }

    @Test
    void undoToInitialPosition() {
        Board board = new Board();
        Game game = new Game(board, new Human(), new Human());
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove move = validMovesChecker.getValidMoves().get(0);
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
        ValidMove move1 = checker.getValidMoves().get(0);
        board.applyMoveToBoard(move1);
        game.previousSteps.add(board.copy());

        checker.computeValidMoves();
        ValidMove move2 = checker.getValidMoves().get(0);
        board.applyMoveToBoard(move2);
        game.previousSteps.add(board.copy());
        game.undoLastMove();
        assertEquals(new Board(), board);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
