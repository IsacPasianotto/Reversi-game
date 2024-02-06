package desktop.gui.main;

import board.Board;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * The panel containing the game board.
 */
public class BoardPanel {
    /**
     * The color of the board edges
     */
    protected static final Color boardBorderColor = new Color(0, 0, 0);

    /**
     * The font used for the board labels
     */
    protected static final Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);

    /**
     * The color of the board labels
     */
    protected static final Color boardLabelColor = new Color(255, 255, 255);
    private static final String columnLabels = "ABCDEFGH";
    final BoardDesktop boardDesktop;
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

    JPanel getBoardPanel() {
        return boardPanel;
    }
}
