package desktop.gui.other.components;

import desktop.gui.main.GuiManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WinnerPanel {
    private static final int PLAYER_SYMBOL_SIZE = 48;
    private final JPanel winnerPanel;
    private final Font winnerFont = GuiManager.arialBoldItalic35;
    private final Color fontColor = GuiManager.white;
    private final ImageIcon black = GuiManager.blackPawn;
    private final ImageIcon white = GuiManager.whitePawn;

    private final ImageIcon draw = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/blackAndWhite.png")));

    public WinnerPanel(int blackScore, int whiteScore){
        black.setImage(black.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        white.setImage(white.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        draw.setImage(draw.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        winnerPanel = new JPanel();
        composeWinnerPanel(blackScore, whiteScore);
    }

    public JPanel getWinnerPanel() {
        return winnerPanel;
    }

    private void composeWinnerPanel(int blackScore, int whiteScore) {
        winnerPanel.setLayout(new GridLayout(0, 1));
        winnerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winnerPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel winnerLabel = getWinnerLabel(blackScore, whiteScore);
        winnerPanel.add(winnerLabel);
    }

    private JLabel getWinnerLabel(int blackScore, int whiteScore) {
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

}
