import desktop.gui.main.GuiManager;
import desktop.gui.other.OutcomeFrame;
import desktop.gui.other.WelcomeFrame;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;
import org.junit.jupiter.api.Test;
import player.computer.SmartPlayer;

import static org.junit.jupiter.api.Assertions.*;

public class OutcomeFrameTest {

    private final int SCORE_BLACK = 2;
    private final int SCORE_WHITE = 2;

    @Test
    public void playAgainButtonSpawnWelcomeFrame() {
        OutcomeFrame outcomeFrame = new OutcomeFrame(SCORE_BLACK, SCORE_WHITE);

        // OutcomeFrame will try to dispose the GuiManager frame, so we need to create it to prevent a null pointer exception
        BoardDesktop boardDesktop = new BoardDesktop();
        GameDesktop gameDesktop = new GameDesktop(boardDesktop, new SmartPlayer(), new SmartPlayer());
        GuiManager guiManager = new GuiManager(gameDesktop, boardDesktop);

        outcomeFrame.getPlayAgainButton().doClick();
        assertTrue(WelcomeFrame.getWelcomeFrame().isVisible());
    }

    @Test
    public void closeButtonTerminatesApp() {
        OutcomeFrame outcomeFrame = new OutcomeFrame(SCORE_BLACK, SCORE_WHITE);

        BoardDesktop boardDesktop = new BoardDesktop();
        GameDesktop gameDesktop = new GameDesktop(boardDesktop, new SmartPlayer(), new SmartPlayer());
        GuiManager guiManager = new GuiManager(gameDesktop, boardDesktop);

        outcomeFrame.getCloseButton().doClick();
        assertFalse(GuiManager.getGameFrame().isVisible());
    }

}
