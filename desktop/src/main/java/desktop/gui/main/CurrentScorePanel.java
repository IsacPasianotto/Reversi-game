package desktop.gui.main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A class that manages the construction and update of the live score panel.
 */
public class CurrentScorePanel {
    /**
     * The size of the player symbol.
     */
    protected static final int PLAYER_SYMBOL_SIZE = 32;
    /**
     * The font used for score labels in this panel.
     */
    protected static final Font currentScoreLabelFont = new Font("Arial", Font.ITALIC, 20);
    /**
     * The color used for score labels in this panel.
     */
    protected static final Color currentScoreLabelColor = new Color(255, 255, 255);
    /**
     * The image to be shown for the black player
     */
    protected static final ImageIcon black = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/black.png")));
    /**
     * The image to be shown for the white player.
     */
    protected static final ImageIcon white = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/white.png")));
    private static JLabel liveScoreLabel;
    private final JPanel currentScorePanel;

    /**
     * Build the current score panel for the game frame, starting from the current scores.
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

    private JLabel buildCurrentScoreTextPanel() {
        JLabel currentScoreTextPanel = new JLabel("Current Score: ");
        currentScoreTextPanel.setFont(currentScoreLabelFont);
        currentScoreTextPanel.setForeground(currentScoreLabelColor);
        return currentScoreTextPanel;
    }

    private JPanel buildLiveScorePanel(int blackScore, int whiteScore) {
        JPanel liveScorePanel = new JPanel(new GridLayout(0, 3));
        liveScorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        liveScorePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        liveScorePanel.add(buildPlayerIcon(black));
        liveScoreLabel = buildLiveScoreLabel(blackScore, whiteScore);
        liveScorePanel.add(liveScoreLabel);
        liveScorePanel.add(buildPlayerIcon(white));
        return liveScorePanel;
    }

    private JLabel buildPlayerIcon(ImageIcon icon) {
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return iconLabel;
    }

    private JLabel buildLiveScoreLabel(int blackScore, int whiteScore) {
        JLabel liveLabel = new JLabel();
        liveLabel.setFont(currentScoreLabelFont);
        liveLabel.setForeground(currentScoreLabelColor);
        liveLabel.setText(blackScore + " - " + whiteScore);
        liveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return liveLabel;
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
