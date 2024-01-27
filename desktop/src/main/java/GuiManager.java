import board.Board;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.stream.IntStream;

public class GuiManager {

    public JFrame frame;
    public JPanel boardPanel;
    public Color boardBorderColor = Color.BLACK;

    public Font boardFont = new Font("Arial", Font.BOLD, 25);

    private static final String columnLabels = "ABCDEFGH";


    public JPanel statusPanel;
    public Font statusLabelFont = new Font("Arial", Font.PLAIN, 20);
    public Font statusLabelContextFont = new Font("Arial", Font.PLAIN, 35);
    public Color statusLabelColor = Color.BLACK;
    public Color statusLabelContextColor = Color.BLACK;

    public static JLabel playerTurnContextLabel = new JLabel();

    public static JLabel currentScoreContextLabel = new JLabel();


    DesktopBoard desktopBoard;
    DesktopGame desktopGame;


   public GuiManager(DesktopBoard desktopBoard, DesktopGame desktopGame) {
         this.desktopBoard = desktopBoard;
         this.desktopGame = desktopGame;
         addListenerToButtonGrid();
         composeBoardPanel();
         composeStatusPanel();
         composeJFrame();

   }

   public void addListenerToButtonGrid(){
       for (int i = 0; i < Board.BOARD_SIZE; i++) {
           for (int j = 0; j < Board.BOARD_SIZE; j++)
               desktopBoard.buttonGrid[i][j].addActionListener(desktopGame.getButtonListener(i, j));
       }
   }

   public void composeBoardPanel() {
       boardPanel = new JPanel(new GridLayout(0, 9));
       boardPanel.setBorder(new LineBorder(boardBorderColor));
       boardPanel.add(new JLabel(""));

       IntStream.range(0, Board.BOARD_SIZE).forEachOrdered(i -> {
           JLabel label = new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER);
           label.setFont(boardFont);
            boardPanel.add(label);
       });

       for (int i = 0; i < Board.BOARD_SIZE; i++) {
           JLabel label = new JLabel("" + (i + 1), SwingConstants.CENTER);
           label.setFont(boardFont);
           boardPanel.add(label);
           for (int j = 0; j < Board.BOARD_SIZE; j++)
               boardPanel.add(desktopBoard.buttonGrid[i][j]);
       }
   }

   public void composeStatusPanel() {

        // CURRENT PLAYER
        statusPanel = new JPanel(new GridLayout(0, 1));
        statusPanel.setBorder(new LineBorder(boardBorderColor));
        JLabel playerTurnLabel = new JLabel("Player turn: ", SwingConstants.CENTER);
        playerTurnLabel.setFont(statusLabelFont);
        playerTurnLabel.setForeground(statusLabelColor);
        statusPanel.add(playerTurnLabel);

        // CURRENT PLAYER CONTEXT
        setPlayerTurnContextLabel();
        statusPanel.add(playerTurnContextLabel);

        // CURRENT SCORE
        JLabel currentScoreLabel = new JLabel("Current score: ", SwingConstants.CENTER);
        currentScoreLabel.setFont(statusLabelFont);
        currentScoreLabel.setForeground(statusLabelColor);
        statusPanel.add(currentScoreLabel);

        // CURRENT SCORE CONTEXT
        JLabel blackIcon = new JLabel(new ImageIcon(DesktopBoard.black.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        JLabel whiteIcon = new JLabel(new ImageIcon(DesktopBoard.white.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        setCurrentScoreContextLabel();

        // create  a panel to add the icons
        JPanel scorePanel = new JPanel(new GridLayout(0, 3));
        scorePanel.add(blackIcon);
        scorePanel.add(currentScoreContextLabel);
        scorePanel.add(whiteIcon);
        //statusPanel.add(blackIcon);
        //statusPanel.add(currentScoreContextLabel);
        //statusPanel.add(whiteIcon);
        statusPanel.add(scorePanel);

        // UNDO BUTTON
        JButton undoButton = new JButton("Undo");
        undoButton.setFont(statusLabelFont);
        undoButton.setForeground(statusLabelColor);
        undoButton.addActionListener(desktopGame.getUndoListener(desktopGame.controller, desktopBoard));
        statusPanel.add(undoButton);

   }

    private void setCurrentScoreContextLabel() {
        currentScoreContextLabel.setFont(statusLabelContextFont);
        currentScoreContextLabel.setForeground(statusLabelContextColor);
        currentScoreContextLabel.setText("2 - 2");
        currentScoreContextLabel.setHorizontalAlignment(SwingConstants.CENTER);
   }

    public void setPlayerTurnContextLabel() {
       playerTurnContextLabel.setFont(statusLabelContextFont);
       playerTurnContextLabel.setForeground(statusLabelContextColor);
       playerTurnContextLabel.setText("Black");
       playerTurnContextLabel.setHorizontalAlignment(SwingConstants.CENTER);
   }

   public void composeJFrame() {
        frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
   }


   public static void updatePlayerTurnContextLabel() {
        String actual = playerTurnContextLabel.getText();
        if (actual.equals("Black")) {
            playerTurnContextLabel.setText("White");
        } else {
            playerTurnContextLabel.setText("Black");
        }
   }

   public static void updateScoreContextLabel(int blackScore, int whiteScore) {
        currentScoreContextLabel.setText(blackScore + " - " + whiteScore);
   }

   public JFrame getJFrame() {
       return frame;
   }

}
