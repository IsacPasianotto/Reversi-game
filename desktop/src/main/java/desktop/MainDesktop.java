package desktop;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import desktop.gui.main.GuiManager;
import desktop.gui.other.WelcomeFrame;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;
import player.Player;
import player.computer.SmartPlayer;
import player.human.Human;

import javax.swing.*;

public class MainDesktop {

    public static JFrame gameFrame;
    private static GuiManager guiManager;

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

        Player blackPlayer = null;
        Player whitePlayer = null;
        BoardDesktop board = new BoardDesktop();



        if (!welcomePanel.isHumanVsComputer()){
            blackPlayer = new Human();
            whitePlayer = new Human();
        } else {
            JOptionPane.showMessageDialog(null, "This game mode is not implemented yed!", "ATTENTION", JOptionPane.WARNING_MESSAGE);
            System.out.println("notImplemented yet");
            // System.exit(0);
            if (welcomePanel.isDifficultyHard()){
                if (welcomePanel.isHumanFirst()){
                    blackPlayer = new Human();
                    whitePlayer = new SmartPlayer();
                } else {
                    blackPlayer = new SmartPlayer();
                    whitePlayer = new Human();
                }
            } else if (!welcomePanel.isDifficultyHard()){
                if (welcomePanel.isHumanFirst()){
                    blackPlayer = new Human();
                    whitePlayer = new SmartPlayer();
                } else {
                    blackPlayer = new SmartPlayer();
                    whitePlayer = new Human();
                }
            }
        }

        GameDesktop gameDesktop = new GameDesktop(board, blackPlayer, whitePlayer);

        GuiManager guiManager  = new GuiManager(board, gameDesktop);

        SwingUtilities.invokeLater(() -> {
            GuiManager.gameFrame.setVisible(true);
        });


    }
}
