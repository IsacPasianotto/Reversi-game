package desktop;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import desktop.welcome.WelcomeFrame;
import player.Player;
import player.human.Human;

import javax.swing.*;

public class MainDesktop {

    public static void main(String[] args) throws InterruptedException {
        FlatGradiantoDeepOceanIJTheme.setup();

        WelcomeFrame welcomePanel = new WelcomeFrame();
        SwingUtilities.invokeLater(() -> {
            JFrame welcomeJF = welcomePanel.frame;
            welcomeJF.setVisible(true);
        });

        while (welcomePanel.frame.isVisible()) {
            Thread.sleep(100);
        }
        welcomePanel.frame.dispose();

        Player blackPlayer;
        Player whitePlayer;

        if (!welcomePanel.isHumanVsComputer()){
            blackPlayer = new Human();
            whitePlayer = new Human();
        } else {
            JOptionPane.showMessageDialog(null, "This game mode is not implemented yed!", "ATTENTION", JOptionPane.WARNING_MESSAGE);
            System.out.println("notImplemented yet");
        }



    }

}
