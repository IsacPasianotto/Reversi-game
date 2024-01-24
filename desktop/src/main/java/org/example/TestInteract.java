package org.example;

//import board.coords.BoardTile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import javax.swing.*;
import javax.swing.border.*;

public class TestInteract {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Testttttttttttttttttttttttttttt");
    private static final String COLS = "ABCDEFGH";

    public TestInteract() {
        initializeGui();
    }

    public void initializeGui(){
        //BoardTile position;
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar(null, JToolBar.VERTICAL);
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.EAST);
        JButton undo = new JButton("Undo");
        undo.addActionListener(e -> {
            System.out.println("Undo");
        });
        tools.add(undo);
        tools.addSeparator();
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> {
            System.out.println("Quit");
        });
        tools.add(quit);


        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        final int[] row = new int[1];
        final int[] column = new int[1];

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon("/home/davide/Desktop/software_dev_methods/pawn.png");
                Image img = icon.getImage();
                Image newimg = img.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));//newimg );
                b.setIcon(icon);
                b.setBackground(Color.WHITE);
                int finalI = i;
                int finalJ = j;
                b.addActionListener(e -> {
                    row[0] = finalI;
                    column[0] = finalJ;
                    System.out.println("clicked column " + column[0]
                            + ", row " + row[0]);
                });
                chessBoardSquares[j][i] = b;

//                chessBoardSquares[i][j].putClientProperty("column", i);
//                chessBoardSquares[i][j].putClientProperty("row", j);
//                chessBoardSquares[i][j].addActionListener(new MyActionListener());
            }
        }

        chessBoard.add(new JLabel(""));
        for (int i = 0; i < 8; i++) {
            chessBoard.add(
                    new JLabel(COLS.substring(i, i + 1),
                            SwingConstants.CENTER));
        }

        for (int i=0; i<8; i++){
            for (int j=0;j<8;j++){
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                TestInteract cb =
                        new TestInteract();

                JFrame f = new JFrame("ChessChamp");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
