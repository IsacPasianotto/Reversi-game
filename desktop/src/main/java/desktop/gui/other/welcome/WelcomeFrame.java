package desktop.gui.other.welcome;

import desktop.gui.main.GuiManager;
import desktop.gui.other.Button;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private static final Font StartButtonFont = GuiManager.buttonFont;
    private static JFrame frame;
    protected WelcomePanel welcomePanel;
    private JButton startButton;

    /**
     * Creates a new WelcomeFrame, a frame that allows to decide game settings.
     */
    public WelcomeFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(WIDTH, HEIGHT);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel generalPanel = buildGeneralPanel();
        frame.add(generalPanel);
        frame.setVisible(true);
    }

    private JPanel buildGeneralPanel() {
        welcomePanel = new WelcomePanel();
        startButton = new Button(StartButtonFont, "Start").getButton();
        setActionListenerToStartButton();
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.add(welcomePanel.getIthPanel(0), BorderLayout.WEST);
        generalPanel.add(welcomePanel.getIthPanel(1), BorderLayout.CENTER);
        generalPanel.add(welcomePanel.getIthPanel(2), BorderLayout.EAST);
        generalPanel.add(startButton, BorderLayout.SOUTH);
        return generalPanel;
    }

    private void setActionListenerToStartButton() {
        startButton.addActionListener(e -> {
            frame.dispose();
            GameSettings.Players result = welcomePanel.getGameSettings().getPlayers();
            GameDesktop gameDesktop = new GameDesktop(new BoardDesktop(), result.blackPlayer(), result.whitePlayer());
            SwingUtilities.invokeLater(gameDesktop.guiManager::setFrameVisible);
        });
    }

    /**
     * Sets the welcome frame visible in order to start the game.
     */
    public void setWelcomeFrameVisible() {
        frame.setVisible(true);
    }
    /**
     * Returns the welcome frame.
     * @return the welcome frame
     */
    public static JFrame getWelcomeFrame() {
        return frame;
    }

    /**
     * Returns the start button.
     * @return the start button
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Presses the ith button on the jth panel (indexes start from 0).
     * @param i the ith button
     * @param j the jth panel (panels are game mode, difficulty, first player)
     */
    public void pressIthButtonOnJthPanel(int i, int j) {
        welcomePanel.returnIthButtonOnJthPanel(i, j).doClick();
    }

    public JPanel getIthPanel(int i){
        return welcomePanel.getIthPanel(i);
    }

    public boolean isDefaultOnIthPanel(int i){
        return welcomePanel.getGameSettings().isDefaultOnIthPanel(i);
    }

}
