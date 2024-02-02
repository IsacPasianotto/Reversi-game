package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameModePanel {
    private final JPanel gameModePanel;
    private final JRadioButton humanVsHumanButton;
    private final JRadioButton humanVsComputerButton;
    //private JRadioButton computerVsComputerButton;


    public GameModePanel(Font labelsFont, Font radioButtonsFont) {
        gameModePanel = new JPanel();
        gameModePanel.setLayout(new BoxLayout(gameModePanel, BoxLayout.Y_AXIS));
        gameModePanel.setBorder(BorderFactory.createTitledBorder("Game Mode"));
        gameModePanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        gameModePanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        JLabel gameModeLabel = getGameModeLabel(labelsFont);
        gameModePanel.add(gameModeLabel);

        ButtonGroup gameModeGroup = new ButtonGroup();
        humanVsHumanButton = getButton("Human vs Human", radioButtonsFont);
        humanVsComputerButton = getButton("Human vs Computer", radioButtonsFont);
        //computerVsComputerButton = getButton("Computer vs Computer",radioButtonsFont);

        humanVsComputerButton.setSelected(true);
        gameModeGroup.add(humanVsHumanButton);
        gameModeGroup.add(humanVsComputerButton);
        //gameModeGroup.add(computerVsComputerButton);

        gameModePanel.add(humanVsHumanButton);
        gameModePanel.add(humanVsComputerButton);
        //gameModePanel.add(computerVsComputerButton);
    }

    public JPanel getGameModePanel() {
        return gameModePanel;
    }

    public JRadioButton getHumanVsHumanButton() { return humanVsHumanButton; }

    public void setActionListenerToHumanVsHumanButton(ActionListener actionListener){
        humanVsHumanButton.addActionListener(actionListener);
    }

    public void setActionListenerToHumanVsComputerButton(ActionListener actionListener){
        humanVsComputerButton.addActionListener(actionListener);
    }

    private JRadioButton getButton(String buttonName, Font radioButtonsFont) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setFont(radioButtonsFont);
        button.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        button.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return button;
    }

    private JLabel getGameModeLabel(Font labelsFont) {
        JLabel gameModeLabel = new JLabel("Select a game mode:");
        gameModeLabel.setFont(labelsFont);
        gameModeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        gameModeLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return gameModeLabel;
    }

}
