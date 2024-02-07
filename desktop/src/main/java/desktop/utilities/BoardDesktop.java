package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.CurrentPlayerPanel;
import desktop.gui.main.CurrentScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Class which extends the Board class present in the core module to add the GUI functionalities.
 * The most relevant addition is a button grid which is used to graphically represent the status of the board
 * It uses a custom extension of JButton to add some properties to the buttons.
 */
public class BoardDesktop extends Board {
    /**
     * The size of the board.
     */
    protected static final int PAWN_ICON_SIZE = 64;
    /**
     * The black pawn image.
     */
    protected static final ImageIcon blackPawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/black.png")));

    /**
     * The white pawn image.
     */
    protected static final ImageIcon whitePawn = new ImageIcon(Objects.requireNonNull(BoardDesktop.class.getResource("/white.png")));

    /**
     * The empty pawn image.
     */
    protected static final ImageIcon emptyPawn = new ImageIcon(new byte[0]);
    private final JGradientButton[][] buttonGrid;

    /**
     * Initializes the board with the starting position of the game.
     */
    public BoardDesktop() {
        super();
        blackPawn.setImage(blackPawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH));
        whitePawn.setImage(whitePawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH));
        emptyPawn.setImage(emptyPawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH));
        buttonGrid = new JGradientButton[BOARD_SIZE][BOARD_SIZE];
        initButtonGrid();
    }

    private void initButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttonGrid[i][j] = new JGradientButton(i, j);
                updateButtonIcon(i, j);
            }
    }

    private void updateButtonIcon(int i, int j) {
        ImageIcon img = switch (getPositionColor(i, j)) {
            case BLACK -> blackPawn;
            case WHITE -> whitePawn;
            case EMPTY -> emptyPawn;
        };
        buttonGrid[i][j].setIcon(img);
    }

    void enableSuggestions(ArrayList<ValidMove> validMoves) {
        for (ValidMove validMove : validMoves)
            setSuggestionAtTile(validMove.position(), true);
    }

    void disableSuggestions() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((boolean) buttonGrid[i][j].getClientProperty("toSuggest"))
                    setSuggestionAtTile(new BoardTile(i, j), false);
            }
    }

    private void setSuggestionAtTile(BoardTile position, boolean toSuggest) {
        int row = position.x();
        int col = position.y();
        buttonGrid[row][col].setToSuggestProperty(toSuggest);
        buttonGrid[row][col].paintBackground();
    }

    void addListenerToButton(BoardTile position, ActionListener listener) {
        buttonGrid[position.x()][position.y()].addActionListener(listener);
    }

    void updateGUIBoard(ValidMove move) {
        applyMoveToBoard(move);
        updateButtonGrid();
        CurrentPlayerPanel.updateCurrentPlayerColorLabel();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer(ColoredPawn.BLACK), computeScoreForPlayer(ColoredPawn.WHITE));
    }

    void updateButtonGrid() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> IntStream.range(0, BOARD_SIZE).forEach(j -> updateButtonIcon(i, j)));
    }

    /**
     * Enable or disable the board when needed.
     *
     * @param enabled true to enable the board, false to disable it
     */
    public void setEnabled(boolean enabled) {
        IntStream.range(0, BOARD_SIZE).forEach(i -> IntStream.range(0, BOARD_SIZE).forEach(j -> buttonGrid[i][j].setEnabled(enabled)));
    }

    /**
     * Returns the button at the given position.
     *
     * @param row the row of the button
     * @param col the column of the button
     * @return the button at the given position
     */
    public JButton getButton(int row, int col) {
        return buttonGrid[row][col];
    }
}