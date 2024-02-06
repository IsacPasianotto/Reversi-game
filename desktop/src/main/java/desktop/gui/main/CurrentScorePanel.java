package desktop.gui.main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The panel that shows the current score.
 */
public class CurrentScorePanel {
    /**
     * The size of the player symbol
     */
    protected static final int PLAYER_SYMBOL_SIZE = 32;
    /**
     * The font used for score labels in the game frame
     */
    protected static final Font currentScoreLabelFont = new Font("Arial", Font.ITALIC, 20);
    /**
     * The color of the font
     */
    protected static final Color currentScoreLabelColor = new Color(255, 255, 255);
    /**
     * The image to be shown for the black player
     */
    protected static final ImageIcon black = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/black.png")));
    /**
     * The image to be shown for the white player
     */
    protected static final ImageIcon white = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/white.png")));
    private static JLabel liveScoreLabel;
    private final JPanel currentScorePanel;

    /**
     * Build the current score panel for the game frame
     *
     * @param blackScore black player score
     * @param whiteScore white player score
     */
    public CurrentScorePanel(int blackScore, int whiteScore) {
        black.setImage(black.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        white.setImage(white.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        currentScorePanel = new JPanel(new GridLayout(0, 1));
        currentScorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentScorePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel currentScoreText = buildCurrentScoreTextPanel();
        JPanel liveScorePanel = buildLiveScorePanel(blackScore, whiteScore);
        currentScorePanel.add(currentScoreText);
        currentScorePanel.add(liveScorePanel);
        currentScorePanel.setBorder(BorderFactory.createTitledBorder("Outcome"));
    }

    private static JLabel buildCurrentScoreTextPanel() {
        JLabel currentScoreTextPanel = new JLabel("Current Score: ");
        currentScoreTextPanel.setFont(currentScoreLabelFont);
        currentScoreTextPanel.setForeground(currentScoreLabelColor);
        return currentScoreTextPanel;
    }

    private static JPanel buildLiveScorePanel(int blackScore, int whiteScore) {
        JPanel liveScorePanel = new JPanel(new GridLayout(0, 3));
        liveScorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        liveScorePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        addPlayerIcon(black, liveScorePanel);
        addLiveScoreLabel(blackScore, whiteScore, liveScorePanel);
        addPlayerIcon(white, liveScorePanel);
        return liveScorePanel;
    }

    private static void addPlayerIcon(ImageIcon icon, JPanel livePanel) {
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        livePanel.add(iconLabel);
    }

    private static void addLiveScoreLabel(int blackScore, int whiteScore, JPanel livePanel) {
        liveScoreLabel = new JLabel();
        liveScoreLabel.setFont(currentScoreLabelFont);
        liveScoreLabel.setForeground(currentScoreLabelColor);
        liveScoreLabel.setText(blackScore + " - " + whiteScore);
        liveScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        livePanel.add(liveScoreLabel);
    }

    /**
     * Updates the live score each time a move is done
     *
     * @param blackScore the score of the black player
     * @param whiteScore the score of the white player
     */
    public static void updateLiveScoreLabel(int blackScore, int whiteScore) {
        liveScoreLabel.setText(blackScore + " - " + whiteScore);
    }

    /**
     * Return the built panel
     *
     * @return the built panel
     */
    public JPanel getCurrentScorePanel() {
        return currentScorePanel;
    }
}
