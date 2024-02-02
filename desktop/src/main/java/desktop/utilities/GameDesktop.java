package desktop.utilities;

import board.Board;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import mechanics.Game;
import player.Player;
import player.computer.SmartPlayer;

import java.awt.event.ActionListener;

public class GameDesktop extends Game {
    private final GameControllerDesktop gameController;
    public final GuiManager guiManager;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        gameController = new GameControllerDesktop(board);
        guiManager = new GuiManager(this, board);
        addListenersToButtonGrid();
        if (!isHumanPlayer(blackPlayer))
            gameController.handleBotTurn(blackPlayer);
    }

    private void addListenersToButtonGrid() {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++){
                BoardTile position = new BoardTile(i, j);
                gameController.board.addListenerToButton(position, getButtonListener(position));
            }
    }

    public void undoLastMoveDesktop() {
        GuiManager.disableBoard();
        gameController.board.cancelPreviousSuggestion();
        int numberOfStepsBack = thereIsAComputerPlayer() ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            gameController.undo(numberOfStepsBack, previousSteps);
        GuiManager.enableBoard();
    }

    private boolean thereIsAComputerPlayer() {
        return !isHumanPlayer(whitePlayer) || !isHumanPlayer(blackPlayer);
    }

    private boolean difficultyIsHard() {
        return whitePlayer.getClass().equals(SmartPlayer.class)  ||
                blackPlayer.getClass().equals(SmartPlayer.class);
    }

    private ActionListener getButtonListener(BoardTile position) {
        return e -> {
            GuiManager.disableBoard();
            gameController.handleHumanAndBotTurns(position, previousSteps, thereIsAComputerPlayer(), difficultyIsHard());
            GuiManager.enableBoard();
        };
    }

}