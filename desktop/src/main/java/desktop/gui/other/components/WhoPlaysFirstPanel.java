package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WhoPlaysFirstPanel {
    private final JPanel whoPlaysFirstPanel;
    private final JRadioButton blackButton;
    private final JRadioButton whiteButton;

    public WhoPlaysFirstPanel(Font labelsFont, Font radioButtonsFont) {
        whoPlaysFirstPanel = new JPanel();
        whoPlaysFirstPanel.setLayout(new BoxLayout(whoPlaysFirstPanel, BoxLayout.Y_AXIS));
        whoPlaysFirstPanel.setBorder(BorderFactory.createTitledBorder("Who plays first?"));
        whoPlaysFirstPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        whoPlaysFirstPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        JLabel whoPlaysFirstLabel = getWhoPlaysFirstLabel(labelsFont);
        whoPlaysFirstPanel.add(whoPlaysFirstLabel);

        ButtonGroup whoPlaysFirstGroup = new ButtonGroup();
        blackButton = getButton("Black",radioButtonsFont);
        whiteButton = getButton("White", radioButtonsFont);

        blackButton.setSelected(true);
        whoPlaysFirstGroup.add(blackButton);
        whoPlaysFirstGroup.add(whiteButton);

        whoPlaysFirstPanel.add(blackButton);
        whoPlaysFirstPanel.add(whiteButton);
    }

    public JPanel getWhoPlaysFirstPanel() {
        return whoPlaysFirstPanel;
    }

    public JRadioButton getWhiteButton() { return whiteButton; }

    public void setActionListenerToBlackButton(ActionListener actionListener){
        blackButton.addActionListener(actionListener);
    }

    public void setActionListenerToWhiteButton(ActionListener actionListener){
        whiteButton.addActionListener(actionListener);
    }

    private JRadioButton getButton(String buttonName, Font radioButtonsFont) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setFont(radioButtonsFont);
        button.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        button.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return button;
    }

    private JLabel getWhoPlaysFirstLabel(Font labelsFont) {
        JLabel whoPlaysFirstLabel = new JLabel("Select your color:");
        whoPlaysFirstLabel.setFont(labelsFont);
        whoPlaysFirstLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        whoPlaysFirstLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return whoPlaysFirstLabel;
    }

}
