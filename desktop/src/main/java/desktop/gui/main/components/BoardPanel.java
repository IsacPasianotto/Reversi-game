package desktop.gui.main.components;

import board.Board;
import desktop.gui.main.GuiManager;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel {

    private final BoardDesktop boardDesktop;
    private JPanel boardPanel;
    private static final Color boardBorderColor = GuiManager.black;
    private static final Color boardLabelColor = GuiManager.white;
    private static final Font boardLabelsFont = GuiManager.boardLabelsFont;
    private static final String columnLabels = GuiManager.columnLabels;

    public BoardPanel(BoardDesktop desktopBoard) {
        this.boardDesktop = desktopBoard;
        composeBoardPanel();
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

}
