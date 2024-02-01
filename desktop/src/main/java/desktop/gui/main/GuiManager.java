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
    public static final Font boardLabelsFont = new Font("Arial", Font.BOLD, 25);

    public static final ImageIcon blackPawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png")));
    public static final ImageIcon whitePawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png")));

    public static final String columnLabels = "ABCDEFGH";

    private static final Color borderColor = new Color(0, 0, 0);
    private final CurrentPlayerPanel currentPlayerPanel;
    private final CurrentScorePanel currentScorePanel;
    private final UndoButton undoButton;
    private final JFrame gameFrame;

    public GuiManager(GameDesktop gameDesktop, BoardDesktop boardDesktop) {
        BoardPanel boardPanel = new BoardPanel(boardDesktop);
        currentPlayerPanel = new CurrentPlayerPanel(boardDesktop);
        currentScorePanel = new CurrentScorePanel();
        undoButton = new UndoButton(gameDesktop, boardDesktop);
        gameFrame = new JFrame("Reversi");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(900, 650);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel.getBoardPanel(), BorderLayout.CENTER);
        mainPanel.add(buildRightPanel(), BorderLayout.EAST);

        gameFrame.add(mainPanel);
    }

    public void setFrameVisible() {
        gameFrame.setVisible(true);
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
