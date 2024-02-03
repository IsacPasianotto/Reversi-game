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
        ArrayList<ValidMove> expected = game.getGameController().getValidMoves();
        ValidMove obtained = firstPlayer.askForAMove(game.getGameController());
        assertTrue(expected.contains(obtained));
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValidWithWhite(Board currentPosition) {
        RandomPlayer secondPlayer = new RandomPlayer(ColoredPawn.WHITE);
        Game game = new Game(currentPosition, new RandomPlayer(ColoredPawn.BLACK), secondPlayer);
        game.swapTurn();
        GameController checker = game.getGameController();
        checker.computeValidMoves(game.getCurrentPlayerColor());
        ArrayList<ValidMove> expected = checker.getValidMoves();
        ValidMove obtained = secondPlayer.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }
}
