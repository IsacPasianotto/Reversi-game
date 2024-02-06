package desktop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainDesktopTest {
    @Test
    public void mainLaunchesAppWithoutException() {
        assertDoesNotThrow(() -> MainDesktop.main(new String[]{}));
    }
}
