package org.example;

import board.Board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DesktopBoard extends Board {

    JPanel panel;
    JButton[][] buttonGrid;
    private static final String columnLabels = "ABCDEFGH";


    public DesktopBoard() {
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
                JButton b = new JButton();
                b.setMargin(buttonMargin);

                ImageIcon icon = new ImageIcon(new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB));
                Image img = icon.getImage();
                Image newimg = img.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);

                b.setIcon(icon);
                b.setBackground(Color.WHITE);
                b.putClientProperty("column", i);
                b.putClientProperty("row", j);
                b.addActionListener(e -> {
                    // DO SOMETHING IN ANOTHER CLASS
                    DesktopGameController.handleButtonPress((int) b.getClientProperty("column"), (int) b.getClientProperty("row"));
                });
                buttonGrid[i][j] = b;
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

    public JPanel getPanel() {
        return panel;
    }
}
