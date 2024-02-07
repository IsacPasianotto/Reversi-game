package desktop.gui.other.outcome;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class WinnerPanel {
    protected static final int PLAYER_SYMBOL_SIZE = 48;
    protected static final Font winnerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 35);
    protected static final Color fontColor = new Color(255, 255, 255);
    protected static final ImageIcon black = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/black.png")));
    protected static final ImageIcon white = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/white.png")));
    protected static final ImageIcon draw = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/blackAndWhite.png")));
    private JPanel winnerPanel;

    public WinnerPanel(int blackScore, int whiteScore) {
        black.setImage(black.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        white.setImage(white.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        draw.setImage(draw.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        composeWinnerPanel(blackScore, whiteScore);
    }

    private void composeWinnerPanel(int blackScore, int whiteScore) {
        winnerPanel = new JPanel();
        winnerPanel.setLayout(new GridLayout(0, 1));
        winnerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winnerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        JLabel winnerLabel = buildWinnerLabel(blackScore, whiteScore);
        winnerPanel.add(winnerLabel);
        winnerPanel.setBorder(BorderFactory.createTitledBorder("The winner is..."));
    }

    private JLabel buildWinnerLabel(int blackScore, int whiteScore) {
        JLabel winnerLabel = new JLabel();
        winnerLabel.setFont(winnerFont);
        winnerLabel.setForeground(fontColor);
        winnerLabel.setText(whiteScore > blackScore ? "White wins!" : whiteScore < blackScore ? "Black wins!" : "Draw!");
        winnerLabel.setIcon(whiteScore > blackScore ? white : whiteScore < blackScore ? black : draw);
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setFont(winnerFont);
        winnerLabel.setForeground(fontColor);
        return winnerLabel;
    }

    public JPanel getWinnerPanel() {
        return winnerPanel;
    }
}
