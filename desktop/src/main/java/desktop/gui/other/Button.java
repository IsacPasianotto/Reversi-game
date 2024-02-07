package desktop.gui.other;

import javax.swing.*;
import java.awt.*;

/**
 * A class that manages the construction of buttons for welcome and outcome frames.
 */
public class Button {
    private final JButton startButton;

    /**
     * Initializes the button with the given font and text.
     *
     * @param buttonFont the font to use for the button
     * @param buttonText the text to display on the button
     */
    public Button(Font buttonFont, String buttonText) {
        startButton = new JButton(buttonText);
        startButton.setFont(buttonFont);
        startButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        startButton.setAlignmentY(JLabel.TOP_ALIGNMENT);
        startButton.setEnabled(true);
        startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
    }

    /**
     * Returns the built button.
     *
     * @return the button
     */
    public JButton getButton() {
        return startButton;
    }
}
