package desktop;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import mechanics.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import static board.Board.BOARD_SIZE;

public class GameControllerDesktop extends GameController {

    private final BoardDesktop board;

    @Override
    public BoardDesktop getBoard() {
        return board;
    }
    public ActionListener getButtonListener(int x, int y) {
        return e -> handleButtonPress(x, y);
    }


    public void handleButtonPress(int x, int y) {
        System.out.println("clicked");
        System.out.println(x + " " + y);
        computeValidMoves();
        BoardTile position = new BoardTile(x, y);
        Optional<ValidMove> move = isValid(position);
        if (move.isPresent()) {
            board.applyMoveToBoard(move.get());
            updateBoard();
            swapTurn();
        }
    }


    public GameControllerDesktop(BoardDesktop board) {
        super(board);
        this.board = board;

    }
    public void updateBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board.getPositionColor(i, j) == ColoredPawn.BLACK) {
                    Image img = BoardDesktop.black.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(img);
                    board.buttonGrid[i][j].setIcon(icon);
                } else if (board.getPositionColor(i, j) == ColoredPawn.WHITE) {
                    Image img = BoardDesktop.white.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(img);
                    board.buttonGrid[i][j].setIcon(icon);
                }
            }
        }
    }

}
