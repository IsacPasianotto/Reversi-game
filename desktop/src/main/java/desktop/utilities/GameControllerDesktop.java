package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.GuiManager;
import mechanics.GameController;

import javax.swing.*;
import java.util.Optional;

public class GameControllerDesktop extends GameController {
    private final BoardDesktop board;
    public GameControllerDesktop(BoardDesktop board) {
        super(board);
        this.board = board;
    }


    protected void handleButtonPress(int x, int y) {

        cancelPreviousSuggestion();

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
                int row = validMove.position().x();
                int col = validMove.position().y();
                board.updateSuggestionAtTile(row, col, true);
                board.resetBackgroundAtTile(row, col);
            }
        }
    }

    void cancelPreviousSuggestion() {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                board.updateSuggestionAtTile(i, j, false);
                board.resetBackgroundAtTile(i, j);
            }
        }
    }
}
