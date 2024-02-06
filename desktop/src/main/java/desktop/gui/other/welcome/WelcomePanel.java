package desktop.gui.other.welcome;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import static desktop.gui.other.welcome.GameSettings.Players;

/**
 * Build the panels needed to set the game settings.
 */
public class WelcomePanel {
    /**
     * The number of game modes: human vs human, human vs computer
     */
    protected static final int numberOfGameModes = 2;
    /**
     * The number of difficulties: easy, hard
     */
    protected static final int numberOfDifficulties = 2;
    /**
     * The number of players: black, white
     */
    protected static final int numberOfPlayers = 2;
    private final SingleSettingPanel gameMode;
    private final SingleSettingPanel difficulty;
    private final SingleSettingPanel firstPlayer;
    private GameSettings gameSettings;

    WelcomePanel() {
        gameSettings = new GameSettings(true, true, true);
        gameMode = new SingleSettingPanel(numberOfGameModes, "Game Mode", "Select a game mode:", new String[]{"Human vs Human", "Human vs Computer"});
        difficulty = new SingleSettingPanel(numberOfDifficulties, "Difficulty", "Select a difficulty", new String[]{"Easy", "Hard"});
        firstPlayer = new SingleSettingPanel(numberOfPlayers, "Who plays first?", "Select your color:", new String[]{"Black", "White"});
        difficulty.setVisible(false);
        firstPlayer.setVisible(false);
        setActionListeners();
    }

    private void setActionListeners() {
        ActionListener[] gameModeListeners = {e -> setHumanVsHumanSetting(true), e -> setHumanVsHumanSetting(false)};
        IntStream.range(0, numberOfGameModes).forEach(i -> gameMode.setActionListenerToIthButton(i, gameModeListeners[i]));

        ActionListener[] difficultyListeners = {e -> gameSettings = new GameSettings(false, true, gameSettings.isHumanFirst()),
                e -> gameSettings = new GameSettings(false, false, gameSettings.isHumanFirst())};
        IntStream.range(0, numberOfDifficulties).forEach(i -> difficulty.setActionListenerToIthButton(i, difficultyListeners[i]));

        ActionListener[] firstPlayerListeners = {e -> gameSettings = new GameSettings(false, gameSettings.isDifficultyEasy(), true),
                e -> gameSettings = new GameSettings(false, gameSettings.isDifficultyEasy(), false)};
        IntStream.range(0, numberOfPlayers).forEach(i -> firstPlayer.setActionListenerToIthButton(i, firstPlayerListeners[i]));
    }

    private void setHumanVsHumanSetting(boolean aFlag) {
        difficulty.setVisible(!aFlag);
        firstPlayer.setVisible(!aFlag);
        gameSettings = new GameSettings(aFlag, gameSettings.isDifficultyEasy(), gameSettings.isHumanFirst());
    }

    Players getPlayers() {
        return gameSettings.getPlayers();
    }

    JRadioButton returnIthButtonOnJthPanel(int i, int j) {
        return switch (j) {
            case 0 -> gameMode.getIthButton(i);
            case 1 -> difficulty.getIthButton(i);
            case 2 -> firstPlayer.getIthButton(i);
            default -> null;
        };
    }

    JPanel getIthPanel(int i) {
        return switch (i) {
            case 0 -> gameMode.getPanel();
            case 1 -> difficulty.getPanel();
            case 2 -> firstPlayer.getPanel();
            default -> null;
        };
    }

    boolean isDefaultOnIthPanel(int i) {
        return gameSettings.isDefaultOnIthPanel(i);
    }

}
