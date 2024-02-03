package player.computer;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.Game;
import mechanics.GameController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import player.Player;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SmartPlayerTest {

    private static final int nIter = 500;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValid(Board currentPosition) {
        SmartPlayer player = new SmartPlayer(ColoredPawn.BLACK);
        Game game = new Game(currentPosition, player, new RandomPlayer(ColoredPawn.WHITE));
        GameController checker = game.getGameController();
        ArrayList<ValidMove> expected = checker.getValidMoves();
        ValidMove obtained = player.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValidWithWhite(Board currentPosition) {
        SmartPlayer player = new SmartPlayer(ColoredPawn.WHITE);
        Game game = new Game(currentPosition, new RandomPlayer(ColoredPawn.BLACK), player);
        GameController checker = game.getGameController();
        game.swapTurn();
        ArrayList<ValidMove> expected = checker.getValidMoves();
        ValidMove obtained = player.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }

    @Test
    void isSmartPlayerStrongerThanRandomWithBlack() {
        int smartWon = 0;
        Player smart = new SmartPlayer(ColoredPawn.BLACK);
        Player random = new RandomPlayer(ColoredPawn.WHITE);
        int blackPawns;
        int whitePawns;
        for (int i = 0; i < nIter; i++) {
            Game game = new Game(new Board(), smart, random);
            game.play();
            Board finalBoard = game.getGameController().getBoard();
            blackPawns = finalBoard.computeScoreForPlayer(ColoredPawn.BLACK);
            whitePawns = finalBoard.computeScoreForPlayer(ColoredPawn.WHITE);
            if (blackPawns > whitePawns) smartWon++;
        }
        System.out.println("Smart won " + smartWon + " times out of " + nIter + " games.");
        assertTrue(smartWon > nIter / 2);
    }

    @Test
    void isSmartPlayerStrongerThanRandomWithWhite() {
        int smartWon = 0;
        Player smart = new SmartPlayer(ColoredPawn.WHITE);
        Player random = new RandomPlayer(ColoredPawn.BLACK);
        int blackPawns;
        int whitePawns;
        for (int i = 0; i < nIter; i++) {
            Game game = new Game(new Board(), random, smart);
            game.play();
            Board finalBoard = game.getGameController().getBoard();
            blackPawns = finalBoard.computeScoreForPlayer(ColoredPawn.BLACK);
            whitePawns = finalBoard.computeScoreForPlayer(ColoredPawn.WHITE);
            if (whitePawns > blackPawns)
                smartWon++;
        }
        assertTrue(smartWon > nIter / 2);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
