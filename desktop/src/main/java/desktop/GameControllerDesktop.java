package desktop;

import board.Board;
import board.coords.BoardTile;
import mechanics.GameController;

public class GameControllerDesktop extends GameController {

    public GameControllerDesktop(Board board) {
        super(board);
    }

    static void handleButtonPress(BoardTile tile) {

        System.out.println("clicked column " + tile.x() + ", row " + tile.y());
    }

}
