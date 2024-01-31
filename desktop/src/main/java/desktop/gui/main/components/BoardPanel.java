package desktop.gui.main.components;

import board.Board;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel {

    private final BoardDesktop boardDesktop;
    private final GameDesktop gameDesktop;
    private JPanel boardPanel;
    private static final Color boardBorderColor = new Color(0, 0, 0);
    private static final Color boardLabelColor = new Color(255, 255, 255);
    private static final Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);
    private static final String columnLabels = "ABCDEFGH";

    public BoardPanel(BoardDesktop desktopBoard, GameDesktop desktopGame) {
        this.boardDesktop = desktopBoard;
        this.gameDesktop = desktopGame;
        composeBoardPanel();
        //boardDesktop.addListenerToButtonGrid(this);
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    private void composeBoardPanel() {
        boardPanel = new JPanel(new GridLayout(0, Board.BOARD_SIZE + 1));
        boardPanel.setBorder(new LineBorder(boardBorderColor));
        boardPanel.add(new JLabel(""));

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            JLabel label = new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER);
            label.setFont(boardLabelsFont);
            label.setForeground(boardLabelColor);
            boardPanel.add(label);
        }
        for (int i = 0; i < Board.BOARD_SIZE; i++) {  // why 2 times??
            JLabel label = new JLabel("" + (i + 1), SwingConstants.CENTER);
            label.setFont(boardLabelsFont);
            label.setForeground(boardLabelColor);
            boardPanel.add(label);
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                boardPanel.add(boardDesktop.getButton(i, j));
            }
        }
    }

}
