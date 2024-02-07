package desktop.gui.main;

import board.Board;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.stream.IntStream;

/**
 * A class that manages the construction of the board panel.
 */
public class BoardPanel {
    /**
     * The color of the board edges.
     */
    protected static final Color boardBorderColor = new Color(0, 0, 0);

    /**
     * The font used for rows and columns labels.
     */
    protected static final Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);

    /**
     * The color used for rows and columns labels.
     */
    protected static final Color boardLabelColor = new Color(255, 255, 255);
    private static final String columnLabels = "ABCDEFGH";
    final BoardDesktop boardDesktop;
    private final JPanel boardPanel;

    /**
     * Build the board panel for the desktop game, starting from the information provided by a BoardDesktop.
     *
     * @param board the board of the game
     * @see BoardDesktop
     */
    public BoardPanel(BoardDesktop board) {
        boardDesktop = board;
        boardPanel = new JPanel(new GridLayout(0, Board.BOARD_SIZE + 1));
        boardPanel.setBorder(new LineBorder(boardBorderColor));
        boardPanel.add(new JLabel(""));
        for (int column = 0; column < Board.BOARD_SIZE; column++)
            buildLabel(columnLabels.substring(column, column + 1));
        IntStream.range(0, Board.BOARD_SIZE).forEach(rowIndex -> {
            buildLabel("" + (rowIndex + 1));
            addLineOfButtons(rowIndex);
        });
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
