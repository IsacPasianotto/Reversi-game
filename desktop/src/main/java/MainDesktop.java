import board.Board;
import player.Player;
import player.human.Human;

import javax.swing.*;

public class MainDesktop {

    public static void main(String[] args) {
        // TODO: add a panel to select difficulty, bots, etc.

        Player blackPlayer = new Human();
        Player whitePlayer = new Human();

        DesktopBoard board = new DesktopBoard();

        DesktopGame game = new DesktopGame(board, blackPlayer, whitePlayer);
        GuiManager guiManager = new GuiManager(board, game);

        JFrame frame = guiManager.getJFrame();




    }

}
