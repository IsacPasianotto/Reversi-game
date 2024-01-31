package desktop.gui.main.components;

import board.ColoredPawn;
import desktop.gui.main.GuiManager;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameControllerDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CurrentScorePanel {
    private JPanel currentScorePanel;
    private static final Font currentScoreLabelFont = GuiManager.currentLabelFont;
    private static final Color currentScoreLabelColor = GuiManager.white;
    private static JPanel currentScoreLivePanel;
    private static JLabel currentScoreLiveLabel;
    private final ImageIcon black = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/black.png")));
    private final ImageIcon white = new ImageIcon(Objects.requireNonNull(CurrentScorePanel.class.getResource("/white.png")));

    private final int PLAYER_SYMBOL_SIZE = 32;

    public CurrentScorePanel() {
        black.setImage(black.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        white.setImage(white.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        composeCurrentScorePanel();
    }

    public CurrentScorePanel(BoardDesktop board){
        black.setImage(black.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        white.setImage(white.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        composeCurrentScorePanel();
        updateCurrentScoreLiveLabel(new GameControllerDesktop(board).computeScoreForPlayer(ColoredPawn.BLACK),
                new GameControllerDesktop(board).computeScoreForPlayer(ColoredPawn.WHITE));
    }


    public JPanel getCurrentScorePanel() {
        return currentScorePanel;
    }

    private void composeCurrentScorePanel() {
        currentScorePanel = new JPanel();
        currentScorePanel.setLayout(new GridLayout(0, 1));
        currentScorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentScorePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel currentScoreLabel = new JLabel("Current Score: ");
        currentScoreLabel.setFont(currentScoreLabelFont);
        currentScoreLabel.setForeground(currentScoreLabelColor);

        setCurrentScoreLivePanel();

        currentScorePanel.add(currentScoreLabel);
        currentScorePanel.add(currentScoreLivePanel);
    }

    private void setCurrentScoreLivePanel() {
        JPanel livePanel = new JPanel(new GridLayout(0, 3));
        livePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        livePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel whiteIconLabel = new JLabel();
        whiteIconLabel.setIcon(white);
        whiteIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel blackIconLabel = new JLabel();
        blackIconLabel.setIcon(black);
        blackIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        currentScoreLiveLabel = new JLabel();
        currentScoreLiveLabel.setFont(currentScoreLabelFont);
        currentScoreLiveLabel.setForeground(currentScoreLabelColor);
        currentScoreLiveLabel.setText("2 - 2");
        currentScoreLiveLabel.setHorizontalAlignment(SwingConstants.CENTER);

        livePanel.add(blackIconLabel);
        livePanel.add(currentScoreLiveLabel);
        livePanel.add(whiteIconLabel);

        currentScoreLivePanel = livePanel;
    }

    public static void updateCurrentScoreLiveLabel(int blackScore, int whiteScore) {
        currentScoreLiveLabel.setText(blackScore + " - " + whiteScore);
    }
}
