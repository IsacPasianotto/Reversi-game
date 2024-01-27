import board.Board;
import board.ColoredPawn;
import mechanics.Game;
import player.Player;

import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.stream.IntStream;

public class DesktopGame extends Game{

    public DesktopController controller;
    public DesktopGame(DesktopBoard board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        controller = new DesktopController(board);
    }

    public ActionListener getButtonListener(int x, int y) {
        return e -> {
            controller.handleButtonPress(x, y);
            previousSteps.add(controller.getBoard());
        };

    }

    public ActionListener getUndoListener(DesktopController controller, DesktopBoard board) {
        return e -> undoLastMoveDesktop(controller, board);
    }

    public void undoLastMoveDesktop(DesktopController controller, DesktopBoard board) {
        int numberOfHumanPlayers = (isHumanPlayer(whitePlayer) ? 1 : 0) +
                (isHumanPlayer(blackPlayer) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;

        if (previousSteps.size() > numberOfStepsBack) {
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            controller.importBoardFrom(previousSteps.getLast());
            IntStream.range(0, numberOfStepsBack).forEach(i -> controller.swapTurn());
            IntStream.range(0, numberOfStepsBack).forEach(i -> GuiManager.updatePlayerTurnContextLabel());
            board.updateButtonGrid();
            GuiManager.updateScoreContextLabel(controller.computeScoreForPlayer(ColoredPawn.BLACK),
                    controller.computeScoreForPlayer(ColoredPawn.WHITE));
        }
    }
}
