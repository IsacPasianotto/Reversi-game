package desktop.utilities;

import board.Board;
import board.ColoredPawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class BoardDesktop extends Board {
    private final JGradientButton[][] buttonGrid;
    private static final ImageIcon black = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png")));
    private static final ImageIcon white = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png")));
    public static final int PAWN_ICON_SIZE = 64;

    public BoardDesktop() {
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
        Image img = switch (getPositionColor(row, col)) {
            case BLACK -> black.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
            case WHITE -> white.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
            case EMPTY -> new ImageIcon(new byte[0]).getImage();
        };
        buttonGrid[row][col].setIcon(new ImageIcon(img));
    }

    public void updateButtonGrid() {
        for (int idx = 0; idx < BOARD_SIZE; idx++) {
            for (int jdx = 0; jdx < BOARD_SIZE; jdx++)
                updateButtonIcon(idx, jdx);
        }
    }

    public void updateSuggestionAtTile(int row, int col, boolean toSuggest) {
        buttonGrid[row][col].setToSuggestProperty(toSuggest);
    }

    public void resetBackgroundAtTile(int row, int col){
        buttonGrid[row][col].resetBackground();
    }

    public void addListenersAT(int row, int col, ActionListener listener){
            buttonGrid[row][col].addActionListener(listener);
    }

    public void disableButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; ++j)
                buttonGrid[i][j].setEnabled(false);
        }
    }

    public void enableButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; ++j)
                buttonGrid[i][j].setEnabled(true);
        }
    }

    //public JGradientButton getGradientButtonAt(int row, int col) { return buttonGrid[row][col]; }
    public ImageIcon getBlack() { return black; }
    public ImageIcon getWhite() { return white; }

    public JGradientButton getButton(int row, int col) {
        return buttonGrid[row][col];
    }

}
