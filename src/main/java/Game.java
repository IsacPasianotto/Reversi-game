import board.Board;
import board.Pawn;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;
import player.computer.RandomPlayer;
import player.human.Human;

import java.util.ArrayList;

public class Game {

    Board board;
    ValidMovesChecker movesChecker;
    int skippedTurns;
    ArrayList<Board> boards;

    Player whitePlayer;
    Player blackPlayer;

    public Game(Board board, Player whitePlayer, Player blackPlayer) {
        this.board = board;
        this.boards = new ArrayList<>();
        this.movesChecker = new ValidMovesChecker(board);
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        skippedTurns = 0;
    }

    public void play () {
        while (!board.isFull()) {
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                noMovesAllowedHandler();
                continue;
            }
            ValidMove chosen = validMoveHandler();
            System.out.println("Player " + board.getCurrentPlayer() + " chose " + chosen.getPosition());
            board.makeMove(chosen);
            backUpBoard();
        }
        board.GameOver();
        printFinalScores(board);
    }

    private void backUpBoard() {
        Board currentSituation = new Board();
        currentSituation.copy(board);
        boards.add(currentSituation);
    }

    private ValidMove validMoveHandler() {
        skippedTurns = 0;
        ValidMove validMove;
        Player currentPlayer = board.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            validMove = currentPlayer.askForAMove(movesChecker);
            return validMove;
        }
        catch (Exception e) {
            System.out.println("Game unexpectedly closed");
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        }
        return null; // this will never happen
    }

    private void undo() {
        if (boards.size() > 1) {
            undo(board, boards);
        } else {
            System.out.println("Cannot undo anymore.");
        }
    }


    private void noMovesAllowedHandler() {
        skippedTurns++;
        if (skippedTurns == 2) {
            board.GameOver();
            System.out.println("No valid moves for both players. Game over.");
            printFinalScores(board);
            System.exit(0);
        }
        System.out.println("No valid moves for the current player. Changing turn.");
        board.changeTurn();

    }

    private void temp() {
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

        Player bot = new RandomPlayer();
        Player human = new Human();

        Game game = new Game(new Board(), bot, human);

        game.play();
    }
}
