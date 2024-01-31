package desktop;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import desktop.gui.other.WelcomeFrame;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;
import player.Player;
import player.computer.SmartPlayer;
import player.human.Human;

import javax.swing.*;

public class MainDesktop {

    public static void main(String[] args) {

        FlatGradiantoDeepOceanIJTheme.setup();

        WelcomeFrame welcomePanel = new WelcomeFrame();
        SwingUtilities.invokeLater(() -> {
            welcomePanel.setWelcomeFrameVisible();
            welcomePanel.setActionListenerToStartButton(e -> {
                welcomePanel.disposeWelcomeFrame();
                startBoardFrame(welcomePanel);
            });
        });
    }

    private static void startBoardFrame(WelcomeFrame welcomePanel) {
        Player blackPlayer = null;
        Player whitePlayer = null;
        BoardDesktop board = new BoardDesktop();


        if (!welcomePanel.isHumanVsComputer()){
            blackPlayer = new Human();
            whitePlayer = new Human();
        } else {
            JOptionPane.showMessageDialog(null, "This game mode is not implemented yed!", "ATTENTION", JOptionPane.WARNING_MESSAGE);
            System.out.println("notImplemented yet");
            System.exit(0);
            if (welcomePanel.isDifficultyHard()){
                if (welcomePanel.isHumanFirst()){
                    blackPlayer = new Human();
                    whitePlayer = new SmartPlayer();
                } else {
                    blackPlayer = new SmartPlayer();
                    whitePlayer = new Human();
                }
            } else {
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

        SwingUtilities.invokeLater(() -> {
            gameDesktop.guiManager.setFrameVisible(true);
        });
    }
}
