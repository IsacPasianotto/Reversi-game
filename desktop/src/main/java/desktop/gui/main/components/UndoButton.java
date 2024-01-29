package desktop.gui.main.components;

import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UndoButton {
    Font undoButtonFont = new Font("Arial", Font.PLAIN, 20);
    Color undoButtonColor = new Color(255,255, 255);
    private final ImageIcon undoIcon = new ImageIcon(Objects.requireNonNull(UndoButton.class.getResource("/undo4.png")));
    private final int UNDO_ICON_SIZE = 25;
    private JButton undoButton;
    GameDesktop gameDesktop;
    BoardDesktop boardDesktop;

    public UndoButton(GameDesktop gameDesktop, BoardDesktop boardDesktop) {
        undoIcon.setImage(undoIcon.getImage().getScaledInstance(UNDO_ICON_SIZE, UNDO_ICON_SIZE, Image.SCALE_SMOOTH));
        this.gameDesktop = gameDesktop;
        this.boardDesktop = boardDesktop;
        composeUndoButton();
        addListenerToUndoButton();
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    private void composeUndoButton() {
        undoButton = new JButton("Undo");
        undoButton.setIcon(undoIcon);
        undoButton.setFont(undoButtonFont);
        undoButton.setForeground(undoButtonColor);
    }

    private void addListenerToUndoButton() {
        undoButton.addActionListener(gameDesktop.getUndoListener(gameDesktop.getGameControllerDesktop(), boardDesktop));
    }

}
