package desktop.utilities;

import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.OutcomeFrame;
import mechanics.GameController;
import player.Player;
import player.human.QuitGameException;
import player.human.UndoException;

import javax.swing.*;
import java.util.Optional;

public class GameControllerDesktop extends GameController {
    final BoardDesktop board;

    public GameControllerDesktop(BoardDesktop board) {
        super(board);
        this.board = board;
    }




    public void handleHumanTurn(BoardTile position) {
        computeValidMoves();
        if (validMoves.isEmpty()) handleNoValidMovesCase();
        else handleHumanMove(position);
    }

    private void handleHumanMove(BoardTile position) {
        board.disableButtonGrid();
        board.cancelPreviousSuggestion();
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) updateGUIBoard(move.get());
        else {
            JOptionPane.showMessageDialog(null, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
            // set the suggestion property of the buttons to be true for the valid moves
            for (ValidMove validMove : validMoves) {
                board.updateSuggestionAtTile(validMove.position(), true);
                board.resetBackgroundAtTile(validMove.position());
            }
        }
        board.enableButtonGrid();
    }

    protected void handleBotTurn(Player bot){
        computeValidMoves();
        if (validMoves.isEmpty()) handleNoValidMovesCase();
        else handleBotMove(bot);
    }

    protected void handleBotMove(Player bot) {
        ValidMove move = null;
        try {
            System.out.println(board);
            move = bot.askForAMove(this);
            System.out.println(board);
            System.out.println(move);
        } catch (QuitGameException | UndoException ignored) {}
        updateGUIBoard(move);
    }

    private void updateGUIBoard(ValidMove move) {
        board.applyMoveToBoard(move);
        swapTurn();
        board.updateButtonGrid();
        CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        CurrentScorePanel.updateCurrentScoreLiveLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                computeScoreForPlayer(ColoredPawn.WHITE));
        if (isBoardFull()) gameOverHandle();
    }

    private void handleNoValidMovesCase() {
        ColoredPawn currentPlayerColor = getCurrentPlayerColor();
        String currentPlayerName = currentPlayerColor == ColoredPawn.BLACK ? "black" : "white";
        JOptionPane.showMessageDialog(null, "No valid moves for the " + currentPlayerName + " player!", "Skipped turn", JOptionPane.INFORMATION_MESSAGE);
        CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        swapTurn();
        computeValidMoves();
        if (validMoves.isEmpty()) gameOverHandle();
    }

    public void gameOverHandle() {
        // guiManager.gameFrame.dispose();
        SwingUtilities.invokeLater(() -> {
            JFrame outcomeFrame = new OutcomeFrame(board).getFrame();
            outcomeFrame.setVisible(true);
        });

    }
}
