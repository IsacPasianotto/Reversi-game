package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.OutcomeFrame;
import mechanics.GameController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;

public class GameControllerDesktop extends GameController {
    final BoardDesktop board;

    public GameControllerDesktop(BoardDesktop board) {
        super(board);
        this.board = board;
        System.out.println("GameControllerDesktop constructor");

    }


    protected void handleButtonPress(BoardTile position) {
        board.disableButtonGrid();

        board.cancelPreviousSuggestion();

        computeValidMoves();
        Optional<ValidMove> move = isValid(position);
        System.out.println(position);
        System.out.println(move);
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

        if (board.isFull()) gameOverHandle();

        // check if at one ValidMove is available for the next player
        if (validMoves.isEmpty()) {
            ColoredPawn currentPlayerColor = getCurrentPlayerColor();
            String currentPlayerName = currentPlayerColor == ColoredPawn.BLACK ? "black" : "white";
            JOptionPane.showMessageDialog(null, "No valid moves for the " + currentPlayerName + " player!", "Skipped turn", JOptionPane.INFORMATION_MESSAGE);
            CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
            swapTurn();
            computeValidMoves();
            if (validMoves.isEmpty()) gameOverHandle();
        }
        board.enableButtonGrid();
    }

    public void gameOverHandle() {
        // guiManager.gameFrame.dispose();
        SwingUtilities.invokeLater(() -> {
            JFrame outcomeFrame = new OutcomeFrame(board).getFrame();
            outcomeFrame.setVisible(true);
        });

    }

    public ActionListener getButtonListener(int x, int y, ArrayList<Board> previousSteps) {
        return e -> {
            handleButtonPress(new BoardTile(x, y));
            if (!board.equals(previousSteps.getLast())) {
                System.out.println("Added to previousSteps");
                previousSteps.add(board.copy());
            }
        };
    }
}
