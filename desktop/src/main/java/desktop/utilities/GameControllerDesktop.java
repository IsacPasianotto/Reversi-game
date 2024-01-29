package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.OutcomeFrame;
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

        if (board.isFull()) {
            gameOverHandle();
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
                ColoredPawn currentPlayerColor = getCurrentPlayerColor();
                String currentPlayerName = currentPlayerColor == ColoredPawn.BLACK ? "black" : "white";
                JOptionPane.showMessageDialog(null, "No valid moves for the " + currentPlayerName + " player!", "Skipped turn", JOptionPane.INFORMATION_MESSAGE);
                CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
            }
        }
        board.enableButtonGrid();
    }

    public void gameOverHandle() {
        GuiManager.gameFrame.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            JFrame outcomeFrame = new OutcomeFrame(board).getFrame();
            outcomeFrame.setVisible(true);
        });

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
