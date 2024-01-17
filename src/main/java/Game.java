import board.Board;
import board.Pawn;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;

public class Game {

    Board board;
    ValidMovesChecker movesChecker;
    int skippedTurns;
    ArrayList<Board> boards;

    Player whitePlayer;
    Player blackPlayer;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.board = board;
        this.boards = new ArrayList<>();
        Board currentSituation = new Board();
        currentSituation.copy(board);
        boards.add(currentSituation);
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
                if (skippedTurns == 2) break;
                continue;
            }
            ValidMove chosen = validMovesAsker();
            if (chosen == null) continue;
            board.makeMove(chosen);
            backUpBoard();
        }
        board.GameOver();
        printFinalScores(board);
    }

    private ValidMove validMovesAsker() {
        ValidMove chosen = null;
        try {
            chosen = validMoveHandler();
        } catch (QuitGameException e) {
            System.out.println("Quitting the game.\n Thanks for playing!");
            System.exit(0);
        } catch (UndoException e) {
            undo();
            return null;
        } catch (Exception e) {
            System.out.println("Something went wrong. Quitting the game.\n Thanks for playing!");
            System.exit(0);
        }
        return chosen;
    }

    private void backUpBoard() {
        Board currentSituation = new Board();
        currentSituation.copy(board);
        boards.add(currentSituation);
    }

    private ValidMove validMoveHandler() throws QuitGameException, UndoException{
        skippedTurns = 0;
        ValidMove validMove;
        Player currentPlayer = board.isBlackToMove() ? blackPlayer : whitePlayer;
        validMove = currentPlayer.askForAMove(movesChecker);
        return validMove;
    }

    private void undo() {
        int nHumanPlayer = 0;
        nHumanPlayer += (whitePlayer.getClass().equals(Human.class)) ? 1 : 0;
        nHumanPlayer += (blackPlayer.getClass().equals(Human.class)) ? 1 : 0;
        int nUndos = (nHumanPlayer == 1) ? 2 : 1;
        if (boards.size() > nUndos) {
            undo(board, boards, nUndos);
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
            // System.exit(0);   // Does not allow benchmarking
            return;
        }
        System.out.println("No valid moves for the current player. Changing turn.");
        board.changeTurn();

    }

    private void undo(Board board, ArrayList<Board> boards, int times) {
        System.out.println("Undoing last move.");
        for (int i = 0; i < times; i++) {
            boards.remove(boards.size() - 1);
        }
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

        Player human = new Human();
        Player bot = new SmartPlayer();
        Player bot2 = new RandomPlayer();
        Game game = new Game(new Board(), bot, human);
        game.play();

//        int smartWon = 0;
//        for(int i = 0; i < 150; i++) {
//            game = new Game(new Board(), bot, bot2);
//            game.play();
//            int nBlack = game.board.computeScoreForPlayer(Pawn.BLACK);
//            int nWhite = game.board.computeScoreForPlayer(Pawn.WHITE);
//            if (nBlack > nWhite)
//                smartWon++;
//        }
//        System.out.println("------------------------------------------------");
//        System.out.println("Smart won " + smartWon + " times out of 150 games.");

    }
}
