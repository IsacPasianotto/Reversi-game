package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WhoPlaysFirstPanel {
    private final JPanel whoPlaysFirstPanel;
    private final JRadioButton blackButton;
    private final JRadioButton whiteButton;

    /**
     * Initialize the panel to choose which player starts the game in case of Human vs Computer game.
     * @param labelsFont the font of the labels
     * @param radioButtonsFont the font of the buttons
     */
    public WhoPlaysFirstPanel(Font labelsFont, Font radioButtonsFont) {
        whoPlaysFirstPanel = new JPanel();
        whoPlaysFirstPanel.setLayout(new BoxLayout(whoPlaysFirstPanel, BoxLayout.Y_AXIS));
        whoPlaysFirstPanel.setBorder(BorderFactory.createTitledBorder("Who plays first?"));
        whoPlaysFirstPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        whoPlaysFirstPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        JLabel whoPlaysFirstLabel = buildWhoPlaysFirstLabel(labelsFont);
        whoPlaysFirstPanel.add(whoPlaysFirstLabel);

        ButtonGroup whoPlaysFirstGroup = new ButtonGroup();
        blackButton = buildButton("Black",radioButtonsFont);
        whiteButton = buildButton("White", radioButtonsFont);

        blackButton.setSelected(true);
        whoPlaysFirstGroup.add(blackButton);
        whoPlaysFirstGroup.add(whiteButton);

        whoPlaysFirstPanel.add(blackButton);
        whoPlaysFirstPanel.add(whiteButton);
    }

    private JLabel buildWhoPlaysFirstLabel(Font labelsFont) {
        JLabel whoPlaysFirstLabel = new JLabel("Select your color:");
        whoPlaysFirstLabel.setFont(labelsFont);
        whoPlaysFirstLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        whoPlaysFirstLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return whoPlaysFirstLabel;
    }

    private JRadioButton buildButton(String buttonName, Font radioButtonsFont) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setFont(radioButtonsFont);
        button.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        button.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return button;
    }

    /**
     * Set the given listener for the black button.
     * @param actionListener the listener to set
     */
    public void setActionListenerToBlackButton(ActionListener actionListener){
        blackButton.addActionListener(actionListener);
    }
    /**
     * Set the given listener for the white button.
     * @param actionListener the listener to set
     */
    public void setActionListenerToWhiteButton(ActionListener actionListener){
        whiteButton.addActionListener(actionListener);
    }

    /**
     * Returns the built panel.
     */
    public JPanel getWhoPlaysFirstPanel() {
        return whoPlaysFirstPanel;
    }

    /**
     * Return the white button.
     */
    public JRadioButton getWhiteButton() {
        return whiteButton;
    }
}