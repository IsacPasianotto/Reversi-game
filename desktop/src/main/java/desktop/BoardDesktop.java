package desktop;

import board.Board;
import board.ColoredPawn;
import board.coords.BoardTile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class BoardDesktop extends Board {

    JPanel panel;
    public final JGradientButton[][] buttonGrid;
    private static final String columnLabels = "ABCDEFGH";
    public static final ImageIcon black = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png")));
    public static final ImageIcon white = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png")));


    public BoardDesktop() {
        super();
        buttonGrid = new JGradientButton[BOARD_SIZE][BOARD_SIZE];
        initializeGui();
    }

    public void initializeGui() {
        panel = new JPanel(new GridLayout(0, 9));
        panel.setBorder(new LineBorder(Color.BLACK));
        // INITIALIZE THE BUTTONS
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttonGrid[i][j] = new JGradientButton("", i, j);
                buttonGrid[i][j].addActionListener(new GameControllerDesktop(this).getButtonListener(i, j));
            }
        }

        // PUT THE BUTTONS ON THE JPanel
        panel.add(new JLabel(""));
        IntStream.range(0, BOARD_SIZE).forEachOrdered(i -> panel.add(new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER)));
        for (int i = 0; i < BOARD_SIZE; i++) {
            panel.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
            for (int j = 0; j < BOARD_SIZE; j++)
                panel.add(buttonGrid[i][j]);
        }
        // set initial pawns
        updateBoard();
    }
    public void updateBoard(){
        for (int idx = 0; idx < BOARD_SIZE; idx++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (getPositionColor(idx, j) == ColoredPawn.BLACK){
                    updateTile(buttonGrid[idx][j], true);
                }
                else if (getPositionColor(idx, j) == ColoredPawn.WHITE){
                    updateTile(buttonGrid[idx][j], false);
                }
            }
        }
    }
    public void updateTile(JButton tile, boolean isBlack) {
        // the two images are in main/resources folder and are loaded here
        Image img;
        if (isBlack) {
            img = black.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        }
        else {
            img = white.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        }
        ImageIcon icon = new ImageIcon(img);
        tile.setIcon(icon);
    }

    public JPanel getPanel() {
        return panel;
    }
}
