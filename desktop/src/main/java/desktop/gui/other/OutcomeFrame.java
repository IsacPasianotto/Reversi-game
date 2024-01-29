package desktop.gui.other;

import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.main.components.WinnerPanel;
import desktop.gui.other.components.Button;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import java.awt.*;

public class OutcomeFrame {
    private JFrame frame;
    private JPanel generalPanel;
    private JPanel outcomePanel;
    private JPanel headerPanel;
    private JPanel winnerPanel;
    private JLabel headerLabel;
    private Font headerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 35);
    private Color fontColor = new Color(255, 255, 255);
    private Font buttonFont = new Font("Arial", Font.BOLD, 20);
    private BoardDesktop boardDesktop;

    public OutcomeFrame(BoardDesktop boardDesktop) {
        this.boardDesktop = boardDesktop;
        buildOutcomeFrame(this.boardDesktop);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void buildOutcomeFrame(BoardDesktop boardDesktop){
        frame = new JFrame("Outcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);

        generalPanel = new JPanel();

        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        headerLabel = new JLabel("GAME OVER!");
        headerLabel.setFont(headerFont);
        headerLabel.setForeground(fontColor);
        headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        headerLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        headerPanel.add(headerLabel);

        outcomePanel = new CurrentScorePanel(boardDesktop).getCurrentScorePanel();
        outcomePanel.setBorder(BorderFactory.createTitledBorder("Outcome"));

        winnerPanel = new WinnerPanel(boardDesktop).getWinnerPanel();
        winnerPanel.setBorder(BorderFactory.createTitledBorder("The winner is..."));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton closeButton = new Button(buttonFont, "CLOSE").getButton();
        JButton playAgainButton = new Button(buttonFont, "PLAY AGAIN").getButton();

        closeButton.addActionListener(e -> {
            GuiManager.gameFrame.dispose();
            frame.dispose();
            System.exit(0);
        });

        playAgainButton.addActionListener(e -> {
            // TODO --> implement the play again button
        });


        buttonPanel.add(closeButton);
        buttonPanel.add(playAgainButton);
        buttonPanel.setSize(frame.getWidth(), 100);


        generalPanel.add(headerPanel);
        generalPanel.add(outcomePanel);
        generalPanel.add(winnerPanel);
        generalPanel.add(buttonPanel);

        frame.add(generalPanel);
    }
}
