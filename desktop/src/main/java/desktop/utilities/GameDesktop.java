package desktop.utilities;

import board.ColoredPawn;
import desktop.gui.components.CurrentPlayerPanel;
import desktop.gui.components.CurrentScorePanel;
import mechanics.Game;
import player.Player;

import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class GameDesktop extends Game {
    private final GameControllerDesktop controller;

    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        controller = new GameControllerDesktop(board);
    }

    public ActionListener getButtonListener(int x, int y) {
        return e -> {
            controller.handleButtonPress(x, y);
            if (controller.getBoard().equals(previousSteps.getLast()))
                return;
            previousSteps.add(controller.getBoard());
        };

    }

    public GameControllerDesktop getGameControllerDesktop() {
        return controller;
    }

    public ActionListener getUndoListener(GameControllerDesktop controller, BoardDesktop board) {
        return e -> undoLastMoveDesktop(controller, board);
    }

    public void undoLastMoveDesktop(GameControllerDesktop controller, BoardDesktop board) {
        board.disableButtonGrid();
        controller.cancelPreviousSuggestion();
        int numberOfHumanPlayers = (isHumanPlayer(whitePlayer) ? 1 : 0) +
                (isHumanPlayer(blackPlayer) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;

        if (previousSteps.size() > numberOfStepsBack) {
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            controller.importBoardFrom(previousSteps.getLast());
            IntStream.range(0, numberOfStepsBack).forEach(i -> controller.swapTurn());

            IntStream.range(0, numberOfStepsBack).forEach(i -> CurrentPlayerPanel.updateCurrentPlayerLiveLabel());
            board.updateButtonGrid();
            CurrentScorePanel.updateCurrentScoreLiveLabel(controller.computeScoreForPlayer(ColoredPawn.BLACK),
                    controller.computeScoreForPlayer(ColoredPawn.WHITE));
        }
        board.enableButtonGrid();
    }


}