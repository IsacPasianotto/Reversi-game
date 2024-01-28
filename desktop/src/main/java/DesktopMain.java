import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import player.Player;
import player.human.Human;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;



public class DesktopMain {

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {

        // set the theme with flatlaf
        // FlatDarkLaf.setup();
        // FlatLightLaf.setup();
        //FlatIntelliJLaf.setup();
        //FlatDarculaLaf.setup();

        // da IntelliJ theme:
        FlatGradiantoDeepOceanIJTheme.setup();
        //FlatGitHubIJTheme.setup();
        //FlatCobalt2IJTheme.setup();
        //FlatMaterialLighterIJTheme.setup();

        // TODO: add a panel to select difficulty, bots, etc.


        OltWelcomePanel welcomePanel = new OltWelcomePanel();
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = welcomePanel.frame;
            frame.setVisible(true);
        });

        while (welcomePanel.frame.isVisible()) {
            Thread.sleep(100);
        }
        welcomePanel.frame.dispose();

        Player blackPlayer = new Human();
        Player whitePlayer = new Human();

        DesktopBoard board = new DesktopBoard();

        DesktopGame game = new DesktopGame(board, blackPlayer, whitePlayer);
        GuiManager guiManager = new GuiManager(board, game);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = guiManager.getJFrame();
            frame.setVisible(true);
        });






    }

}
