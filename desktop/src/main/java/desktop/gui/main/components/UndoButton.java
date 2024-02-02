package desktop.gui.main.components;

import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UndoButton {
    public static final Font undoButtonFont = new Font("Arial", Font.PLAIN, 20);
    public static final Color undoButtonColor = new Color(255,255, 255);
    private final JButton undoButton;
    GameDesktop gameDesktop;

    public UndoButton(GameDesktop gameDesktop) {
        ImageIcon undoIcon = new ImageIcon(Objects.requireNonNull(UndoButton.class.getResource("/undo4.png")));
        int UNDO_ICON_SIZE = 25;
        undoIcon.setImage(undoIcon.getImage().getScaledInstance(UNDO_ICON_SIZE, UNDO_ICON_SIZE, Image.SCALE_SMOOTH));
        this.gameDesktop = gameDesktop;
        undoButton = new JButton("Undo");
        undoButton.setIcon(undoIcon);
        undoButton.setFont(undoButtonFont);
        undoButton.setForeground(undoButtonColor);
        addListenerToUndoButton();
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    private void addListenerToUndoButton() {
        undoButton.addActionListener(e->gameDesktop.undoLastMoveDesktop());
    }

    public void setEnabled(boolean enabled) {
        undoButton.setEnabled(enabled);
    }
}
