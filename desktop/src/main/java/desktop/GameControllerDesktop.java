package desktop;

import board.Board;
import board.coords.BoardTile;
import mechanics.GameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameControllerDesktop extends GameController {
    static class MyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceBtn = (JButton) e.getSource();
            handleButtonPress(new BoardTile((int) sourceBtn.getClientProperty("column"), (int) sourceBtn.getClientProperty("row")));
        }
    }
    public GameControllerDesktop(BoardDesktop board) {
        super(board);
    }

    static void handleButtonPress(BoardTile tile) {
        System.out.println("clicked column " + tile.x() + ", row " + tile.y());
    }

}
