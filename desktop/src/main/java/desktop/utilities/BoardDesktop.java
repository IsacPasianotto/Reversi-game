package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;

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
        initButtonGrid();
    }

    public void initButtonGrid(){
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                BoardTile position = new BoardTile(i, j);
                buttonGrid[i][j] = new JGradientButton(position);
                updateButtonIcon(position);
            }
    }

    private void updateButtonIcon(BoardTile position) {
        Image img = switch (getPositionColor(position)) {
            case BLACK -> blackPawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
            case WHITE -> whitePawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
            case EMPTY -> new ImageIcon(new byte[0]).getImage();
        };
        buttonGrid[position.x()][position.y()].setIcon(new ImageIcon(img));
    }

    public void updateButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                updateButtonIcon(new BoardTile(i, j));
    }

    public void enableSuggestionAtTile(BoardTile position) {
        int row = position.x();
        int col = position.y();
        buttonGrid[row][col].setToSuggestProperty(true);
        buttonGrid[row][col].resetBackground();
    }
    private void disableSuggestionAtTile(BoardTile position) {
        int row = position.x();
        int col = position.y();
        buttonGrid[row][col].setToSuggestProperty(false);
        buttonGrid[row][col].resetBackground();
    }

    public static Image getBlackPawnImage() { return blackPawn.getImage(); }
    public static Image getWhitePawnImage() { return whitePawn.getImage(); }

    public JGradientButton getButton(int row, int col) {
        return buttonGrid[row][col];
    }

    void cancelPreviousSuggestion() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                disableSuggestionAtTile(new BoardTile(i, j));
    }

    protected void addListenerToButton(BoardTile position, ActionListener listener){
        buttonGrid[position.x()][position.y()].addActionListener(listener);
    }

    protected void updateGUIBoard(ValidMove move) {
        applyMoveToBoard(move);
        updateButtonGrid();
        CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                computeScoreForPlayer(ColoredPawn.WHITE));
    }

}
