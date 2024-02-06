package desktop.gui.main;

import desktop.utilities.GameDesktop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * The manager of the game GUI: initializes the game and starts it after settings have been chosen.
 */
public class GuiManager {
    /**
     * The width of the game frame
     */
    protected static final int WIDTH = 900;

    /**
     * The height of the game frame
     */
    protected static final int HEIGHT = 650;

    /**
     * The font used for edges in the game frame
     */
    protected static final Color borderColor = new Color(0, 0, 0);
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
