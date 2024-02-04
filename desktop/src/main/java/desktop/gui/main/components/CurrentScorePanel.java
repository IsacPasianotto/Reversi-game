package desktop.gui.main.components;

import desktop.gui.main.GuiManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CurrentScorePanel {
    private static final Font currentScoreLabelFont = GuiManager.currentLabelFont;
    private static final Color currentScoreLabelColor = GuiManager.white;
    private static final ImageIcon black = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/black.png")));
    private static final ImageIcon white = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/white.png")));
    private static final int PLAYER_SYMBOL_SIZE = 32;
    private static JLabel liveScoreLabel;
    private final JPanel currentScorePanel;

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

    public static void updateLiveScoreLabel(int blackScore, int whiteScore) {
        liveScoreLabel.setText(blackScore + " - " + whiteScore);
    }

    public JPanel getCurrentScorePanel() {
        return currentScorePanel;
    }
}
