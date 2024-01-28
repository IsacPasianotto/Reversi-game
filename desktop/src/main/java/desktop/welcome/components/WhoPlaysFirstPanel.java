package desktop.welcome.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WhoPlaysFirstPanel {

    private JPanel whoPlaysFirstPanel;
    private JLabel whoPlaysFirstLabel;
    private ButtonGroup whoPlaysFirstGroup;
    private JRadioButton blackButton;
    private JRadioButton whiteButton;

    public WhoPlaysFirstPanel(Font labelsFont, Font radioButtonsFont) {
        buildWhoPlaysFirstPanel(labelsFont, radioButtonsFont);
    }

    public JPanel getWhoPlaysFirstPanel() {
        return whoPlaysFirstPanel;
    }

    private void buildWhoPlaysFirstPanel(Font labelsFont, Font radioButtonsFont){
        whoPlaysFirstPanel = new JPanel();
        whoPlaysFirstPanel.setLayout(new BoxLayout(whoPlaysFirstPanel, BoxLayout.Y_AXIS));
        whoPlaysFirstPanel.setBorder(BorderFactory.createTitledBorder("Who plays first?"));
        whoPlaysFirstPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        whoPlaysFirstPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        whoPlaysFirstLabel = new JLabel("Select your color:");
        whoPlaysFirstLabel.setFont(labelsFont);
        whoPlaysFirstLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        whoPlaysFirstLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        whoPlaysFirstPanel.add(whoPlaysFirstLabel);

        whoPlaysFirstGroup = new ButtonGroup();
        blackButton = new JRadioButton("Black");
        whiteButton = new JRadioButton("White");

        blackButton.setFont(radioButtonsFont);
        whiteButton.setFont(radioButtonsFont);

        blackButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        blackButton.setAlignmentY(JLabel.TOP_ALIGNMENT);
        whiteButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        whiteButton.setAlignmentY(JLabel.TOP_ALIGNMENT);

        blackButton.setSelected(true);
        whoPlaysFirstGroup.add(blackButton);
        whoPlaysFirstGroup.add(whiteButton);

        whoPlaysFirstPanel.add(blackButton);
        whoPlaysFirstPanel.add(whiteButton);
    }

    public void setActionListenerToBlackButton(ActionListener actionListener){
        blackButton.addActionListener(actionListener);
    }
    public void setActionListenerToWhiteButton(ActionListener actionListener){
        whiteButton.addActionListener(actionListener);
    }
}
