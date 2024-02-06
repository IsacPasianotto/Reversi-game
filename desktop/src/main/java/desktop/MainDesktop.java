package desktop;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import desktop.gui.other.welcome.WelcomeFrame;

import javax.swing.*;

/**
 * The main class of the desktop application, starts a welcome frame to begin the game.
 */
public class MainDesktop {

    /**
     * Starts a welcome frame to begin the game.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args) {

        FlatGradiantoDeepOceanIJTheme.setup();

        WelcomeFrame welcomeFrame = new WelcomeFrame();
        SwingUtilities.invokeLater(welcomeFrame::setWelcomeFrameVisible);
    }
}
