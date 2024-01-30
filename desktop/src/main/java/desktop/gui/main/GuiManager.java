package desktop.gui.main;

import desktop.gui.main.components.BoardPanel;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.main.components.UndoButton;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GuiManager {
    private final Color borderColor = new Color(0, 0, 0);
    private BoardDesktop boardDesktop;
    private GameDesktop gameDesktop;
    private BoardPanel boardPanel;
    private CurrentPlayerPanel currentPlayerPanel;
    private CurrentScorePanel currentScorePanel;
    private UndoButton undoButton;
    public static JFrame gameFrame;

    public GuiManager(BoardDesktop boardDesktop, GameDesktop gameDesktop) {
        this.boardDesktop = boardDesktop;
        this.gameDesktop = gameDesktop;
        boardPanel = new BoardPanel(boardDesktop, gameDesktop);
        currentPlayerPanel = new CurrentPlayerPanel(boardDesktop, gameDesktop);
        currentScorePanel = new CurrentScorePanel();
        undoButton = new UndoButton(gameDesktop, boardDesktop);

        composeFrame();
    }

    private void composeFrame() {
        gameFrame = new JFrame("Reversi");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(900, 650);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel.getBoardPanel(), BorderLayout.CENTER);
        mainPanel.add(buildRightPanel(), BorderLayout.EAST);

        gameFrame.add(mainPanel);
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
