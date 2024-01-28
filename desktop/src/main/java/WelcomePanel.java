import javax.swing.*;

public class WelcomePanel {

    JFrame frame;

    public WelcomePanel() {
        buildFrame();
    }

    private void buildFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);

        JButton startButton = new JButton("Start");
        startButton.setBounds(50, 100, 100, 30);
        startButton.addActionListener(e -> {
                   frame.setVisible(false);
        });

        frame.add(startButton);

        JLabel label = new JLabel("Welcome to Reversi!");
        label.setBounds(50, 50, 200, 30);
        frame.add(label);
    }

}
