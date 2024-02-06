package player.computer;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.Game;
import mechanics.GameController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomPlayerTest {

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValid(Board currentPosition) {
        RandomPlayer firstPlayer = new RandomPlayer(ColoredPawn.BLACK);
        Game game = new Game(currentPosition, firstPlayer, new RandomPlayer(ColoredPawn.WHITE));
        GameController gameController = game.getGameController();
        gameController.computeValidMoves(firstPlayer.getPlayerColor());
        ArrayList<ValidMove> expected = gameController.getValidMoves();
        ValidMove obtained = firstPlayer.askForAMove(gameController);
        assertTrue(expected.contains(obtained));
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValidWithWhite(Board currentPosition) {
        GameController gameController = new GameController(currentPosition);
        RandomPlayer player = new RandomPlayer(ColoredPawn.WHITE);
        gameController.computeValidMoves(ColoredPawn.WHITE);
        ArrayList<ValidMove> expected = gameController.getValidMoves();
        ValidMove obtained = player.askForAMove(gameController);
        assertTrue(expected.contains(obtained));
    }
}
