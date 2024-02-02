package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import mechanics.Game;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.Human;

import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class GameDesktop extends Game {
    final GameControllerDesktop gameController;
    public final GuiManager guiManager;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);

        gameController = new GameControllerDesktop(board);
        guiManager = new GuiManager(this, board);
        addListenersToButtonGrid();
        if (!(blackPlayer.getClass().equals(Human.class)))
            gameController.handleBotTurn(blackPlayer);
    }

    private void addListenersToButtonGrid() {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++)
                gameController.board.addListenerToButton(i,j, getButtonListener(i,j));
    }

    public ActionListener getUndoListener(BoardDesktop board) {
        return e -> undoLastMoveDesktop(board);
    }

    @Override
    public GameControllerDesktop getGameController() {
        return gameController;
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

    boolean thereIsAComputerPlayer() {
        return !isHumanPlayer(whitePlayer) || !isHumanPlayer(blackPlayer);
    }

    boolean isDifficultyHard() {
        return whitePlayer.getClass().equals(SmartPlayer.class)  ||
                blackPlayer.getClass().equals(SmartPlayer.class);
    }


    public ActionListener getButtonListener(int x, int y) {
        return e -> {
            gameController.board.disableButtonGrid();
            gameController.handleHumanTurn(new BoardTile(x, y));
            if (!((Board) gameController.board).equals(previousSteps.getLast())) {
                previousSteps.add(gameController.board.copy());
                if (thereIsAComputerPlayer()) {
                    System.out.println("Bot's turn");
                    Player bot = isDifficultyHard() ? new SmartPlayer() : new RandomPlayer();
                    gameController.handleBotTurn(bot);
                    if (!((Board) gameController.board).equals(previousSteps.getLast()))
                        previousSteps.add(gameController.board.copy());
                }
            }
            gameController.computeValidMoves();
            if (gameController.getValidMoves().isEmpty()) {
                gameController.handleNoValidMovesCase();
            }
            gameController.board.enableButtonGrid();
        };
    }
}