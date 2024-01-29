package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DifficultyPanel {

    private JPanel difficultyPanel;
    private JLabel difficultyLabel;
    private ButtonGroup difficultyGroup;
    private JRadioButton easyButton;
    private JRadioButton hardButton;

    public DifficultyPanel(Font labelsFont, Font radioButtonsFont) {
        buildDifficultyPanel(labelsFont, radioButtonsFont);
    }

    public JPanel getDifficultyPanel() {
        return difficultyPanel;
    }

    private void buildDifficultyPanel(Font labelsFont, Font radioButtonsFont) {
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
        difficultyPanel.setBorder(BorderFactory.createTitledBorder("Difficulty"));
        difficultyPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        difficultyPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        difficultyLabel = new JLabel("Select a difficulty:");
        difficultyLabel.setFont(labelsFont);
        difficultyLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        difficultyLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        difficultyPanel.add(difficultyLabel);

        difficultyGroup = new ButtonGroup();
        easyButton = new JRadioButton("Easy");
        hardButton = new JRadioButton("Hard");

        easyButton.setFont(radioButtonsFont);
        hardButton.setFont(radioButtonsFont);

        easyButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        easyButton.setAlignmentY(JLabel.TOP_ALIGNMENT);
        hardButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        hardButton.setAlignmentY(JLabel.TOP_ALIGNMENT);

        hardButton.setSelected(true);
        difficultyGroup.add(easyButton);
        difficultyGroup.add(hardButton);

        difficultyPanel.add(easyButton);
        difficultyPanel.add(hardButton);
    }

    public void setActionListenerToEasyButton(ActionListener actionListener){
        easyButton.addActionListener(actionListener);
    }

    public void setActionListenerToHardButton(ActionListener actionListener){
        hardButton.addActionListener(actionListener);
    }

}
