package desktop.utilities;

import board.Board;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import mechanics.Game;
import player.Player;

import java.awt.event.ActionListener;

public class GameDesktop extends Game {
    //private final GameControllerDesktop gameController;
    public final GuiManager guiManager;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerDesktop(board);
        guiManager = new GuiManager(this, board);
        addListenersToButtonGrid();
        if (!Player.isHumanPlayer(blackPlayer)) {
            getGameController().handleBotTurn(blackPlayer);
            swapTurn();
        }
    }

    private void addListenersToButtonGrid() {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++){
                BoardTile position = new BoardTile(i, j);
                getGameController().getBoard().addListenerToButton(position, getButtonListener(position));
            }
    }

    @Override
    public void undoLastMove() {
        GuiManager.disableBoard();
        getGameController().getBoard().cancelPreviousSuggestion();
        int numberOfStepsBack = thereIsAComputerPlayer() ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            undo(numberOfStepsBack);
        GuiManager.enableBoard();
    }

    private ActionListener getButtonListener(BoardTile position) {
        return e -> {
            GuiManager.disableBoard();
            handleHumanAndBotTurns(position);
            GuiManager.enableBoard();
        };
    }

    void handleHumanAndBotTurns(BoardTile position){
        getGameController().handleHumanTurn(position, getCurrentPlayerColor());
        BoardDesktop currentBoard = getGameController().getBoard();
        if (aNewMoveHasBeenMade(currentBoard)) {
            previousSteps.add(currentBoard.copy());
            swapTurn();
            Player currentPlayer = isBlackToMove()? blackPlayer : whitePlayer;
            if (!Player.isHumanPlayer(currentPlayer)) {
                getGameController().handleBotTurn(currentPlayer);
                currentBoard = getGameController().getBoard();
                if (aNewMoveHasBeenMade(currentBoard))
                    previousSteps.add(currentBoard.copy());
                swapTurn();
            }
        }
        getGameController().computeValidMoves(getCurrentPlayerColor());
        if (getGameController().thereAreNoValidMoves())
            getGameController().handleNoValidMovesCase(getCurrentPlayerColor());
    }

    private boolean aNewMoveHasBeenMade(BoardDesktop board) {
        return !board.equals(previousSteps.getLast());
    }

    @Override
    public void undo(int numberOfStepsBack) {
        super.undo(numberOfStepsBack);
        getGameController().updateBoard(numberOfStepsBack);
    }



    public GameControllerDesktop getGameController() {
        return (GameControllerDesktop) this.gameController;
    }
}