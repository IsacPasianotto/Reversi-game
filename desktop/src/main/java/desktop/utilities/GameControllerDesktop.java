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
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.QuitGameException;
import player.human.UndoException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameControllerDesktop extends GameController {
    final BoardDesktop board;

    public GameControllerDesktop(BoardDesktop board) {
        super(board);
        this.board = board;
    }

    void handleHumanAndBotTurns(BoardTile position, ArrayList<Board> previousSteps, boolean thereIsAComputerPlayer, boolean difficultyIsHard) {
        handleHumanTurn(position);
        if (aNewMoveHasBeenMade(previousSteps)) {
            previousSteps.add(board.copy());
            if (thereIsAComputerPlayer) {
                Player bot = difficultyIsHard ? new SmartPlayer() : new RandomPlayer();
                handleBotTurn(bot);
                if (aNewMoveHasBeenMade(previousSteps))
                    previousSteps.add(board.copy());
            }
        }
        computeValidMoves();
        if (thereAreNoValidMoves()) {
            handleNoValidMovesCase();
        }
    }

    private boolean aNewMoveHasBeenMade(ArrayList<Board> previousSteps) {
        return !board.equals(previousSteps.getLast());
    }

    private void handleHumanTurn(BoardTile position) {
        computeValidMoves();
        if (thereAreNoValidMoves()) handleNoValidMovesCase();
        else handleHumanMove(position);
    }

    private void handleHumanMove(BoardTile position) {
        board.cancelPreviousSuggestion();
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) updateGUIBoard(move.get());
        else {
            JOptionPane.showMessageDialog(null, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
            // set the suggestion property of the buttons to be true for the valid moves
            for (ValidMove validMove : getValidMoves())
                board.enableSuggestionAtTile(validMove.position());
        }
    }

    protected void handleBotTurn(Player bot){
        computeValidMoves();
        if (thereAreNoValidMoves()) handleNoValidMovesCase();
        else handleBotMove(bot);
    }

    private void handleBotMove(Player bot) {
        ValidMove move = null;
        try {
            move = bot.askForAMove(this);
        } catch (QuitGameException | UndoException ignored) {}
        updateGUIBoard(move);
    }

    private void updateGUIBoard(ValidMove move) {
        board.applyMoveToBoard(move);
        swapTurn();
        board.updateButtonGrid();
        CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                computeScoreForPlayer(ColoredPawn.WHITE));
    }

    void handleNoValidMovesCase() {
        ColoredPawn currentPlayerColor = getCurrentPlayerColor();
        String currentPlayerName = currentPlayerColor == ColoredPawn.BLACK ? "black" : "white";
        swapTurn();
        computeValidMoves();
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

    void undo(int numberOfStepsBack, ArrayList<Board> previousSteps) {
        IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
        importBoardFrom(previousSteps.getLast());
        for (int i = 0; i < numberOfStepsBack; i++) {
            swapTurn();
            CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        }
        board.updateButtonGrid();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                computeScoreForPlayer(ColoredPawn.WHITE));
    }
}
