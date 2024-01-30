package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;

public class Button {

    JButton startButton;

    public Button(Font buttonFont, String buttonText) {
        buildButton(buttonFont, buttonText);
    }

    public JButton getButton() {
        return startButton;
    }

    private void buildButton(Font buttonFont, String buttonText) {
        startButton = new JButton(buttonText);
        startButton.setFont(buttonFont);
        startButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        startButton.setAlignmentY(JLabel.TOP_ALIGNMENT);

        startButton.setEnabled(true);
        startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
    }
}
