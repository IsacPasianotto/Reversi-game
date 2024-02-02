package desktop.gui.main.components;

import desktop.gui.main.GuiManager;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import java.awt.*;

public class CurrentPlayerPanel {
    private final JPanel currentPlayerPanel;
    private static final Font currentPlayerLabelFont = GuiManager.currentLabelFont;
    private static final Color currentPlayerLabelColor = GuiManager.white;
    private static JLabel currentPlayerLiveLabel;
    private static ImageIcon blackIcon;
    private static ImageIcon whiteIcon;
    private static final int PLAYER_SYMBOL_SIZE = 24;

    public CurrentPlayerPanel() {
        blackIcon = new ImageIcon(BoardDesktop.getBlackPawnImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        whiteIcon = new ImageIcon(BoardDesktop.getWhitePawnImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));

        currentPlayerPanel = new JPanel();
        currentPlayerPanel.setLayout(new GridLayout(0, 1));
        setStartingPlayerLabel();
        JLabel playerTurnLabel = getPlayerTurnLabel();
        currentPlayerPanel.add(playerTurnLabel);
        currentPlayerPanel.add(currentPlayerLiveLabel);
    }

    private JLabel getPlayerTurnLabel() {
        JLabel playerTurnLabel = new JLabel("Current Player: ");
        playerTurnLabel.setFont(currentPlayerLabelFont);
        playerTurnLabel.setForeground(currentPlayerLabelColor);
        return playerTurnLabel;
    }

    public JPanel getCurrentPlayerPanel() {
        return currentPlayerPanel;
    }
    
    private void setStartingPlayerLabel() {
        currentPlayerLiveLabel = new JLabel();
        currentPlayerLiveLabel.setFont(currentPlayerLabelFont);
        currentPlayerLiveLabel.setForeground(currentPlayerLabelColor);
        currentPlayerLiveLabel.setIcon(blackIcon);
        currentPlayerLiveLabel.setText("Black");
        currentPlayerLiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayerLiveLabel.setVerticalAlignment(SwingConstants.TOP);
    }

    public static void updateCurrentPlayerLiveLabel() {
        String actual = currentPlayerLiveLabel.getText();
        currentPlayerLiveLabel.setIcon(actual.equals("Black") ? whiteIcon : blackIcon);
        currentPlayerLiveLabel.setText(actual.equals("Black") ? "White" : "Black");
    }
}
