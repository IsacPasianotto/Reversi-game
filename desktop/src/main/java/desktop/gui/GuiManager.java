package desktop.gui;

import desktop.gui.components.BoardPanel;
import desktop.gui.components.CurrentPlayerPanel;
import desktop.gui.components.CurrentScorePanel;
import desktop.gui.components.UndoButton;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GuiManager {
    private Color borderColor = new Color(0, 0, 0);
    private BoardDesktop boardDesktop;
    private GameDesktop gameDesktop;
    private BoardPanel boardPanel;
    private CurrentPlayerPanel currentPlayerPanel;
    private CurrentScorePanel currentScorePanel;
    private UndoButton undoButton;
    private JFrame frame;

    public GuiManager(BoardDesktop boardDesktop, GameDesktop gameDesktop) {
        this.boardDesktop = boardDesktop;
        this.gameDesktop = gameDesktop;
        boardPanel = new BoardPanel(boardDesktop, gameDesktop);
        currentPlayerPanel = new CurrentPlayerPanel(boardDesktop, gameDesktop);
        currentScorePanel = new CurrentScorePanel();
        undoButton = new UndoButton(gameDesktop, boardDesktop);

        composeFrame();
    }

    public JFrame getJFrame() {
        return frame;
    }

    private void composeFrame() {
        frame = new JFrame("Reversi");
        // TODO --> When there will be a after game screen, this will be changed to dispose on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel.getBoardPanel(), BorderLayout.CENTER);
        mainPanel.add(buildRightPanel(), BorderLayout.EAST);
        // mainPanel.add(undoButton.getUndoButton(), BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    private JPanel buildRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(0, 1));
        rightPanel.setBorder(new LineBorder(borderColor));

        JPanel currentPlayerPanel = this.currentPlayerPanel.getCurrentPlayerPanel();
        JPanel currentScorePanel = this.currentScorePanel.getCurrentScorePanel();

        currentPlayerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPlayerPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        currentScorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentScorePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        rightPanel.add(currentPlayerPanel);
        rightPanel.add(currentScorePanel);
        rightPanel.add(undoButton.getUndoButton(), BorderLayout.SOUTH);

        return rightPanel;
    }
}
