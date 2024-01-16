import board.Board;
import mechanics.ValidMovesChecker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.human.UserInputReader;
import positions.GamePositions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    @Test
    void noStrangeInputAllowed() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            Board board = new Board();
            UserInputReader mockedInputReader = mock(UserInputReader.class);
            String simulatedInput = "ThisIsClearlyNotAValidMove";
            when(mockedInputReader.readInput()).thenReturn(simulatedInput);
            ValidMovesChecker movesChecker = new ValidMovesChecker(board);
            movesChecker.computeValidMoves();

            Game game = new Game();
            game.getMove(mockedInputReader, movesChecker);
        });
        assertEquals("Input coordinates should be a 2 characters string, eg. \"a1\"", e.getMessage());
    }


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void bothPlayersCanNotMove() {

        Board board = GamePositions.impossibleToMovePosition();

        UserInputReader mockedInputReader = mock(UserInputReader.class);
        String simulatedInput = "This does not matter, the game should end before asking for input";
        when(mockedInputReader.readInput()).thenReturn(simulatedInput);
        Game game = new Game();

        game.play(board, mockedInputReader);
        assertTrue(outputStreamCaptor.toString().trim().endsWith("No valid moves for both players. Game over."));
        assertTrue(board.isGameOver());

    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


}
