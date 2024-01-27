import board.Board;
import mechanics.Game;
import player.Player;

import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class DesktopGame extends Game{

    public DesktopController controller;
    public DesktopGame(DesktopBoard board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        controller = new DesktopController(board);
    }

}
