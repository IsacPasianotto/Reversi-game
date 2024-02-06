package desktop;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import desktop.gui.other.welcome.WelcomeFrame;

import javax.swing.*;

public class MainDesktop {

    /**
     * The main method of the desktop application, starts a welcome frame to begin the game.
     */
    public static void main(String[] args) {

        FlatGradiantoDeepOceanIJTheme.setup();

        WelcomeFrame welcomePanel = new WelcomeFrame();
        SwingUtilities.invokeLater(welcomePanel::setWelcomeFrameVisible);
    }
}
