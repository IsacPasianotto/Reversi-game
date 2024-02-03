package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import org.junit.jupiter.api.Test;
import player.computer.RandomPlayer;
import player.human.Human;

import static org.junit.jupiter.api.Assertions.*;
import static positions.GamePositions.impossibleToMovePosition;

class GameTest {

    @Test
    void blackToMoveOnStart() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        assertTrue(game.isBlackToMove());
    }

    @Test
    void PlayerAreCorrectOnStart() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        assertEquals(ColoredPawn.BLACK, game.getCurrentPlayerColor());
        assertEquals(ColoredPawn.WHITE, game.getCurrentPlayerColor().opposite());
    }

    @Test
    void changeTurnAfterMove() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.playASingleTurn();
        assertFalse(game.isBlackToMove());
    }

    @Test
    void bothPlayersCantMove() {
        Board board = impossibleToMovePosition();
        Game game = new Game(board, new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.play();
        assertTrue(game.isGameOver);
    }

    @Test
    void undoToInitialPosition() {
        Game game = new Game(new Board(), new Human(ColoredPawn.BLACK), new Human(ColoredPawn.WHITE));
        game.playASingleTurn();
        game.undoLastMove();
        assertEquals(new Board(), game.getGameController().getBoard());
    }

    @Test
    void undoTwoMovesIfHumanVsBot() {
        Board board = new Board();
        Game game = new Game(board, new Human(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        GameController gameController = game.getGameController();

        gameController.computeValidMoves(game.getCurrentPlayerColor());
        ValidMove move1 = gameController.getValidMoves().getFirst();
        board.applyMoveToBoard(move1);
        game.previousSteps.add(board.copy());

        gameController.computeValidMoves(game.getCurrentPlayerColor());
        ValidMove move2 = gameController.getValidMoves().getFirst();
        board.applyMoveToBoard(move2);
        game.previousSteps.add(board.copy());

        game.undoLastMove();
        assertEquals(new Board(), gameController.getBoard());
    }
}
