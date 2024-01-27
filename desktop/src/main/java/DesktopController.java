import board.Board;
import board.ValidMove;
import mechanics.GameController;

import board.coords.BoardTile;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Optional;

public class DesktopController extends GameController {
    private final DesktopBoard board;
    public DesktopController(DesktopBoard board) {
        super((Board) board);
        this.board = board;


    }

    public ActionListener getButtonListener(int x, int y) {
        return e -> handleButtonPress(x, y);
    }

    private void handleButtonPress(int x, int y) {
        computeValidMoves();
        BoardTile position = new BoardTile(x, y);
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) {
            board.applyMoveToBoard(move.get());
            swapTurn();
            board.updateButtonGrid();
            GuiManager.updatePlayerTurnContextLabel();
        }
    }


}
