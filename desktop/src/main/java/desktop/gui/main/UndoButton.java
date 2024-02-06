package desktop.gui.main;

import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The undo button for the game frame
 */
public class UndoButton {
    /**
     * The size of the undo button icon
     */
    protected static final int UNDO_ICON_SIZE = 25;

    /**
     * The font used for the undo button
     */
    protected static final Font undoButtonFont = new Font("Arial", Font.PLAIN, 20);

    /**
     * The color of the button
     */
    protected static final Color undoButtonColor = new Color(255, 255, 255);
    private final JButton undoButton;

    /**
     * Build the Undo button for the game frame
     * @param gameDesktop the game
     */
    public UndoButton(GameDesktop gameDesktop) {
        ImageIcon undoIcon = new ImageIcon(Objects.requireNonNull(UndoButton.class.getResource("/undo.png")));
        undoIcon.setImage(undoIcon.getImage().getScaledInstance(UNDO_ICON_SIZE, UNDO_ICON_SIZE, Image.SCALE_SMOOTH));
        undoButton = new JButton("Undo");
        undoButton.setIcon(undoIcon);
        undoButton.setFont(undoButtonFont);
        undoButton.setForeground(undoButtonColor);
        undoButton.addActionListener(e->gameDesktop.undoLastMove());
    }

    JButton getUndoButton() {
        return undoButton;
    }

    void setEnabled(boolean enabled) {
        undoButton.setEnabled(enabled);
    }
}