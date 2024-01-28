import board.Board;
import board.ColoredPawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DesktopBoard extends Board {

    public final JGradientButton[][] buttonGrid;
    private static final String columnLabels = "ABCDEFGH";
    public static final ImageIcon black = new ImageIcon(Objects.requireNonNull(DesktopBoard.class.getResource("/black.png")));
    public static final ImageIcon white = new ImageIcon(Objects.requireNonNull(DesktopBoard.class.getResource("/white.png")));

    public final int PAWN_SIZE = 64;

    public DesktopBoard() {
        super();
        buttonGrid = new JGradientButton[BOARD_SIZE][BOARD_SIZE];
        initializeButtons();
        updateButtonGrid();
    }

    public void initializeButtons(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++)
                buttonGrid[i][j] = new JGradientButton("", i, j);
        }
    }

    public void updateButtonIcon(int row, int col) {
        Image img = null;
        if (getPositionColor(row, col) == ColoredPawn.BLACK) {
            img = black.getImage().getScaledInstance(PAWN_SIZE, PAWN_SIZE, Image.SCALE_SMOOTH);
        }
        if (getPositionColor(row, col) == ColoredPawn.WHITE) {
            img = white.getImage().getScaledInstance(PAWN_SIZE, PAWN_SIZE, Image.SCALE_SMOOTH);
        }
        img = img == null ? new ImageIcon(new byte[0]).getImage() : img;

        buttonGrid[row][col].setIcon(new ImageIcon(img));
    }

    public void updateButtonGrid() {
        for (int idx = 0; idx < BOARD_SIZE; idx++) {
            for (int jdx = 0; jdx < BOARD_SIZE; jdx++) {
                updateButtonIcon(idx, jdx);
            }
        }
    }


}
