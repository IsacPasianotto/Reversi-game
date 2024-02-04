package desktop.gui.main.components;

import board.Board;
import desktop.gui.main.GuiManager;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel {
    private static final Color boardBorderColor = GuiManager.black;
    private static final Color boardLabelColor = GuiManager.white;
    private static final Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);
    private static final String columnLabels = "ABCDEFGH";
    private final BoardDesktop boardDesktop;
    private final JPanel boardPanel;

    public BoardPanel(BoardDesktop board) {
        boardDesktop = board;
        boardPanel = new JPanel(new GridLayout(0, Board.BOARD_SIZE + 1));
        boardPanel.setBorder(new LineBorder(boardBorderColor));
        boardPanel.add(new JLabel(""));
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            buildLabel(columnLabels.substring(i, i + 1));
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            buildLabel("" + (i + 1));
            for (int j = 0; j < Board.BOARD_SIZE; j++)
                boardPanel.add(boardDesktop.getButton(i, j));
        }
    }

    private void buildLabel(String stringLabel) {
        JLabel label = new JLabel(stringLabel, SwingConstants.CENTER);
        label.setFont(boardLabelsFont);
        label.setForeground(boardLabelColor);
        boardPanel.add(label);
    }

    public void setEnabled(boolean enabled) {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++)
                boardDesktop.getButton(i, j).setEnabled(enabled);
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
