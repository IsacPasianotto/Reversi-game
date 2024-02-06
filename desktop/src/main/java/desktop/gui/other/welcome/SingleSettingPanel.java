package desktop.gui.other.welcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class SingleSettingPanel {
    protected static final Font LabelsFont = new Font("Arial", Font.ITALIC,18 );
    protected static final Font RadioButtonsFont = new Font("Arial", Font.BOLD, 15);
    private final JPanel settingPanel;
    private final JRadioButton[] buttons;

    SingleSettingPanel(int numOfButtons, String title, String text, String[] buttonTexts) {
        buttons = new JRadioButton[numOfButtons];
        settingPanel = new JPanel();
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        settingPanel.setBorder(BorderFactory.createTitledBorder(title));
        settingPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        settingPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        JLabel settingLabel = buildSettingLabel(text);
        settingPanel.add(settingLabel);

        ButtonGroup gameModeGroup = new ButtonGroup();
        for (int i = 0; i< numOfButtons; i++){
            buttons[i] = buildButton(buttonTexts[i]);
            gameModeGroup.add(buttons[i]);
        }
        buttons[0].setSelected(true);
        for (int i = 0; i< numOfButtons; i++)
            settingPanel.add(buttons[i]);
    }

    private JLabel buildSettingLabel(String labelText) {
        JLabel gameModeLabel = new JLabel(labelText);
        gameModeLabel.setFont(LabelsFont);
        gameModeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        gameModeLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return gameModeLabel;
    }

    private JRadioButton buildButton(String buttonName) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setFont(RadioButtonsFont);
        button.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        button.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return button;
    }

    void setActionListenerToIthButton(int i, ActionListener actionListener){
        buttons[i].addActionListener(actionListener);
    }

    JPanel getPanel() {
        return settingPanel;
    }

    JRadioButton getIthButton(int i){
        return buttons[i];
    }

    void setVisible(boolean visible){
        settingPanel.setVisible(visible);
    }
}
