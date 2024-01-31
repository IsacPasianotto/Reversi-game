package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import desktop.gui.other.components.GameSettings;
import mechanics.Game;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;

import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class GameDesktop extends Game {
    final GameControllerDesktop gameController;
    public final GuiManager guiManager;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);

        gameController = new GameControllerDesktop(board);
        guiManager = new GuiManager(board, this);
        addListenersToButtonGrid(board);
    }

    private void addListenersToButtonGrid(BoardDesktop board) {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++)
                board.addListenersToButtonGrid(i,j, getButtonListener(i,j));
    }

    public ActionListener getUndoListener(BoardDesktop board) {
        return e -> undoLastMoveDesktop(board);
    }

    public void undoLastMoveDesktop(BoardDesktop board) {
        board.disableButtonGrid();
        gameController.board.cancelPreviousSuggestion();
        int numberOfHumanPlayers = (isHumanPlayer(whitePlayer) ? 1 : 0) +
                (isHumanPlayer(blackPlayer) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;

        if (previousSteps.size() > numberOfStepsBack) {
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            gameController.importBoardFrom(previousSteps.getLast());
            IntStream.range(0, numberOfStepsBack).forEach(i -> gameController.swapTurn());

            IntStream.range(0, numberOfStepsBack).forEach(i -> CurrentPlayerPanel.updateCurrentPlayerLiveLabel());
            board.updateButtonGrid();
            CurrentScorePanel.updateCurrentScoreLiveLabel(gameController.computeScoreForPlayer(ColoredPawn.BLACK),
                    gameController.computeScoreForPlayer(ColoredPawn.WHITE));
        }
        board.enableButtonGrid();
    }

    private boolean thereIsAComputerPlayer() {
        return !isHumanPlayer(whitePlayer) || !isHumanPlayer(blackPlayer);
    }

    private boolean isDifficultyHard() {
        return whitePlayer instanceof SmartPlayer || blackPlayer instanceof SmartPlayer;
    }


    public ActionListener getButtonListener(int x, int y) {
        return e -> {
            gameController.handleHumanTurn(new BoardTile(x, y));
            if (!((Board)gameController.board).equals(previousSteps.getLast())) {
                previousSteps.add(gameController.board.copy());
                if (thereIsAComputerPlayer()) {
                    Player bot = isDifficultyHard() ? new SmartPlayer() : new RandomPlayer();
                    gameController.handleBotTurn(bot);
                    if (!gameController.board.equals(previousSteps.getLast()))
                        previousSteps.add(gameController.board.copy());
                }
            }
        };
    }

}