package desktop.utilities;

import board.Board;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardDesktop extends Board {
    private final JGradientButton[][] buttonGrid;
    private static final ImageIcon blackPawn = GuiManager.blackPawn;
    private static final ImageIcon whitePawn = GuiManager.whitePawn;
    public static final int PAWN_ICON_SIZE = 64;

    public BoardDesktop() {
        super();
        buttonGrid = new JGradientButton[BOARD_SIZE][BOARD_SIZE];
        initializeButtons();
    }

    public void initializeButtons(){
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttonGrid[i][j] = new JGradientButton(i, j);
                updateButtonIcon(i, j);
            }
    }

    public void updateButtonIcon(int row, int col) {
        Image img = switch (getPositionColor(row, col)) {
            case BLACK -> blackPawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
            case WHITE -> whitePawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
            case EMPTY -> new ImageIcon(new byte[0]).getImage();
        };
        buttonGrid[row][col].setIcon(new ImageIcon(img));
    }

    public void updateButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                updateButtonIcon(i, j);
    }

    public void updateSuggestionAtTile(BoardTile position, boolean toSuggest) {
        int row = position.x();
        int col = position.y();
        buttonGrid[row][col].setToSuggestProperty(toSuggest);
    }

    public void resetBackgroundAtTile(BoardTile position) {
        int row = position.x();
        int col = position.y();
        buttonGrid[row][col].resetBackground();
    }

    public void disableButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; ++j)
                buttonGrid[i][j].setEnabled(false);
    }

    public void enableButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; ++j)
                buttonGrid[i][j].setEnabled(true);
    }

    public Image getBlackPawnImage() { return blackPawn.getImage(); }
    public Image getWhitePawnImage() { return whitePawn.getImage(); }

    public JGradientButton getButton(int row, int col) {
        return buttonGrid[row][col];
    }

    void cancelPreviousSuggestion() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                BoardTile position = new BoardTile(i, j);
                updateSuggestionAtTile(position, false);
                resetBackgroundAtTile(position);
            }
        }
    }

    public void addListenerToButton(int i, int j, ActionListener listener){
        buttonGrid[i][j].addActionListener(listener);
    }
}
