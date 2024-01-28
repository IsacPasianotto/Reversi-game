package desktop.gui.components;

import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;

public class CurrentPlayerPanel {
    private JPanel currentPlayerPanel;
    private Font currentPlayerLabelFont = new Font("Arial", Font.ITALIC, 20);
    private Color currentPlayerLabelColor = new Color(255,255, 255);
    private static JLabel currentPlayerLiveLabel;
    private static ImageIcon blackIcon;
    private static ImageIcon whiteIcon;
    private static final int PLAYER_SYMBOL_SIZE = 24;

    public CurrentPlayerPanel(BoardDesktop desktopBoard, GameDesktop desktopGame) {
        blackIcon = new ImageIcon(desktopBoard.getBlack().getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        whiteIcon = new ImageIcon(desktopBoard.getWhite().getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));

        currentPlayerPanel = new JPanel();

        composeCurrentPlayerPanel();
    }

    public JPanel getCurrentPlayerPanel() {
        return currentPlayerPanel;
    }

    private void composeCurrentPlayerPanel() {
        currentPlayerPanel.setLayout(new GridLayout(0, 1));

        JLabel playerTurnLabel = new JLabel("Current Player: ");
        playerTurnLabel.setFont(currentPlayerLabelFont);
        playerTurnLabel.setForeground(currentPlayerLabelColor);

        setCurrentPlayerLiveLabel();

        currentPlayerPanel.add(playerTurnLabel);
        currentPlayerPanel.add(currentPlayerLiveLabel);
    }

    private void setCurrentPlayerLiveLabel() {
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
        if (actual.equals("Black")) {
            currentPlayerLiveLabel.setIcon(whiteIcon);
            currentPlayerLiveLabel.setText("White");
        } else {
            currentPlayerLiveLabel.setIcon(blackIcon);
            currentPlayerLiveLabel.setText("Black");
            currentPlayerLiveLabel.add(new JLabel(new ImageIcon(new BoardDesktop().getBlack().getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH))));
        }
    }

}
