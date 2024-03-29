package desktop.utilities;

import board.Board;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import mechanics.Game;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Class which extends the Game class present in the core module.
 * The play method is overridden since in GUI games there is no while loop to play the game, but the game is played by
 * listening the events of the buttons.
 */
public class GameDesktop extends Game {
    private final GuiManager guiManager;
    private final GameControllerDesktop gameController;

    /**
     * Initialize a new GUI game with the given board and players.
     *
     * @param board       the board of the game
     * @param blackPlayer the black player
     * @param whitePlayer the white player
     */
    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerDesktop(board);
        guiManager = new GuiManager(this);
        addListenersToButtonGrid();
        if (!blackPlayer.isHumanPlayer())
            handleBotPlayerTurn(blackPlayer);
    }

    /**
     * Start the game and play.
     */
    @Override
    public void play() {
        SwingUtilities.invokeLater(guiManager::setFrameVisible);
    }

    private void addListenersToButtonGrid() {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                BoardTile position = new BoardTile(i, j);
                gameController.addListenerToButton(position, getButtonListener(position));
            }
    }

    private ActionListener getButtonListener(BoardTile position) {
        return e -> {
            GuiManager.disableBoard();
            handleHumanAndBotTurns(position);
            GuiManager.enableBoard();
        };
    }

    private void handleHumanAndBotTurns(BoardTile position) {
        gameController.handleHumanTurn(position, getCurrentPlayerColor());
        BoardDesktop currentBoard = gameController.getBoard();
        if (aNewMoveHasBeenMade(currentBoard)) {
            previousSteps.add(currentBoard.copy());
            swapTurn();
            Player currentPlayer = isBlackToMove() ? blackPlayer : whitePlayer;
            if (!currentPlayer.isHumanPlayer())
                handleBotPlayerTurn(currentPlayer);
        }
        gameController.computeValidMoves(getCurrentPlayerColor());
        if (gameController.thereAreNoValidMoves())
            gameController.handleNoValidMovesCase(getCurrentPlayerColor());
    }

    private void handleBotPlayerTurn(Player currentPlayer) {
        gameController.handleBotTurn(currentPlayer);
        BoardDesktop currentBoard = gameController.getBoard();
        if (aNewMoveHasBeenMade(currentBoard))
            previousSteps.add(currentBoard.copy());
        swapTurn();
    }

    private boolean aNewMoveHasBeenMade(BoardDesktop board) {
        return !board.equals(previousSteps.get(previousSteps.size() - 1));
    }

    /**
     * Prepare the board to undo the last move.
     */
    @Override
    public void undoLastMove() {
        GuiManager.disableBoard();
        gameController.getBoard().disableSuggestions();
        int numberOfStepsBack = thereIsAComputerPlayer() ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            undo(numberOfStepsBack);
        GuiManager.enableBoard();
    }

    /**
     * Cancel the last move done and go back to the previous state of the game.
     */
    @Override
    protected void undo(int numberOfStepsBack) {
        super.undo(numberOfStepsBack);
        gameController.updateBoardAfterUndo(numberOfStepsBack);
    }

    /**
     * Returns the manager of the game.
     *
     * @return the manager of the game
     */
    @Override
    public GameControllerDesktop getGameController() {
        return gameController;
    }
}