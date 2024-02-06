package desktop.gui.main;

import desktop.utilities.BoardDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The panel that shows the current player.
 */
public class CurrentPlayerPanel {
    /**
     * The size of the player symbol.
     */
    protected static final int PLAYER_SYMBOL_SIZE = 24;
    /**
     * The font used for players labels in the game frame.
     */
    protected static final Font currentPlayerLabelFont = new Font("Arial", Font.ITALIC, 20);
    /**
     * The color of the font of the current player.
     */
    protected static final Color currentPlayerLabelColor = new Color(255, 255, 255);
    /**
     * The black pawn image.
     */
    protected static final Image blackPawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png"))).getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH);
    /**
     * The white pawn image.
     */
    protected static final Image whitePawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png"))).getImage().getScaledInstance(PLAYER_SYMBOL_SIZE, PLAYER_SYMBOL_SIZE, Image.SCALE_SMOOTH);
    private static JLabel currentPlayerLiveLabel;
    private static ImageIcon blackIcon;
    private static ImageIcon whiteIcon;
    private final JPanel currentPlayerPanel;

    /**
     * Initialize the panel that shows the current player.
     */
    public CurrentPlayerPanel() {
        blackIcon = new ImageIcon(blackPawn);
        whiteIcon = new ImageIcon(whitePawn);
        currentPlayerPanel = new JPanel();
        currentPlayerPanel.setLayout(new GridLayout(0, 1));
        setCurrentPlayerLiveLabel();
        JLabel playerTurnLabel = buildPlayerTurnLabel();
        currentPlayerPanel.add(playerTurnLabel);
        currentPlayerPanel.add(currentPlayerLiveLabel);
    }

    /**
     * Update the label that shows the current player after a move.
     */
    public static void updateCurrentPlayerLiveLabel() {
        String actual = currentPlayerLiveLabel.getText();
        currentPlayerLiveLabel.setIcon(actual.equals("Black") ? whiteIcon : blackIcon);
        currentPlayerLiveLabel.setText(actual.equals("Black") ? "White" : "Black");
    }

    private JLabel buildPlayerTurnLabel() {
        JLabel playerTurnLabel = new JLabel("Current Player: ");
        playerTurnLabel.setFont(currentPlayerLabelFont);
        playerTurnLabel.setForeground(currentPlayerLabelColor);
        return playerTurnLabel;
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

    JPanel getCurrentPlayerPanel() {
        return currentPlayerPanel;
    }
}
