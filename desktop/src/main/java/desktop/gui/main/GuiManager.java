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
import java.util.Objects;

public class GuiManager {

    public static final Font arialBoldItalic35 = new Font("Arial", Font.BOLD | Font.ITALIC, 35);
    public static final Color white = new Color(255, 255, 255);
    public static final Color black = new Color(0, 0, 0);
    public static final Font buttonFont = new Font("Arial", Font.BOLD, 20);
    public static final Font currentLabelFont = new Font("Arial", Font.ITALIC, 20);
    public static final ImageIcon blackPawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png")));
    public static final ImageIcon whitePawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png")));
    private static final Color borderColor = new Color(0, 0, 0);
    private static UndoButton undoButton;
    private static BoardPanel boardPanel;
    private static JFrame gameFrame;

    public static void disposeFrame() {
        gameFrame.dispose();
    }

    public GuiManager(GameDesktop gameDesktop, BoardDesktop boardDesktop) {
        boardPanel = new BoardPanel(boardDesktop);
        undoButton = new UndoButton(gameDesktop);
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

        CurrentPlayerPanel currentPlayerPanel1 = new CurrentPlayerPanel();
        CurrentScorePanel currentScorePanel1 = new CurrentScorePanel(2,2);
        JPanel currentPlayerPanel = currentPlayerPanel1.getCurrentPlayerPanel();
        JPanel currentScorePanel = currentScorePanel1.getCurrentScorePanel();

        currentPlayerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPlayerPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        currentScorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentScorePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        rightPanel.add(currentPlayerPanel);
        rightPanel.add(currentScorePanel);
        rightPanel.add(undoButton.getUndoButton(), BorderLayout.SOUTH);

        return rightPanel;
    }

    public void setFrameVisible() {
        gameFrame.setVisible(true);
    }

    public static void disableBoard(){
        undoButton.setEnabled(false);
        boardPanel.setEnabled(false);
    }

    public static void enableBoard(){
        undoButton.setEnabled(true);
        boardPanel.setEnabled(true);
    }

    public static JFrame getGameFrame() { return gameFrame; }
}
