import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;

import board.coords.BoardTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Optional;
import java.util.stream.IntStream;

public class DesktopController extends GameController {
    private final DesktopBoard board;
    public DesktopController(DesktopBoard board) {
        super((Board) board);
        this.board = board;
    }


    protected void handleButtonPress(int x, int y) {

        // re-paint all the buttons to delete previous suggestions
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                board.buttonGrid[i][j].putClientProperty("toSuggest", false);
                board.buttonGrid[i][j].setBackground(JGradientButton.boardColor);
            }
        }

        computeValidMoves();
        BoardTile position = new BoardTile(x, y);
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) {
            board.applyMoveToBoard(move.get());
            swapTurn();
            board.updateButtonGrid();
            GuiManager.updatePlayerTurnContextLabel();
            GuiManager.updateScoreContextLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                    computeScoreForPlayer(ColoredPawn.WHITE));
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
            // set the suggestion property of the buttons to be true for the valid moves
            for (ValidMove validMove : validMoves) {
                board.buttonGrid[validMove.position().x()][validMove.position().y()].putClientProperty("toSuggest", true);
                board.buttonGrid[validMove.position().x()][validMove.position().y()].setBackground(JGradientButton.suggestionColor);
            }

        }
    }

}
