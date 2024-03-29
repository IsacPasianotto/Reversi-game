package player.human;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputReaderTest {

    private static Stream<String> undoInVariousFormats() {
        return Stream.of("Undo", "undo", "UNDO");
    }

    private static Stream<String> quitInVariousFormats() {
        return Stream.of("Quit", "quit", "QUIT");
    }

    @ParameterizedTest
    @MethodSource("undoInVariousFormats")
    void undoCommandThrowsUndoException(String undoString) throws IOException {
        BufferedReader mockedReader = mock(BufferedReader.class);
        when(mockedReader.readLine()).thenReturn(undoString);
        UserInputReader reader = new UserInputReader(mockedReader);
        assertThrows(UndoException.class, reader::readInput);
    }

    @ParameterizedTest
    @MethodSource("quitInVariousFormats")
    void quitCommandThrowsQuitGameException(String quitstring) throws IOException {
        BufferedReader mockedReader = mock(BufferedReader.class);
        when(mockedReader.readLine()).thenReturn(quitstring);
        UserInputReader reader = new UserInputReader(mockedReader);
        assertThrows(QuitGameException.class, reader::readInput);
    }
}
