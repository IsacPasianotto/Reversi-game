import board.Board;
import board.Pawn;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.computer.RandomPlayer;
import player.human.UndoException;
import player.human.UserInputReader;

import java.util.ArrayList;

public class Game {

    Board board;
    ValidMovesChecker movesChecker;
    UserInputReader reader;
    int skippedTurns;
    ArrayList<Board> boards;

    RandomPlayer bot;

    public Game(Board board, UserInputReader reader) {
        this.board = board;
        movesChecker = new ValidMovesChecker(board);
        this.reader = reader;
        skippedTurns = 0;
        boards = new ArrayList<>();
        Board currentSituation = new Board();
        currentSituation.copy(board);
        boards.add(currentSituation);
    }

    public Game(Board board, UserInputReader human, RandomPlayer bot) {
        this.board = board;
        movesChecker = new ValidMovesChecker(board);
        this.reader = human;
        skippedTurns = 0;
        boards = new ArrayList<>();
        Board currentSituation = new Board();
        currentSituation.copy(board);
        boards.add(currentSituation);
        this.bot = bot;
    }



    public void playBot() {
        while (!board.isFull()) {
            // HUMAN --> black
            // BOT --> white
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                try{
                    noMovesAllowedHandler();
                }catch (RuntimeException e) {
                    System.out.println(e.getMessage());

                    break;
                }
                continue;
            }
            skippedTurns = 0;
            ValidMove validMove;

            if (board.isBlackToMove()) {

                try {
                    validMove = reader.askForAMove(movesChecker);
                } catch (UndoException e) {
                    if (boards.size() > 1) {
                        undo(board, boards);
                    } else {
                        System.out.println("Cannot undo anymore.");
                    }
                    continue;
                }
                board.makeMove(validMove);
            } else {
                try {
                    ValidMove chosen = bot.chooseMove(movesChecker);
                    board.makeMove(chosen);

                } catch (Exception e) {}

            }
            Board currentSituation = new Board();
            currentSituation.copy(board);
            boards.add(currentSituation);
        }

        board.GameOver();
        printFinalScores(board);
    }





    public void play() {
        while (!board.isFull()) {
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                try{
                    noMovesAllowedHandler();
                }catch (RuntimeException e) {
                    System.out.println(e.getMessage());

                    break;
                }
                continue;
            }
            skippedTurns = 0;
            ValidMove validMove;
            try {
                validMove = reader.askForAMove(movesChecker);
            }catch (UndoException e){
                if (boards.size() > 1) {
                    undo(board, boards);
                } else {
                    System.out.println("Cannot undo anymore.");
                }
                continue;
            }
            board.makeMove(validMove);

            Board currentSituation = new Board();
            currentSituation.copy(board);
            boards.add(currentSituation);
        }
        board.GameOver();
        printFinalScores(board);
    }

    private void noMovesAllowedHandler() {
        skippedTurns++;
        if (skippedTurns == 2) {
            // board.GameOver();
            printFinalScores(board);
            throw new RuntimeException("No valid moves for both players. Game over.");
        }
        System.out.println("No valid moves for the current player. Changing turn.");
        board.changeTurn();
    }

    private void undo(Board board, ArrayList<Board> boards) {
        System.out.println("Undoing last move.");
        boards.remove(boards.size() - 1);
        board.copy(boards.get(boards.size() - 1));
    }

    private void printFinalScores(Board board) {
        int whiteScore = 0;
        int blackScore = 0;
        for (int i = 0; i < Board.BOARD_SIZE; i++){
            for (int j = 0; j < Board.BOARD_SIZE; j++){
                if (board.getPositionValue(i, j) == Pawn.WHITE) whiteScore++;
                if (board.getPositionValue(i, j) == Pawn.BLACK) blackScore++;
            }
        }
        System.out.println("FINAL SCORE: "+Pawn.WHITE+": "+whiteScore+", "+Pawn.BLACK+": "+blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
    }

    public static void main(String[] args) {
        System.out.println("There will be the start of the game");
       //Player bot = new Player();


//       // human -vs human:
//        Game game = new Game(new Board(), new UserInputReader());

        RandomPlayer bot = new RandomPlayer();
        UserInputReader human = new UserInputReader();

        Game game = new Game(new Board(), human, bot);

        try (UserInputReader reader = new UserInputReader()) {
            //game.play();
            game.playBot();
        } catch (Exception e) {
            System.out.println("Game unexpectedly closed");
        }
    }
}
