package desktop.welcome.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameModePanel {
    private JPanel gameModePanel;
    private JLabel gameModeLabel;
    private ButtonGroup gameModeGroup;
    private JRadioButton humanVsHumanButton;
    private JRadioButton humanVsComputerButton;
    private JRadioButton computerVsComputerButton;


    public GameModePanel(Font labelsFont, Font radioButtonsFont) {
        buildGameModePanel(labelsFont, radioButtonsFont);
    }

    public JPanel getGameModePanel() {
        return gameModePanel;
    }

    public void buildGameModePanel(Font labelsFont, Font radioButtonsFont){
        gameModePanel = new JPanel();
        gameModePanel.setLayout(new BoxLayout(gameModePanel, BoxLayout.Y_AXIS));
        gameModePanel.setBorder(BorderFactory.createTitledBorder("Game Mode"));
        gameModePanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        gameModePanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        gameModeLabel = new JLabel("Select a game mode:");
        gameModeLabel.setFont(labelsFont);
        gameModeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        gameModeLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        gameModePanel.add(gameModeLabel);

        gameModeGroup = new ButtonGroup();
        humanVsHumanButton = new JRadioButton("Human vs Human");
        humanVsComputerButton = new JRadioButton("Human vs Computer");
        //computerVsComputerButton = new JRadioButton("Computer vs Computer");

        humanVsHumanButton.setFont(radioButtonsFont);
        humanVsComputerButton.setFont(radioButtonsFont);
        //computerVsComputerButton.setFont(radioButtonsFont);

        humanVsComputerButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        humanVsComputerButton.setAlignmentY(JLabel.TOP_ALIGNMENT);
        humanVsHumanButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        humanVsHumanButton.setAlignmentY(JLabel.TOP_ALIGNMENT);
        //computerVsComputerButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        //computerVsComputerButton.setAlignmentY(JLabel.TOP_ALIGNMENT);

        humanVsComputerButton.setSelected(true);
        gameModeGroup.add(humanVsHumanButton);
        gameModeGroup.add(humanVsComputerButton);
        //gameModeGroup.add(computerVsComputerButton);

        gameModePanel.add(humanVsHumanButton);
        gameModePanel.add(humanVsComputerButton);
        //gameModePanel.add(computerVsComputerButton);
    }

    public void setActionListenerToHumanVsHumanButton(ActionListener actionListener){
        humanVsHumanButton.addActionListener(actionListener);
    }

    public void setActionListenerToHumanVsComputerButton(ActionListener actionListener){
        humanVsComputerButton.addActionListener(actionListener);
    }

    public void setActionListenerToComputerVsComputerButton(ActionListener actionListener){
        computerVsComputerButton.addActionListener(actionListener);
    }

}
