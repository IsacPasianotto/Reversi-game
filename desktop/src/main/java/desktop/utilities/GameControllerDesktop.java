package desktop.utilities;

import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.OutcomeFrame;
import mechanics.GameController;
import player.Player;
import player.human.QuitGameException;
import player.human.UndoException;

import javax.swing.*;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameControllerDesktop extends GameController {

    public GameControllerDesktop(BoardDesktop board) {
        super(board);
    }

    public BoardDesktop getBoard() {
        return (BoardDesktop) board;
    }

    protected void handleHumanTurn(BoardTile position, ColoredPawn currentPlayer) {
        computeValidMoves(currentPlayer);
        if (thereAreNoValidMoves()) handleNoValidMovesCase(currentPlayer);
        else handleHumanMove(position);
    }

    private void handleHumanMove(BoardTile position) {
        getBoard().cancelPreviousSuggestion();
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) getBoard().updateGUIBoard(move.get());
        else {
            JOptionPane.showMessageDialog(null, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
            // set the suggestion property of the buttons to be true for the valid moves
            for (ValidMove validMove : getValidMoves())
                getBoard().enableSuggestionAtTile(validMove.position());
        }
    }

    protected void handleBotTurn(Player bot){
        computeValidMoves(bot.getPlayerColor());
        if (thereAreNoValidMoves()) handleNoValidMovesCase(bot.getPlayerColor());
        else handleBotMove(bot);
    }

    private void handleBotMove(Player bot) {
        ValidMove move = null;
        try {
            move = bot.askForAMove(this);
        } catch (QuitGameException | UndoException ignored) {}
        getBoard().updateGUIBoard(move);
    }

    void handleNoValidMovesCase(ColoredPawn currentColor) {
        String currentPlayerName = currentColor == ColoredPawn.BLACK ? "black" : "white";
        computeValidMoves(currentColor.opposite());
        if (thereAreNoValidMoves()) gameOverHandle();
        else {
            JOptionPane.showMessageDialog(null, "No valid moves for the " + currentPlayerName + " player!", "Skipped turn", JOptionPane.INFORMATION_MESSAGE);
            CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        }
    }

    private void gameOverHandle() {
        SwingUtilities.invokeLater(() -> {
            GuiManager.disableBoard();
            int blackScore = computeScoreForPlayer(ColoredPawn.BLACK);
            int whiteScore = computeScoreForPlayer(ColoredPawn.WHITE);
            JFrame outcomeFrame = new OutcomeFrame(blackScore, whiteScore).getFrame();
            outcomeFrame.setVisible(true);
        });
    }

    protected void updateBoard(int numberOfStepsBack) {
        IntStream.range(0, numberOfStepsBack).forEach(i -> CurrentPlayerPanel.updateCurrentPlayerLiveLabel());
        getBoard().updateButtonGrid();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer((ColoredPawn.BLACK)),
                computeScoreForPlayer(ColoredPawn.WHITE));
    }
}
