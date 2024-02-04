package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameModePanel {
    private final JPanel gameModePanel;
    private final JRadioButton humanVsHumanButton;
    private final JRadioButton humanVsComputerButton;

    public GameModePanel(Font labelsFont, Font radioButtonsFont) {
        gameModePanel = new JPanel();
        gameModePanel.setLayout(new BoxLayout(gameModePanel, BoxLayout.Y_AXIS));
        gameModePanel.setBorder(BorderFactory.createTitledBorder("Game Mode"));
        gameModePanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        gameModePanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        JLabel gameModeLabel = buildGameModeLabel(labelsFont);
        gameModePanel.add(gameModeLabel);

        ButtonGroup gameModeGroup = new ButtonGroup();
        humanVsHumanButton = buildButton("Human vs Human", radioButtonsFont);
        humanVsComputerButton = buildButton("Human vs Computer", radioButtonsFont);
        humanVsComputerButton.setSelected(true);
        gameModeGroup.add(humanVsHumanButton);
        gameModeGroup.add(humanVsComputerButton);

        gameModePanel.add(humanVsHumanButton);
        gameModePanel.add(humanVsComputerButton);
    }

    private JLabel buildGameModeLabel(Font labelsFont) {
        JLabel gameModeLabel = new JLabel("Select a game mode:");
        gameModeLabel.setFont(labelsFont);
        gameModeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        gameModeLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return gameModeLabel;
    }

    private JRadioButton buildButton(String buttonName, Font radioButtonsFont) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setFont(radioButtonsFont);
        button.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        button.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return button;
    }

    public void setActionListenerToHumanVsHumanButton(ActionListener actionListener){
        humanVsHumanButton.addActionListener(actionListener);
    }

    public void setActionListenerToHumanVsComputerButton(ActionListener actionListener){
        humanVsComputerButton.addActionListener(actionListener);
    }

    public JPanel getGameModePanel() {
        return gameModePanel;
    }

    public JRadioButton getHumanVsHumanButton() {
        return humanVsHumanButton;
    }
}