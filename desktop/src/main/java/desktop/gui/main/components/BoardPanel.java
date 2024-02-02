package desktop.gui.main.components;

import board.Board;
import desktop.gui.main.GuiManager;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel {

    private final BoardDesktop boardDesktop;
    private final JPanel boardPanel;
    private static final Color boardBorderColor = GuiManager.black;
    private static final Color boardLabelColor = GuiManager.white;
    private static final Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);
    private static final String columnLabels = "ABCDEFGH";

    public BoardPanel(BoardDesktop desktopBoard) {
        this.boardDesktop = desktopBoard;
        boardPanel = new JPanel(new GridLayout(0, Board.BOARD_SIZE + 1));
        boardPanel.setBorder(new LineBorder(boardBorderColor));
        boardPanel.add(new JLabel(""));

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            JLabel label = new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER);
            label.setFont(boardLabelsFont);
            label.setForeground(boardLabelColor);
            boardPanel.add(label);
        }
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            JLabel label = new JLabel("" + (i + 1), SwingConstants.CENTER);
            label.setFont(boardLabelsFont);
            label.setForeground(boardLabelColor);
            boardPanel.add(label);
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                boardPanel.add(boardDesktop.getButton(i, j));
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public void setEnabled(boolean enabled) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                boardDesktop.getButton(i, j).setEnabled(enabled);
            }
        }
    }

}
