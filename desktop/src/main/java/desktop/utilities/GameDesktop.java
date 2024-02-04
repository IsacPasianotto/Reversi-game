package desktop.utilities;

import board.Board;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import mechanics.Game;
import player.Player;

import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class GameDesktop extends Game {
    public final GuiManager guiManager;
    private final GameControllerDesktop gameController;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerDesktop(board);
        guiManager = new GuiManager(this, board);
        addListenersToButtonGrid();
        if (!Player.isHumanPlayer(blackPlayer)) {
            gameController.handleBotTurn(blackPlayer);
            swapTurn();
        }
    }

    private void addListenersToButtonGrid() {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++){
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

    void handleHumanAndBotTurns(BoardTile position){
        gameController.handleHumanTurn(position, getCurrentPlayerColor());
        BoardDesktop currentBoard = gameController.getBoard();
        if (aNewMoveHasBeenMade(currentBoard)) {
            previousSteps.add(currentBoard.copy());
            swapTurn();
            Player currentPlayer = isBlackToMove()? blackPlayer : whitePlayer;
            if (!Player.isHumanPlayer(currentPlayer)) {
                gameController.handleBotTurn(currentPlayer);
                currentBoard = gameController.getBoard();
                if (aNewMoveHasBeenMade(currentBoard))
                    previousSteps.add(currentBoard.copy());
                swapTurn();
            }
        }
        gameController.computeValidMoves(getCurrentPlayerColor());
        if (gameController.thereAreNoValidMoves())
            gameController.handleNoValidMovesCase(getCurrentPlayerColor());
    }

    private boolean aNewMoveHasBeenMade(BoardDesktop board) {
        return !board.equals(previousSteps.getLast());
    }

    @Override
    public void undoLastMove() {
        GuiManager.disableBoard();
        gameController.getBoard().disableSuggestions();
        int numberOfStepsBack = thereIsAComputerPlayer() ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            undo(numberOfStepsBack);
        GuiManager.enableBoard();
    }

    @Override
    public void undo(int numberOfStepsBack) {
        super.undo(numberOfStepsBack);
        gameController.updateBoard(numberOfStepsBack);
    }

    @Override
    public GameControllerDesktop getGameController() {
        return gameController;
    }
}