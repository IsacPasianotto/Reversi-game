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
    private static final int WIDTH = 500;
    private static final int HEIGHT = 450;
    private final JFrame frame;
    private JButton closeButton;
    private JButton playAgainButton;

    public OutcomeFrame(int blackScore, int whiteScore){
        frame = new JFrame("Outcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        JPanel generalPanel = buildGeneralPanel(blackScore, whiteScore);
        frame.add(generalPanel);
    }

    private JPanel buildGeneralPanel(int blackScore, int whiteScore) {
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel = buildHeaderPanel();
        JPanel outcomePanel = buildOutcomePanel(blackScore, whiteScore);
        JPanel winnerPanel = buildWinnerPanel(blackScore, whiteScore);
        JPanel exitButtonPanel = buildExitButtonPanel();
        generalPanel.add(headerPanel);
        generalPanel.add(outcomePanel);
        generalPanel.add(winnerPanel);
        generalPanel.add(exitButtonPanel);
        return generalPanel;
    }

    private JPanel buildHeaderPanel() {
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("GAME OVER!");
        headerLabel.setFont(headerFont);
        headerLabel.setForeground(fontColor);
        headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        headerLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        headerPanel.add(headerLabel);
        return headerPanel;
    }

    private JPanel buildOutcomePanel(int blackScore, int whiteScore) {
        JPanel outcomePanel = new CurrentScorePanel(blackScore,whiteScore).getCurrentScorePanel();
        outcomePanel.setBorder(BorderFactory.createTitledBorder("Outcome"));
        return outcomePanel;
    }

    private JPanel buildWinnerPanel(int blackScore, int whiteScore) {
        JPanel winnerPanel = new WinnerPanel(blackScore,whiteScore).getWinnerPanel();
        winnerPanel.setBorder(BorderFactory.createTitledBorder("The winner is..."));
        return winnerPanel;
    }

    private JPanel buildExitButtonPanel() {
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

    public JFrame getFrame() {
        return frame;
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
}