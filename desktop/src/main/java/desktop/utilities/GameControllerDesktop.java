package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.components.CurrentPlayerPanel;
import desktop.gui.components.CurrentScorePanel;
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
        board.disableButtonGrid();

        cancelPreviousSuggestion();

        computeValidMoves();
        BoardTile position = new BoardTile(x, y);
        Optional<ValidMove> move = isValid(position);

        if (move.isPresent()) {
            board.applyMoveToBoard(move.get());
            swapTurn();
            board.updateButtonGrid();
            CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
            CurrentScorePanel.updateCurrentScoreLiveLabel(computeScoreForPlayer(ColoredPawn.BLACK),
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

        // check if at one ValidMove is available for the next player
        computeValidMoves();
        if (validMoves.isEmpty()) {
            swapTurn();
            computeValidMoves();
            if (validMoves.isEmpty()) {
                gameOverHandle();
            }
            else {
                JOptionPane.showMessageDialog(null, "No valid moves for the current player!", "Skipped turn", JOptionPane.INFORMATION_MESSAGE);
                CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
            }
        }
        board.enableButtonGrid();
    }

    public void gameOverHandle() {
        String winner = board.computeScoreForPlayer(ColoredPawn.BLACK) > board.computeScoreForPlayer(ColoredPawn.WHITE) ? "Black" : "White";
        if (board.computeScoreForPlayer(ColoredPawn.BLACK) == board.computeScoreForPlayer(ColoredPawn.WHITE))
            winner = "Draw";
        String message = "Game over! " + winner + " wins!";
        JOptionPane.showMessageDialog(null, message, "Game over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
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
