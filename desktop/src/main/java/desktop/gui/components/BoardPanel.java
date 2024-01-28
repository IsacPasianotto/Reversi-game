package desktop.gui.components;

import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel {

    private BoardDesktop boardDesktop;
    private GameDesktop gameDesktop;
    private JPanel boardPanel;
    private Color boardBorderColor = new Color(0, 0, 0);
    private Color boardLabelColor = new Color(255, 255, 255);
    private Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);
    private static final String columnLabels = "ABCDEFGH";

    public BoardPanel(BoardDesktop desktopBoard, GameDesktop desktopGame) {
        this.boardDesktop = desktopBoard;
        this.gameDesktop = desktopGame;
        composeBoardPanel();
        addListenerToButtonGrid();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    private void composeBoardPanel() {
        boardPanel = new JPanel(new GridLayout(0, boardDesktop.BOARD_SIZE + 1));
        boardPanel.setBorder(new LineBorder(boardBorderColor));
        boardPanel.add(new JLabel(""));

        for (int i = 0; i < boardDesktop.BOARD_SIZE; i++) {
            JLabel label = new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER);
            label.setFont(boardLabelsFont);
            label.setForeground(boardLabelColor);
            boardPanel.add(label);
        }
        for (int i = 0; i < boardDesktop.BOARD_SIZE; i++) {
            JLabel label = new JLabel("" + (i + 1), SwingConstants.CENTER);
            label.setFont(boardLabelsFont);
            label.setForeground(boardLabelColor);
            boardPanel.add(label);
            for (int j = 0; j < boardDesktop.BOARD_SIZE; j++) {
                boardPanel.add(boardDesktop.getButton(i, j));
            }
        }
    }

    private void addListenerToButtonGrid(){
        for (int i = 0; i < boardDesktop.BOARD_SIZE; i++) {
            for (int j = 0; j < boardDesktop.BOARD_SIZE; j++)
                boardDesktop.addListenersAT(i, j, gameDesktop.getButtonListener(i, j));
        }
    }

}
