package desktop.gui.other;

import board.ColoredPawn;
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
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private static final Font LabelsFont = new Font("Arial", Font.ITALIC,18 );
    private static final Font RadioButtonsFont = new Font("Arial", Font.BOLD, 15);
    private static final Font StartButtonFont = GuiManager.buttonFont;
    private static JFrame frame;
    private GameModePanel gameMode;
    private DifficultyPanel difficulty;
    private WhoPlaysFirstPanel whoPlaysFirst;
    private JPanel difficultyPanel;
    private JPanel whoPlaysFirstPanel;
    private JButton startButton;
    /**
     * A record containing the settings of the game.
     */
    public record GameSettings(boolean isHumanVsComputer, boolean isDifficultyHard, boolean isHumanFirst) {}
    private GameSettings gameSettings;

    /**
     * Creates a new WelcomeFrame, a frame that allows to decide game settings.
     */
    public WelcomeFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(WIDTH, HEIGHT);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        gameSettings = new GameSettings(true, true, true);
        JPanel generalPanel = buildGeneralPanel();
        frame.add(generalPanel);
        frame.setVisible(true);
    }

    private JPanel buildGeneralPanel() {
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

    private void setActionListenerToStartButton() {
        startButton.addActionListener(e -> {
            frame.dispose();
            Players result = getPlayers();
            GameDesktop gameDesktop = new GameDesktop(new BoardDesktop(), result.blackPlayer(), result.whitePlayer());
            SwingUtilities.invokeLater(gameDesktop.guiManager::setFrameVisible);
        });
    }

    private record Players(Player blackPlayer, Player whitePlayer) {}
    private Players getPlayers() {
        Player blackPlayer;
        Player whitePlayer;

        if (!gameSettings.isHumanVsComputer()) {
            blackPlayer = new Human(ColoredPawn.BLACK);
            whitePlayer = new Human(ColoredPawn.WHITE);
        } else {
            if (gameSettings.isDifficultyHard()) {
                blackPlayer = gameSettings.isHumanFirst() ? new Human(ColoredPawn.BLACK) : new SmartPlayer(ColoredPawn.BLACK);
                whitePlayer = gameSettings.isHumanFirst() ? new SmartPlayer(ColoredPawn.WHITE) : new Human(ColoredPawn.WHITE);
            } else {
                blackPlayer = gameSettings.isHumanFirst() ? new Human(ColoredPawn.BLACK) : new RandomPlayer(ColoredPawn.BLACK);
                whitePlayer = gameSettings.isHumanFirst() ? new RandomPlayer(ColoredPawn.WHITE) : new Human(ColoredPawn.WHITE);
            }
        }
        return new Players(blackPlayer, whitePlayer);
    }

    private void setPlayersListener(boolean aFlag) {
        difficultyPanel.setVisible(aFlag);
        whoPlaysFirstPanel.setVisible(aFlag);
        gameSettings = new GameSettings(aFlag, gameSettings.isDifficultyHard(), gameSettings.isHumanFirst());
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
    public static JFrame getWelcomeFrame() { return frame; }
    /**
     * Returns the game settings.
     * @return the game settings
     */
    public GameSettings getGameSettings() { return gameSettings; }
    /**
     * Returns the game mode panel.
     * @return the game mode panel
     */
    public GameModePanel getGameMode() { return gameMode; }
    /**
     * Returns the start button.
     * @return the start button
     */
    public JButton getStartButton() { return startButton; }
    /**
     * Returns the difficulty panel.
     * @return the difficulty panel
     */
    public DifficultyPanel getDifficulty() { return difficulty; }

    /**
     * Returns the who plays first panel.
     * @return the who plays first panel
     */
    public WhoPlaysFirstPanel getWhoPlaysFirst() { return whoPlaysFirst; }
}
