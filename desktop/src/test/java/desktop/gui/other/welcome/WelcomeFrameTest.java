package desktop.gui.other.welcome;

import desktop.gui.main.GuiManager;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WelcomeFrameTest extends WelcomeFrame {
    @Test
    public void defaultGameMode() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.isDefaultOnIthPanel(0));
    }

    @Test
    public void defaultDifficulty() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.isDefaultOnIthPanel(1));
    }

    @Test
    public void defaultWhoPlaysFirst() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.isDefaultOnIthPanel(2));
    }

    @Test
    public void changeGameMode() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.pressIthButtonOnJthPanel(1, 0);
        assertFalse(welcomeFrame.isDefaultOnIthPanel(0));
    }

    @Test
    public void changeDifficulty() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.pressIthButtonOnJthPanel(1, 1);
        assertFalse(welcomeFrame.isDefaultOnIthPanel(1));
    }

    @Test
    public void changeFirstPlayer() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.pressIthButtonOnJthPanel(1, 2);
        assertFalse(welcomeFrame.isDefaultOnIthPanel(2));
    }

    @Test
    public void DifficultyAndFirstPlayerAreNotVisible() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertFalse(welcomeFrame.getIthPanel(1).isVisible());
        assertFalse(welcomeFrame.getIthPanel(2).isVisible());
    }

    @Test
    public void HumanVsComputerShowsDifficultyAndWhoPlaysFirst() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.pressIthButtonOnJthPanel(1, 0);
        assertTrue(welcomeFrame.getIthPanel(1).isVisible());
        assertTrue(welcomeFrame.getIthPanel(2).isVisible());
    }

    @Test
    public void startButtonSpawnGuiGame() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.getStartButton().doClick();
        Optional<JFrame> gameFrame = Optional.ofNullable(GuiManager.getGameFrame());
        assertTrue(gameFrame.isPresent());
    }
}
