package desktop;

import board.Board;
import board.coords.BoardTile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class BoardDesktop extends Board {

    JPanel panel;
    JButton[][] buttonGrid;
    private static final String columnLabels = "ABCDEFGH";


    public BoardDesktop() {
        super();
        buttonGrid = new JButton[BOARD_SIZE][BOARD_SIZE];
        initializeGui();
    }

    public void initializeGui() {
        panel = new JPanel(new GridLayout(0, 9));
        panel.setBorder(new LineBorder(Color.BLACK));

        // INITIALIZE THE BUTTONS
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                buttonGrid[i][j] = getjButton(i, j);
        }

        // PUT THE BUTTONS ON THE JPanel
        panel.add(new JLabel(""));
        IntStream.range(0, BOARD_SIZE).forEachOrdered(i -> panel.add(new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER)));
        for (int i = 0; i < BOARD_SIZE; i++) {
            panel.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
            for (int j = 0; j < BOARD_SIZE; j++)
                panel.add(buttonGrid[i][j]);
        }

    }

    private static JButton getjButton(int i, int j) {
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        JButton b = new JButton();
        b.setMargin(buttonMargin);

        ImageIcon icon = new ImageIcon(new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB));
        //Image img = icon.getImage();
        //Image newimg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH); //NOTE: actually not necessary if we give the right size
        //icon = new ImageIcon(newimg);

        b.setIcon(icon);
        b.setBackground(Color.WHITE);
        b.putClientProperty("column", j);
        b.putClientProperty("row", i);
        b.addActionListener(e -> {
            // DO SOMETHING IN ANOTHER CLASS
            GameControllerDesktop.handleButtonPress(new BoardTile((int) b.getClientProperty("column"), (int) b.getClientProperty("row")));
        });
        return b;
    }

    public JPanel getPanel() {
        return panel;
    }
}
