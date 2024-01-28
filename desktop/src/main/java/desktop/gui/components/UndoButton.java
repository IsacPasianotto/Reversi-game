package desktop.gui.components;

import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;

public class UndoButton {
    Font undoButtonFont = new Font("Arial", Font.PLAIN, 20);
    Color undoButtonColor = new Color(255,255, 255);
    private JButton undoButton;
    GameDesktop gameDesktop;
    BoardDesktop boardDesktop;

    public UndoButton(GameDesktop gameDesktop, BoardDesktop boardDesktop) {
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
        undoButton.setFont(undoButtonFont);
        undoButton.setForeground(undoButtonColor);
    }

    private void addListenerToUndoButton() {
        undoButton.addActionListener(gameDesktop.getUndoListener(gameDesktop.getGameControllerDesktop(), boardDesktop));
    }

}
