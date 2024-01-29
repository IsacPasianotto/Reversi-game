package desktop.gui.main.components;

import board.ColoredPawn;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WinnerPanel {
    private JPanel winnerPanel;
    private JLabel winnerLabel;
    private Font winnerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 35);
    private Color fontColor = new Color(255, 255, 255);
    private final ImageIcon black = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/black.png")));
    private final ImageIcon white = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/white.png")));
    private final ImageIcon draw = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/blackAndWhite.png")));
    private final int PLAYER_SYMBOL_SIZE = 48;

    public WinnerPanel(BoardDesktop boardDesktop) {
        black.setImage(black.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        white.setImage(white.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        draw.setImage(draw.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        winnerPanel = new JPanel();
        composeWinnerPanel(boardDesktop);
    }

    public JPanel getWinnerPanel() {
        return winnerPanel;
    }

    private void composeWinnerPanel(BoardDesktop boardDesktop) {
        winnerPanel.setLayout(new GridLayout(0, 1));
        winnerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winnerPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        winnerLabel = getWinnerLabel(boardDesktop);
        winnerLabel.setFont(winnerFont);
        winnerLabel.setForeground(fontColor);
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        winnerPanel.add(winnerLabel);
    }

    private JLabel getWinnerLabel(BoardDesktop boardDesktop) {
        JLabel winnerLabel = new JLabel();
        winnerLabel.setFont(winnerFont);
        winnerLabel.setForeground(fontColor);
        int blackScore = boardDesktop.computeScoreForPlayer(ColoredPawn.BLACK);
        int whiteScore = boardDesktop.computeScoreForPlayer(ColoredPawn.WHITE);
        if (blackScore == whiteScore) {
            winnerLabel.setText("Draw");
            winnerLabel.setIcon(draw);
        }
        if (blackScore > whiteScore) {
            winnerLabel.setText("Black Wins");
            winnerLabel.setIcon(black);
        }
        if (blackScore < whiteScore) {
            winnerLabel.setText("White Wins");
            winnerLabel.setIcon(white);
        }

        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return winnerLabel;
    }

}
