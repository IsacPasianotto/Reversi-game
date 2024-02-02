package desktop.gui.other;

import desktop.gui.main.GuiManager;
import desktop.gui.other.components.*;
import desktop.gui.other.components.Button;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.Human;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame {

    private static JFrame frame;
    private static final Font LabelsFont = new Font("Arial", Font.ITALIC,18 );
    private static final Font RadioButtonsFont = new Font("Arial", Font.BOLD, 15);
    private static final Font StartButtonFont = GuiManager.buttonFont;
    private GameModePanel gameMode;
    private DifficultyPanel difficulty;
    private WhoPlaysFirstPanel whoPlaysFirst;
    private JPanel difficultyPanel;
    private JPanel whoPlaysFirstPanel;
    private JButton startButton;
    public record GameSettings(boolean isHumanVsComputer, boolean isDifficultyHard, boolean isHumanFirst) {}
    private GameSettings gameSettings;

    public WelcomeFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(500, 200);
        frame.setMinimumSize(new Dimension(500, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        gameSettings = new GameSettings(true, true, true);
        JPanel generalPanel = getGeneralPanel();
        frame.add(generalPanel);
        frame.setVisible(true);
    }

    public static JFrame getWelcomeFrame() { return frame; }

    public GameSettings getGameSettings() { return gameSettings; }

    public GameModePanel getGameMode() { return gameMode; }

    public JButton getStartButton() { return startButton; }

    public DifficultyPanel getDifficulty() { return difficulty; }

    public WhoPlaysFirstPanel getWhoPlaysFirst() { return whoPlaysFirst; }

    public void setWelcomeFrameVisible() {
        frame.setVisible(true);
    }

    public void setActionListenerToStartButton() {
        startButton.addActionListener(e -> {
            frame.dispose();
            Players result = getPlayers();
            GameDesktop gameDesktop = new GameDesktop(new BoardDesktop(), result.blackPlayer(), result.whitePlayer());
            SwingUtilities.invokeLater(gameDesktop.guiManager::setFrameVisible);
        });
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

        difficulty.setActionListenerToEasyButton(e -> gameSettings = new GameSettings(true,false, gameSettings.isHumanFirst()));
        difficulty.setActionListenerToHardButton(e -> gameSettings = new GameSettings(true,true, gameSettings.isHumanFirst()));

        whoPlaysFirst.setActionListenerToBlackButton(e -> gameSettings = new GameSettings(true, gameSettings.isDifficultyHard(), true));
        whoPlaysFirst.setActionListenerToWhiteButton(e -> gameSettings = new GameSettings(true, gameSettings.isDifficultyHard(), false));

        setActionListenerToStartButton();
    }

    private record Players(Player blackPlayer, Player whitePlayer) {}
    private Players getPlayers() {
        Player blackPlayer;
        Player whitePlayer;

        if (!gameSettings.isHumanVsComputer()) {
            blackPlayer = new Human();
            whitePlayer = new Human();
        } else {
            if (gameSettings.isDifficultyHard()) {
                blackPlayer = gameSettings.isHumanFirst() ? new Human() : new SmartPlayer();
                whitePlayer = gameSettings.isHumanFirst() ? new SmartPlayer() : new Human();
            } else {
                blackPlayer = gameSettings.isHumanFirst() ? new Human() : new RandomPlayer();
                whitePlayer = gameSettings.isHumanFirst() ? new RandomPlayer() : new Human();
            }
        }
        return new Players(blackPlayer, whitePlayer);
    }

    private void setPlayersListener(boolean aFlag) {
        difficultyPanel.setVisible(aFlag);
        whoPlaysFirstPanel.setVisible(aFlag);
        gameSettings = new GameSettings(aFlag, gameSettings.isDifficultyHard(), gameSettings.isHumanFirst());
    }
}
