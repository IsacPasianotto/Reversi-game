package desktop.gui.other.outcome;

import desktop.MainDesktop;
import desktop.gui.main.CurrentScorePanel;
import desktop.gui.main.GuiManager;
import desktop.gui.other.Button;

import javax.swing.*;
import java.awt.*;

/**
 * A class that manages the construction of the frame used to display the game outcome.
 */
public class OutcomeFrame {
    /**
     * The width of the frame.
     */
    protected static final int WIDTH = 500;
    /**
     * The height of the frame.
     */
    protected static final int HEIGHT = 450;
    /**
     * The font to use for the header.
     */
    protected static final Font headerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 35);
    /**
     * The color of the header label
     */
    protected static final Color fontColor = new Color(255, 255, 255);
    /**
     * The font to use for buttons.
     */
    protected static final Font buttonFont = new Font("Arial", Font.BOLD, 20);
    private final JFrame frame;
    private JButton closeButton;
    private JButton playAgainButton;

    /**
     * Creates a new OutcomeFrame with the given scores.
     *
     * @param blackScore the score of the black player
     * @param whiteScore the score of the white player
     */
    public OutcomeFrame(int blackScore, int whiteScore) {
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
        CurrentScorePanel finalScorePanel = new CurrentScorePanel(blackScore, whiteScore);
        WinnerPanel winnerPanel = new WinnerPanel(blackScore, whiteScore);
        JPanel exitButtonPanel = buildExitButtonPanel();
        generalPanel.add(headerPanel);
        generalPanel.add(finalScorePanel.getCurrentScorePanel());
        generalPanel.add(winnerPanel.getWinnerPanel());
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

    private JPanel buildExitButtonPanel() {
        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setLayout(new BoxLayout(exitButtonPanel, BoxLayout.X_AXIS));
        closeButton = new Button(buttonFont, "CLOSE").getButton();
        playAgainButton = new Button(buttonFont, "PLAY AGAIN").getButton();
        closeButton.addActionListener(e -> {
            GuiManager.disposeFrame();
            frame.dispose();
        });
        playAgainButton.addActionListener(e -> {
            GuiManager.disposeFrame();
            frame.dispose();
            MainDesktop.main(null);
        });
        exitButtonPanel.add(closeButton);
        exitButtonPanel.add(playAgainButton);
        exitButtonPanel.setSize(frame.getWidth(), 100);
        return exitButtonPanel;
    }

    /**
     * Sets the visibility of the frame.
     *
     * @param visible the visibility of the frame
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    JButton getPlayAgainButton() {
        return playAgainButton;
    }

    JButton getCloseButton() {
        return closeButton;
    }
}
