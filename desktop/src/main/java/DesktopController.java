import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;

import board.coords.BoardTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Optional;
import java.util.stream.IntStream;

public class DesktopController extends GameController {
    private final DesktopBoard board;
    public DesktopController(DesktopBoard board) {
        super((Board) board);
        this.board = board;
    }


    protected void handleButtonPress(int x, int y) {
        computeValidMoves();
        BoardTile position = new BoardTile(x, y);
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) {
            board.applyMoveToBoard(move.get());
            swapTurn();
            board.updateButtonGrid();
            GuiManager.updatePlayerTurnContextLabel();
            GuiManager.updateScoreContextLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                    computeScoreForPlayer(ColoredPawn.WHITE));
        }
        else {
            //validMoves.forEach(validMove -> board.buttonGrid[validMove.position().x()][validMove.position().y()].setBackground(Color.MAGENTA));
            JOptionPane.showMessageDialog(null, "Invalid move!");
            // color the valid moves in the board
        }
    }

}
