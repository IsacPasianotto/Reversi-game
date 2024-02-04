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
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameControllerDesktop extends GameController {
    private final BoardDesktop board;

    /**
     * Creates a new GameControllerDesktop with the given board.
     * @param board the board of the game
     */
    public GameControllerDesktop(BoardDesktop board) {
        super(board);
        this.board = board;
    }

    void handleHumanTurn(BoardTile position, ColoredPawn currentPlayer) {
        computeValidMoves(currentPlayer);
        if (thereAreNoValidMoves()) handleNoValidMovesCase(currentPlayer);
        else handleHumanMove(position);
    }

    private void handleHumanMove(BoardTile position) {
        board.disableSuggestions();
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent())
            board.updateGUIBoard(move.get());
        else {
            JOptionPane.showMessageDialog(null, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
            board.enableSuggestions(getValidMoves());
        }
    }

    void handleBotTurn(Player bot){
        computeValidMoves(bot.getPlayerColor());
        if (thereAreNoValidMoves()) handleNoValidMovesCase(bot.getPlayerColor());
        else handleBotMove(bot);
    }

    private void handleBotMove(Player bot) {
        ValidMove move = null;
        try {
            move = bot.askForAMove(this);
        } catch (QuitGameException | UndoException ignored) {}
        board.updateGUIBoard(move);
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
            OutcomeFrame outcomeFrame = new OutcomeFrame(blackScore, whiteScore);
            outcomeFrame.setVisible(true);
        });
    }

    void updateBoardAfterUndo(int numberOfStepsBack) {
        IntStream.range(0, numberOfStepsBack).forEach(i -> CurrentPlayerPanel.updateCurrentPlayerLiveLabel());
        board.updateButtonGrid();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer((ColoredPawn.BLACK)),
                computeScoreForPlayer(ColoredPawn.WHITE));
    }

    void addListenerToButton(BoardTile position, ActionListener listener){
        board.addListenerToButton(position, listener);
    }

    /**
     * Returns the board of the game.
     * @return the board of the game
     */
    @Override
    public BoardDesktop getBoard() {
        return board;
    }
}
