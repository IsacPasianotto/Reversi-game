package desktop.welcome;

import desktop.welcome.components.DifficultyPanel;
import desktop.welcome.components.GameModePanel;
import desktop.welcome.components.StartButton;
import desktop.welcome.components.WhoPlaysFirstPanel;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame {

    public JFrame frame;
    private JPanel generalPanel;
    private GameModePanel gameMode;
    private DifficultyPanel difficulty;
    private WhoPlaysFirstPanel whoPlaysFirst;
    private JPanel gameModePanel;
    private JPanel difficultyPanel;
    private JPanel whoPlaysFirstPanel;
    private JButton startButton;
    private final Font LabelsFont = new Font("Arial", Font.ITALIC,18 );
    private final Font RadioButtonsFont = new Font("Arial", Font.BOLD, 15);
    private final Font StartButtonFont = new Font("Arial", Font.BOLD, 20);
    private boolean isHumanVsComputer = true;
    private boolean isDifficultyHard = true;
    private boolean isHumanFirst = true;

    public WelcomeFrame() {
        buildFrame();
    }

    private void buildFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(500, 200);
        frame.setMinimumSize(new Dimension(500, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        gameMode = new GameModePanel(LabelsFont, RadioButtonsFont);
        difficulty = new DifficultyPanel(LabelsFont, RadioButtonsFont);
        whoPlaysFirst = new WhoPlaysFirstPanel(LabelsFont, RadioButtonsFont);
        startButton = new StartButton(StartButtonFont).getStartButton();

        setActionListeners();

        gameModePanel = gameMode.getGameModePanel();
        difficultyPanel = difficulty.getDifficultyPanel();
        whoPlaysFirstPanel = whoPlaysFirst.getWhoPlaysFirstPanel();

        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.add(gameModePanel, BorderLayout.WEST);
        generalPanel.add(difficultyPanel, BorderLayout.CENTER);
        generalPanel.add(whoPlaysFirstPanel, BorderLayout.EAST);
        generalPanel.add(startButton, BorderLayout.SOUTH);

        frame.add(generalPanel);
        frame.setVisible(true);
    }

    private void setActionListeners(){
        gameMode.setActionListenerToHumanVsHumanButton(e -> {
            difficultyPanel.setVisible(false);
            whoPlaysFirstPanel.setVisible(false);
            isHumanVsComputer = false;
        });
        gameMode.setActionListenerToHumanVsComputerButton(e -> {
            difficultyPanel.setVisible(true);
            whoPlaysFirstPanel.setVisible(true);
            isHumanVsComputer = true;
        });

        difficulty.setActionListenerToEasyButton(e -> {
            isDifficultyHard = false;
        });
        difficulty.setActionListenerToHardButton(e -> {
            isDifficultyHard = true;
        });

        whoPlaysFirst.setActionListenerToBlackButton(e -> {
            isHumanFirst = true;
        });
        whoPlaysFirst.setActionListenerToWhiteButton(e -> {
            isHumanFirst = false;
        });

        startButton.addActionListener(e -> {
            frame.setVisible(false);
        });
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
