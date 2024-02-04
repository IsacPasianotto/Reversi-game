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

    /**
     * The font used for the outcome frame
     */
    public static final Font arialBoldItalic35 = new Font("Arial", Font.BOLD | Font.ITALIC, 35);
    /**
     * White color
     */
    public static final Color white = new Color(255, 255, 255);
    /**
     * Black color
     */
    public static final Color black = new Color(0, 0, 0);
    /**
     * The font used for the welcome and outcome buttons
     */
    public static final Font buttonFont = new Font("Arial", Font.BOLD, 20);
    /**
     * The font used for labels in the game frame
     */
    public static final Font currentLabelFont = new Font("Arial", Font.ITALIC, 20);
    /**
     * Black pawn image
     */
    public static final ImageIcon blackPawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png")));
    /**
     * White pawn image
     */
    public static final ImageIcon whitePawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png")));
    private static final Color borderColor = new Color(0, 0, 0);
    private static final int WIDTH = 900;
    private static final int HEIGHT = 650;
    private static UndoButton undoButton;
    private static BoardPanel boardPanel;
    private static JFrame gameFrame;

    /**
     * Initialize the GUI manager for the game
     * @param gameDesktop the game to initialize
     */
    public GuiManager(GameDesktop gameDesktop) {
        boardPanel = new BoardPanel(gameDesktop.getGameController().getBoard());
        undoButton = new UndoButton(gameDesktop);
        gameFrame = new JFrame("Reversi");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(WIDTH, HEIGHT);

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

    /**
     * Set the game frame visible
     */
    public void setFrameVisible() {
        gameFrame.setVisible(true);
    }

    /**
     * Closes the game frame
     */
    public static void disposeFrame() {
        gameFrame.dispose();
    }

    /**
     * Disables the board to avoid involuntary clicks
     */
    public static void disableBoard(){
        undoButton.setEnabled(false);
        boardPanel.boardDesktop.setEnabled(false);
    }

    /**
     * Re-enable a disabled board
     */
    public static void enableBoard(){
        undoButton.setEnabled(true);
        boardPanel.boardDesktop.setEnabled(true);
    }

    /**
     * Returns the game frame
     * @return the game frame
     */
    public static JFrame getGameFrame() {
        return gameFrame;
    }
}
