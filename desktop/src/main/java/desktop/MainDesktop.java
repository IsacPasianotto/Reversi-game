package desktop;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import desktop.gui.other.WelcomeFrame;

import javax.swing.*;

public class MainDesktop {

    public static void main(String[] args) {

        FlatGradiantoDeepOceanIJTheme.setup();

        WelcomeFrame welcomePanel = new WelcomeFrame();
        SwingUtilities.invokeLater(welcomePanel::setWelcomeFrameVisible);
    }
}
