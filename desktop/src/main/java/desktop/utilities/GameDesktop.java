package desktop.utilities;

import board.ColoredPawn;
import board.coords.BoardTile;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;
import mechanics.Game;
import player.Player;

import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class GameDesktop extends Game {
    private final GameControllerDesktop gameController;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        gameController = new GameControllerDesktop(board);
    }

    public ActionListener getButtonListener(int x, int y) {
        return e -> {
            gameController.handleButtonPress(new BoardTile(x, y));
            if (!gameController.getBoard().equals(previousSteps.getLast()))
                previousSteps.add(gameController.getBoard());
        };

    }

//    public GameControllerDesktop getGameControllerDesktop() {
//        return gameController;
//    }

    public ActionListener getUndoListener(BoardDesktop board) {
        return e -> undoLastMoveDesktop(board);
    }

    public void undoLastMoveDesktop(BoardDesktop board) {
        board.disableButtonGrid();
        gameController.cancelPreviousSuggestion();
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


}