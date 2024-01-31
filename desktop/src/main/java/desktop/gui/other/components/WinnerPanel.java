package desktop.gui.other.components;

import board.ColoredPawn;
import desktop.gui.main.GuiManager;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WinnerPanel {
    private final JPanel winnerPanel;
    private final Font winnerFont = GuiManager.arialBoldItalic35;
    private final Color fontColor = GuiManager.white;
    private final ImageIcon black = GuiManager.blackPawn;
    private final ImageIcon white = GuiManager.whitePawn;
    private final ImageIcon draw = new ImageIcon(Objects.requireNonNull(WinnerPanel.class.getResource("/blackAndWhite.png")));

    public WinnerPanel(BoardDesktop boardDesktop) {
        int PLAYER_SYMBOL_SIZE = 48;
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

        JLabel winnerLabel = getWinnerLabel(boardDesktop);

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
        winnerLabel.setFont(winnerFont);
        winnerLabel.setForeground(fontColor);
        return winnerLabel;
    }

}
