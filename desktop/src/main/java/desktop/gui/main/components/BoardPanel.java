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
    public final BoardDesktop boardDesktop;
    private final JPanel boardPanel;

    /**
     * Initialize the panel of the board.
     * @param board the board of the game
     */
    public BoardPanel(BoardDesktop board) {
        boardDesktop = board;
        boardPanel = new JPanel(new GridLayout(0, Board.BOARD_SIZE + 1));
        boardPanel.setBorder(new LineBorder(boardBorderColor));
        boardPanel.add(new JLabel(""));
        for (int column = 0; column < Board.BOARD_SIZE; column++)
            buildLabel(columnLabels.substring(column, column + 1));
        for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {
            buildLabel("" + (rowIndex + 1));
            addLineOfButtons(rowIndex);
        }
    }

    private void addLineOfButtons(int i) {
        for (int j = 0; j < Board.BOARD_SIZE; j++)
            boardPanel.add(boardDesktop.getButton(i, j));
    }

    private void buildLabel(String stringLabel) {

        JLabel label = new JLabel(stringLabel, SwingConstants.CENTER);
        label.setFont(boardLabelsFont);
        label.setForeground(boardLabelColor);
        boardPanel.add(label);
    }

    /**
     * Returns the panel of the board.
     * @return the panel of the board
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
