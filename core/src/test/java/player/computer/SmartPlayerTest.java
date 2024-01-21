package player.computer;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.Game;
import mechanics.ValidMovesChecker;
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
        ValidMovesChecker checker = new ValidMovesChecker(currentPosition);
        checker.computeValidMoves();
        ArrayList<ValidMove> expected = checker.getValidMoves();
        SmartPlayer player = new SmartPlayer();
        ValidMove obtained = player.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValidWithWhite(Board currentPosition) {
        ValidMovesChecker checker = new ValidMovesChecker(currentPosition);
        checker.swapTurn();
        checker.computeValidMoves();
        ArrayList<ValidMove> expected = checker.getValidMoves();
        SmartPlayer player = new SmartPlayer();
        ValidMove obtained = player.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }

    @Test
    void isSmartPlayerStrongerThanRandomWithBlack() {
        int smartWon = 0;
        Player smart = new SmartPlayer();
        Player random = new RandomPlayer();
        int blackPawns;
        int whitePawns;
        for (int i = 0; i < nIter; i++) {
            Game game = new Game(new Board(), smart, random);
            game.play();
            Board finalBoard = game.getBoard();
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
        Player smart = new SmartPlayer();
        Player random = new RandomPlayer();
        int blackPawns;
        int whitePawns;
        for (int i = 0; i < nIter; i++) {
            Game game = new Game(new Board(), random, smart);
            game.play();
            Board finalBoard = game.getBoard();
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
