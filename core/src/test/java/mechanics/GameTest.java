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
    void playersUpdatedAfterMove() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.playASingleTurn();
        assertEquals(ColoredPawn.WHITE, game.getCurrentPlayerColor());
        assertEquals(ColoredPawn.BLACK, game.getCurrentPlayerColor().opposite());
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
        game.playASingleTurn();
        game.playASingleTurn();
        assertEquals(2, game.skippedTurns);
    }

    @Test
    void undoToInitialPosition() {
        Board board = new Board();
        Game game = new Game(board, new Human(ColoredPawn.BLACK), new Human(ColoredPawn.WHITE));
        GameController gameController = game.getGameController();
        doSingleMove(game);
        game.undoLastMove();
        assertEquals(new Board(), gameController.getBoard());
    }

    @Test
    void undoTwoMovesIfHumanVsBot() {
        Board board = new Board();
        Game game = new Game(board, new Human(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        GameController gameController = game.getGameController();
        doSingleMove(game);
        game.swapTurn();
        doSingleMove(game);
        game.undoLastMove();
        assertEquals(new Board(), gameController.getBoard());
    }

    private void doSingleMove(Game game) {
        GameController gameController = game.getGameController();
        Board board = gameController.getBoard();
        gameController.computeValidMoves(game.getCurrentPlayerColor());
        ValidMove move = gameController.getValidMoves().get(0);
        board.applyMoveToBoard(move);
        game.previousSteps.add(board.copy());
    }
}
