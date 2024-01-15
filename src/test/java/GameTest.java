import board.Board;
import mechanics.ValidMovesChecker;
import org.junit.jupiter.api.Test;
import player.Player;
import player.human.InputReader;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    @Test
    void noStrangeInputAllowed() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            Board board = new Board();
            Player player = new Player();
            InputReader mockedInputReader = mock(InputReader.class);
            String simulatedInput = "ThisIsClearlyNotAValidMove";
            when(mockedInputReader.readInput()).thenReturn(simulatedInput);
            ValidMovesChecker movesChecker = new ValidMovesChecker(board);
            movesChecker.computeValidMoves();

            Game game = new Game();
            game.getMove(board, player, mockedInputReader, movesChecker);
        });
        assertEquals("Input coordinates should be a 2 characters string, eg. \"a1\"", e.getMessage());
    }
}
