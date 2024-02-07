package desktop.gui.main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A class that manages the construction and update of the current player panel.
 */
public class CurrentPlayerPanel {
    /**
     * The size of the player symbol.
     */
    protected static final int PLAYER_SYMBOL_SIZE = 24;
    /**
     * The font used for labels in this panel.
     */
    protected static final Font currentPlayerLabelFont = new Font("Arial", Font.ITALIC, 20);
    /**
     * The color used for labels in this panel.
     */
    protected static final Color currentPlayerLabelColor = new Color(255, 255, 255);
    /**
     * The black pawn image.
     */
    protected static final ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(CurrentPlayerPanel.class.getResource("/black.png")));

    /**
     * The white pawn image.
     */
    protected static final ImageIcon whiteIcon= new ImageIcon(Objects.requireNonNull(CurrentPlayerPanel.class.getResource("/white.png")));
    private static JLabel currentPlayerColorLabel;
    private final JPanel currentPlayerPanel;

    /**
     * Build the current player panel for the desktop game.
     */
    public CurrentPlayerPanel() {
        blackIcon.setImage(blackIcon.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        whiteIcon.setImage(whiteIcon.getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH));
        currentPlayerPanel = new JPanel();
        currentPlayerPanel.setLayout(new GridLayout(0, 1));
        setCurrentPlayerColorLabel();
        JLabel currentPlayerLabel = buildCurrentPlayerLabel();
        currentPlayerPanel.add(currentPlayerLabel);
        currentPlayerPanel.add(currentPlayerColorLabel);
    }

    /**
     * Update the label that shows the current player after a move.
     */
    public static void updateCurrentPlayerColorLabel() {
        String actual = currentPlayerColorLabel.getText();
        currentPlayerColorLabel.setIcon(actual.equals("Black") ? whiteIcon : blackIcon);
        currentPlayerColorLabel.setText(actual.equals("Black") ? "White" : "Black");
    }

    private JLabel buildCurrentPlayerLabel() {
        JLabel currentPlayerLabel = new JLabel("Current Player: ");
        currentPlayerLabel.setFont(currentPlayerLabelFont);
        currentPlayerLabel.setForeground(currentPlayerLabelColor);
        return currentPlayerLabel;
    }

    private void setCurrentPlayerColorLabel() {
        currentPlayerColorLabel = new JLabel();
        currentPlayerColorLabel.setFont(currentPlayerLabelFont);
        currentPlayerColorLabel.setForeground(currentPlayerLabelColor);
        currentPlayerColorLabel.setIcon(blackIcon);
        currentPlayerColorLabel.setText("Black");
        currentPlayerColorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayerColorLabel.setVerticalAlignment(SwingConstants.TOP);
    }

    JPanel getCurrentPlayerPanel() {
        return currentPlayerPanel;
    }
}
