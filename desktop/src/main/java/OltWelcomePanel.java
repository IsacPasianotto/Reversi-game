import javax.swing.*;
import java.awt.*;

public class OltWelcomePanel {

    JFrame frame;
    JPanel modePanel;
    JPanel difficultyPanel;

    JPanel whoPlaysFirstPanel;

    JButton startButton;

    private Font customLabelFont = new Font("Arial", Font.ITALIC,18 );
    private Font customRadioFont = new Font("Arial", Font.BOLD, 15);
    private Font customButtonFont = new Font("Arial", Font.BOLD, 20);



    public OltWelcomePanel() {
        buildFrame();
    }

    private void buildFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(450, 200);
        frame.setMinimumSize(new Dimension(450, 200));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // mode panel and difficulty panel should be in the same row if the window is wide enough
        // otherwise, they should be one on top of the other


        buildModePanel();
        //frame.add(modePanel, BorderLayout.CENTER);


        buildDifficultyPanel();
        buildWhoPlayFirstPanel();
        //frame.add(difficultyPanel, BorderLayout.CENTER);

        JPanel controllersPanel = new JPanel();
        controllersPanel.setLayout(new FlowLayout());
        controllersPanel.add(modePanel, BorderLayout.CENTER);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(difficultyPanel);
        rightPanel.add(whoPlaysFirstPanel);
        controllersPanel.add(rightPanel, BorderLayout.EAST);
        frame.add(controllersPanel, BorderLayout.CENTER);


        buildStartButton();
        // add it on the bottom
        // button should be wide as the frame
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);


    }


    private void buildDifficultyPanel(){
        // Difficulty panel should be visible only if the mode is Human vs Computer or Computer vs Computer



        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
        // difficultyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel difficultyLabel = new JLabel("Select a difficulty:");
        difficultyLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        difficultyLabel.setFont(customLabelFont);
        difficultyPanel.add(difficultyLabel);

        // make a radio button group
        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton easy = new JRadioButton("Easy");
        easy.setFont(customRadioFont);
        JRadioButton hard = new JRadioButton("Hard");
        hard.setFont(customRadioFont);
        difficultyGroup.add(easy);
        difficultyGroup.add(hard);
        hard.setSelected(true);

        // add the radio buttons to the panel
        difficultyPanel.add(easy);
        difficultyPanel.add(hard);

    }

    private void buildModePanel(){
        modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        //modePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel modeLabel = new JLabel("Select a game mode:");
        modeLabel.setFont(customLabelFont);
        modeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        modePanel.add(modeLabel);

        // make a radio button group
        ButtonGroup modeGroup = new ButtonGroup();
        JRadioButton humanVsHuman = new JRadioButton("Human vs Human");
        humanVsHuman.setFont(customRadioFont);
        JRadioButton humanVsBot = new JRadioButton("Human vs Computer");
        humanVsBot.setFont(customRadioFont);
        //JRadioButton botVsBot = new JRadioButton("Computer vs Computer");
        //botVsBot.setFont(customRadioFont);
        modeGroup.add(humanVsHuman);
        modeGroup.add(humanVsBot);
        //modeGroup.add(botVsBot);
        humanVsBot.setSelected(true);

        // add the radio buttons group to the panel

        modePanel.add(humanVsHuman);
        modePanel.add(humanVsBot);

        humanVsHuman.addActionListener(e -> {
            difficultyPanel.setVisible(false);
            whoPlaysFirstPanel.setVisible(false);
            //frame.pack();
        });
        humanVsBot.addActionListener(e -> {
            difficultyPanel.setVisible(true);
            whoPlaysFirstPanel.setVisible(true);
            //frame.pack();
        });

        //modePanel.add(botVsBot);

    }

    private void buildWhoPlayFirstPanel() {
            whoPlaysFirstPanel = new JPanel();
        whoPlaysFirstPanel.setLayout(new BoxLayout(whoPlaysFirstPanel, BoxLayout.Y_AXIS));
        //whoPlaysFirstPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel whoPlaysFirstLabel = new JLabel("Who plays first?");
        whoPlaysFirstLabel.setFont(customLabelFont);
        whoPlaysFirstLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        whoPlaysFirstPanel.add(whoPlaysFirstLabel);

        // make a radio button group
        ButtonGroup whoPlaysFirstGroup = new ButtonGroup();
        JRadioButton black = new JRadioButton("Black");
        black.setFont(customRadioFont);
        JRadioButton white = new JRadioButton("White");
        white.setFont(customRadioFont);
        whoPlaysFirstGroup.add(black);
        whoPlaysFirstGroup.add(white);
        black.setSelected(true);

        // add the radio buttons group to the panel

        whoPlaysFirstPanel.add(black);
        whoPlaysFirstPanel.add(white);

    }




    private void buildStartButton(){
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            frame.setVisible(false);
        });
        startButton.setVisible(true);
        startButton.setEnabled(true);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        //make button as wide as the frame and as tall as it needs to be
        startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
        startButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
        startButton.setMinimumSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
        startButton.setSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
        startButton.setFont(customButtonFont);

    }

}
