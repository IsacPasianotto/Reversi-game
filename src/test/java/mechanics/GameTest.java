package mechanics;

import board.Board;
import board.ValidMove;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.computer.RandomPlayer;
import player.human.Human;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static positions.GamePositions.impossibleToMovePosition;

public class GameTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void bothPlayersCanNotMove() {
        Board board = impossibleToMovePosition();
        Player player1 = new RandomPlayer();
        Player player2 = new RandomPlayer();
        Game game = new Game(board, player1, player2);
        game.play();
        assertTrue(outputStreamCaptor.toString().trim().contains("No valid moves for both players. Game over."));
        assertTrue(board.isGameOver());

   }

    @Test
    void undoToInitialPosition () {
        Board board = new Board();
        ValidMovesChecker checker = new ValidMovesChecker(board);
        checker.computeValidMoves();
        ValidMove move = checker.getValidMoves().get(0);
        Game game = new Game(board, new RandomPlayer(), new RandomPlayer());
        game.board.applyMoveToBoard(move);
        game.previousSteps.add(game.board.copy());
        game.undoLastMove();
        assertTrue(game.board.equals(new Board()));
    }

    @Test
    void undoChangesTwoMovesIfThereIsOneHuman() {
        Board board = new Board();
        ValidMovesChecker checker = new ValidMovesChecker(board);
        checker.computeValidMoves();
        ValidMove move1 = checker.getValidMoves().get(0);
        Game game = new Game(board, new Human(), new RandomPlayer());
        game.board.applyMoveToBoard(move1);
        game.previousSteps.add(game.board.copy());
        checker = new ValidMovesChecker(game.board);
        checker.computeValidMoves();
        ValidMove move2 = checker.getValidMoves().get(0);
        game.board.applyMoveToBoard(move2);
        game.previousSteps.add(game.board.copy());
        game.undoLastMove();
        assertTrue(game.board.equals(new Board()));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
