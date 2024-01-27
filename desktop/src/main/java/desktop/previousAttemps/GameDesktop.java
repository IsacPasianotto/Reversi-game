//package desktop.previousAttemps;
//
//import mechanics.Game;
//import player.Player;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//
//import static desktop.previousAttemps.GameControllerDesktop.currentPlayerLabel;
//
//public class GameDesktop extends Game {
//    private final GameControllerDesktop gameController;
//    public JFrame frame;
//
//
//    public GameDesktop(BoardDesktop board, Player blackPlayer, Player whitePlayer) {
//        super(board, blackPlayer, whitePlayer);
//        this.gameController = new GameControllerDesktop(board);
//        this.frame = getjFrame();
//    }
//
//    public void play(){
//        gameController.computeValidMoves();
//        frame.setVisible(true);
//    }
//
//
//    private JFrame getjFrame() {
//        JFrame frame = new JFrame("Reversi");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        frame.setSize(800, 800);
//
//        BoardDesktop board = new BoardDesktop(); // this shouldn't be here!
//
//        JToolBar dashboard = new JToolBar(null, JToolBar.VERTICAL);
//        dashboard.setFloatable(false);
//
//        JButton button = new JButton("Undo");
//        button.addActionListener(e -> {
//            System.out.println("Undo");
//            //undoLastMove();
//        });
//        dashboard.add(button);
//        dashboard.add(new JLabel("Current player: "));
//        dashboard.add(currentPlayerLabel);
//
//
//        // Create a glue component to center the button vertically
//        Component verticalGlue1 = Box.createVerticalGlue();
//        Component verticalGlue2 = Box.createVerticalGlue();
//        dashboard.add(verticalGlue1);
//        dashboard.add(verticalGlue2);
//
//
//        JPanel globalView = new JPanel();
//        //globalView.setBorder(new LineBorder(Color.BLACK));
//
//        globalView.setBorder(new EmptyBorder(4, 4, 4, 4));
//        globalView.setLayout(new BorderLayout());
//
//        globalView.add(board.getPanel(), BorderLayout.CENTER);
//        globalView.add(dashboard, BorderLayout.EAST);
//
//        frame.add(globalView);
//        return frame;
//    }
//
//
//
//}
