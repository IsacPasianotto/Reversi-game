package desktop.welcome.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartButton {

    JButton startButton;

    public StartButton(Font startButtonFont) {
        buildStartButton(startButtonFont);
    }

    public JButton getStartButton() {
        return startButton;
    }

    private void buildStartButton(Font startButtonFont) {
        startButton = new JButton("Start");
        startButton.setFont(startButtonFont);
        startButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        startButton.setAlignmentY(JLabel.TOP_ALIGNMENT);

        startButton.setEnabled(true);
        startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
        startButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
    }

    public void setActionListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }
}
