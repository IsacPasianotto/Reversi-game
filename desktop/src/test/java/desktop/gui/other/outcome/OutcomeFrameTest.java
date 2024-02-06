package desktop.gui.other.outcome;

import board.ColoredPawn;
import desktop.gui.main.GuiManager;
import desktop.gui.other.welcome.WelcomeFrame;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;
import org.junit.jupiter.api.Test;
import player.computer.SmartPlayer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutcomeFrameTest {

    private final int SCORE_BLACK = 2;
    private final int SCORE_WHITE = 2;

    @Test
    public void playAgainButtonSpawnWelcomeFrame() {
        OutcomeFrame outcomeFrame = new OutcomeFrame(SCORE_BLACK, SCORE_WHITE);

        // OutcomeFrame will try to dispose the game, so we need to create it to prevent a null pointer exception
        GameDesktop gameDesktop = new GameDesktop(new BoardDesktop(), new SmartPlayer(ColoredPawn.BLACK), new SmartPlayer(ColoredPawn.WHITE));
        outcomeFrame.getPlayAgainButton().doClick();
        assertTrue(WelcomeFrame.getWelcomeFrame().isVisible());
    }

    @Test
    public void closeButtonTerminatesApp() {
        OutcomeFrame outcomeFrame = new OutcomeFrame(SCORE_BLACK, SCORE_WHITE);
        GameDesktop gameDesktop = new GameDesktop(new BoardDesktop(), new SmartPlayer(ColoredPawn.BLACK), new SmartPlayer(ColoredPawn.WHITE));
        outcomeFrame.getCloseButton().doClick();
        assertFalse(GuiManager.getGameFrame().isVisible());
    }
}
