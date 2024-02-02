import desktop.gui.main.GuiManager;
import desktop.gui.other.WelcomeFrame;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.html.Option;

import java.util.Optional;

import static desktop.gui.main.GuiManager.getGameFrame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WelcomeFrameTest extends WelcomeFrame{

    @Test
    public void defaultGameMode() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.getGameSettings().isHumanVsComputer());
    }

    @Test
    public void defaultDifficulty() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.getGameSettings().isDifficultyHard());
    }

    @Test
    public void defaultWhoPlaysFirst() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.getGameSettings().isHumanFirst());
    }

    @Test
    public void changeGameMode() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.getGameMode().getHumanVsHumanButton().doClick();
        assertFalse(welcomeFrame.getGameSettings().isHumanVsComputer());
    }

    @Test
    public void changeDifficulty() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.getDifficulty().getEasyButton().doClick();
        assertFalse(welcomeFrame.getGameSettings().isDifficultyHard());
    }

    @Test
    public void changeWhoPlaysFirst() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.getWhoPlaysFirst().getWhiteButton().doClick();
        assertFalse(welcomeFrame.getGameSettings().isHumanFirst());
    }

    @Test
    public void DifficultyAndWhoPlaysFirstAreVisible() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        assertTrue(welcomeFrame.getDifficulty().getDifficultyPanel().isVisible());
        assertTrue(welcomeFrame.getWhoPlaysFirst().getWhoPlaysFirstPanel().isVisible());
    }

    @Test
    public void HumanVsHumanHidesDifficultyAndWhoPlaysFirst() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.getGameMode().getHumanVsHumanButton().doClick();
        assertFalse(welcomeFrame.getDifficulty().getDifficultyPanel().isVisible());
        assertFalse(welcomeFrame.getWhoPlaysFirst().getWhoPlaysFirstPanel().isVisible());
    }

    @Test
    public void startButtonSpawnGuiGame() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.getStartButton().doClick();
        Optional<JFrame> gameFrame = Optional.ofNullable(GuiManager.getGameFrame());
        assertTrue(gameFrame.isPresent());
    }

}
