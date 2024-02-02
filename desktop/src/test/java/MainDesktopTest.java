import desktop.MainDesktop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainDesktopTest {

    @Test
    public void mainLaunchesAppWithoutException() {
        assertDoesNotThrow(() -> MainDesktop.main(new String[]{}));
    }

}
