package desktop.gui.other;

import desktop.gui.other.components.Button;
import desktop.gui.other.components.DifficultyPanel;
import desktop.gui.other.components.GameModePanel;
import desktop.gui.other.components.WhoPlaysFirstPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeFrame {

    private JFrame frame;
    private GameModePanel gameMode;
    private DifficultyPanel difficulty;
    private WhoPlaysFirstPanel whoPlaysFirst;
    private JPanel difficultyPanel;
    private JPanel whoPlaysFirstPanel;
    private JButton startButton;
    private static final Font LabelsFont = new Font("Arial", Font.ITALIC,18 );
    private static final Font RadioButtonsFont = new Font("Arial", Font.BOLD, 15);
    private static final Font StartButtonFont = new Font("Arial", Font.BOLD, 20);
    private boolean isHumanVsComputer = true;
    private boolean isDifficultyHard = true;
    private boolean isHumanFirst = true;

    public WelcomeFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(500, 200);
        frame.setMinimumSize(new Dimension(500, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel generalPanel = getGeneralPanel();
        frame.add(generalPanel);
        frame.setVisible(true);    
    }

    public JFrame getFrame() {
        return frame;
    }

    private JPanel getGeneralPanel() {
        gameMode = new GameModePanel(LabelsFont, RadioButtonsFont);
        difficulty = new DifficultyPanel(LabelsFont, RadioButtonsFont);
        whoPlaysFirst = new WhoPlaysFirstPanel(LabelsFont, RadioButtonsFont);
        startButton = new Button(StartButtonFont, "Start").getButton();

        setActionListeners();

        JPanel gameModePanel = gameMode.getGameModePanel();
        difficultyPanel = difficulty.getDifficultyPanel();
        whoPlaysFirstPanel = whoPlaysFirst.getWhoPlaysFirstPanel();

        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.add(gameModePanel, BorderLayout.WEST);
        generalPanel.add(difficultyPanel, BorderLayout.CENTER);
        generalPanel.add(whoPlaysFirstPanel, BorderLayout.EAST);
        generalPanel.add(startButton, BorderLayout.SOUTH);
        return generalPanel;
    }

    private void setActionListeners(){
        gameMode.setActionListenerToHumanVsHumanButton(e -> setPlayersListener(false));
        gameMode.setActionListenerToHumanVsComputerButton(e -> setPlayersListener(true));

        difficulty.setActionListenerToEasyButton(e -> isDifficultyHard = false);
        difficulty.setActionListenerToHardButton(e -> isDifficultyHard = true);

        whoPlaysFirst.setActionListenerToBlackButton(e -> isHumanFirst = true);
        whoPlaysFirst.setActionListenerToWhiteButton(e -> isHumanFirst = false);
    }

    public void setActionListenerToStartButton(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

    public void setWelcomeFrameVisible() {
        frame.setVisible(true);
    }

    public void disposeWelcomeFrame() {
        frame.dispose();
    }

    private void setPlayersListener(boolean aFlag) {
        difficultyPanel.setVisible(aFlag);
        whoPlaysFirstPanel.setVisible(aFlag);
        isHumanVsComputer = aFlag;
    }

    public boolean isHumanVsComputer() {
        return isHumanVsComputer;
    }
    public boolean isDifficultyHard() {
        return isDifficultyHard;
    }
    public boolean isHumanFirst() {
        return isHumanFirst;
    }

}
