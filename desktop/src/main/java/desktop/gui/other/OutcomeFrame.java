package desktop.gui.other;

import desktop.MainDesktop;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.components.Button;
import desktop.gui.other.components.WinnerPanel;

import javax.swing.*;
import java.awt.*;

public class OutcomeFrame {
    private static final Font headerFont = GuiManager.arialBoldItalic35;
    private static final Color fontColor = GuiManager.white;
    private static final Font buttonFont = GuiManager.buttonFont;
    private final JFrame frame;
    private JButton closeButton;
    private JButton playAgainButton;

    public OutcomeFrame(int blackScore, int whiteScore){
        frame = new JFrame("Outcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        JPanel generalPanel = getGeneralPanel(blackScore, whiteScore);
        frame.add(generalPanel);
    }

    public JFrame getFrame() { return frame; }

    public JButton getPlayAgainButton() { return playAgainButton; }

    public JButton getCloseButton() { return closeButton; }

    private JPanel getGeneralPanel(int blackScore, int whiteScore) {
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel = getHeaderPanel();
        JPanel outcomePanel = getOutcomePanel(blackScore, whiteScore);
        JPanel winnerPanel = getWinnerPanel(blackScore, whiteScore);
        JPanel exitButtonPanel = getExitButtonPanel();

        generalPanel.add(headerPanel);
        generalPanel.add(outcomePanel);
        generalPanel.add(winnerPanel);
        generalPanel.add(exitButtonPanel);
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

    private JPanel getOutcomePanel(int blackScore, int whiteScore) {
        JPanel outcomePanel = new CurrentScorePanel(blackScore,whiteScore).getCurrentScorePanel();
        outcomePanel.setBorder(BorderFactory.createTitledBorder("Outcome"));
        return outcomePanel;
    }

    private JPanel getWinnerPanel(int blackScore, int whiteScore) {
        JPanel winnerPanel = new WinnerPanel(blackScore,whiteScore).getWinnerPanel();
        winnerPanel.setBorder(BorderFactory.createTitledBorder("The winner is..."));
        return winnerPanel;
    }

    private JPanel getExitButtonPanel() {
        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setLayout(new BoxLayout(exitButtonPanel, BoxLayout.X_AXIS));
        closeButton = new Button(buttonFont, "CLOSE").getButton();
        playAgainButton = new Button(buttonFont, "PLAY AGAIN").getButton();
        closeButton.addActionListener(e -> {
            frame.dispose();
            GuiManager.disposeFrame();
        });
        playAgainButton.addActionListener(e -> {
            frame.dispose();
            GuiManager.disposeFrame();
            MainDesktop.main(null);
        });
        exitButtonPanel.add(closeButton);
        exitButtonPanel.add(playAgainButton);
        exitButtonPanel.setSize(frame.getWidth(), 100);
        return exitButtonPanel;
    }
}