package desktop.gui.other;

import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.components.WinnerPanel;
import desktop.gui.other.components.Button;
import desktop.utilities.BoardDesktop;

import javax.swing.*;
import java.awt.*;

public class OutcomeFrame {
    private final JFrame frame;
    private static final Font headerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 35);  // the following variables are already defined in Welcomeframe, can we share them somehow?
    private static final Color fontColor = new Color(255, 255, 255);
    private static final Font buttonFont = new Font("Arial", Font.BOLD, 20);

    public OutcomeFrame(BoardDesktop boardDesktop) {
        frame = new JFrame("Outcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        JPanel generalPanel = getGeneralPanel(boardDesktop);
        frame.add(generalPanel);
    }

    public JFrame getFrame() {
        return frame;
    }

    private JPanel getGeneralPanel(BoardDesktop boardDesktop) {

        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel = getHeaderPanel();
        JPanel outcomePanel = getOutcomePanel(boardDesktop);
        JPanel winnerPanel = getWinnerPanel(boardDesktop);
        JPanel buttonPanel = getButtonPanel();

        generalPanel.add(headerPanel);
        generalPanel.add(outcomePanel);
        generalPanel.add(winnerPanel);
        generalPanel.add(buttonPanel);
        return generalPanel;
    }

    private JPanel getHeaderPanel() {
        JPanel headerPanel = new JPanel();

        JLabel headerLabel = new JLabel("GAME OVER!");
        headerLabel.setFont(headerFont);
        headerLabel.setForeground(fontColor);
        headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        headerLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        headerPanel.add(headerLabel);
        return headerPanel;
    }

    private JPanel getOutcomePanel(BoardDesktop boardDesktop) {
        JPanel outcomePanel = new CurrentScorePanel(boardDesktop).getCurrentScorePanel();
        outcomePanel.setBorder(BorderFactory.createTitledBorder("Outcome"));
        return outcomePanel;
    }

    private JPanel getWinnerPanel(BoardDesktop boardDesktop) {
        JPanel winnerPanel = new WinnerPanel(boardDesktop).getWinnerPanel();
        winnerPanel.setBorder(BorderFactory.createTitledBorder("The winner is..."));
        return winnerPanel;
    }

    private JPanel getButtonPanel() {
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
        return buttonPanel;
    }
}
