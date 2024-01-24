package terminal;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainTerminalTest {

    @Test
    void findUserInputDoesNotBreak() throws IOException {
        String wrongInput = "This is not a number";
        try (BufferedReader mockedReader = mock(BufferedReader.class)) {
            when(mockedReader.readLine()).thenReturn(wrongInput);
            assertDoesNotThrow(() -> MainTerminal.findUserInput(mockedReader));
        }
    }

}
