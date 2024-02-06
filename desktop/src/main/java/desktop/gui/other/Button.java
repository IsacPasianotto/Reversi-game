package desktop.gui.other;

import javax.swing.*;
import java.awt.*;

/**
 * The button for the welcome frame
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
     * Returns the button.
     *
     * @return the button
     */
    public JButton getButton() {
        return startButton;
    }
}
