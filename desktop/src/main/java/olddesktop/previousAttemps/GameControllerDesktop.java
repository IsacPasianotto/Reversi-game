//package desktop.previousAttemps;
//
//import board.ValidMove;
//import board.coords.BoardTile;
//import mechanics.GameController;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.util.Optional;
//
//public class GameControllerDesktop extends GameController {
//
//    private final BoardDesktop board;
//    public static JLabel currentPlayerLabel;
//
//
//    public GameControllerDesktop(BoardDesktop board) {
//        super(board);
//        this.board = board;
//        currentPlayerLabel = new JLabel("Black");
//        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 35));
//
//
//        currentPlayerLabel.addPropertyChangeListener("text", evt -> {
//            currentPlayerLabel.setText((String) evt.getNewValue());
//            currentPlayerLabel.repaint();
//        });
//    }
//
//    @Override
//    public BoardDesktop getBoard() {
//        return board;
//    }
//
//    public void swapCurrentPlayerLabel() {
//        if (currentPlayerLabel.getText().equals("Black")) {
//            currentPlayerLabel.setText("White");
//        } else {
//            currentPlayerLabel.setText("Black");
//        }
//    }
//
//
//    public ActionListener getButtonListener(int x, int y) {
//        return e -> handleButtonPress(x, y);
//    }
//
//    public void handleButtonPress(int x, int y) {
//        computeValidMoves();
//        BoardTile position = new BoardTile(x, y);
//        Optional<ValidMove> move = isValid(position);
//        if (move.isPresent()) {
//            board.applyMoveToBoard(move.get());
//            swapTurn();
//            board.updateBoard();
//            swapCurrentPlayerLabel();
//        }
//    }
//}
//
