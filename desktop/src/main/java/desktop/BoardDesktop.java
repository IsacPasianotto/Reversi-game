package desktop;

import board.Board;
import board.coords.BoardTile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardDesktop extends Board {

    JPanel panel;
    JButton[][] buttonGrid;
    private static final String columnLabels = "ABCDEFGH";


    public BoardDesktop() {
        super();
        panel = new JPanel(new GridLayout(0, 9));
        panel.setBorder(new LineBorder(Color.BLACK));

        buttonGrid = new JButton[BOARD_SIZE][BOARD_SIZE];
        initializeGui();
    }

    public void initializeGui() {
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // INITIALIZE THE BUTTONS
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttonGrid[i][j] = getjButton(buttonMargin, i, j);
            }
        }

        // PUT THE BUTTONS ON THE JPanel
        panel.add(new JLabel(""));
        for (int i = 0; i < BOARD_SIZE; i++) {
            panel.add(new JLabel(columnLabels.substring(i, i + 1), SwingConstants.CENTER));
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            panel.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
            for (int j = 0; j < BOARD_SIZE; j++)
                panel.add(buttonGrid[i][j]);
        }

    }

    private static JButton getjButton(Insets buttonMargin, int i, int j) {
        JButton b = new JButton();
        b.setMargin(buttonMargin);

        ImageIcon icon = new ImageIcon(new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);

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
