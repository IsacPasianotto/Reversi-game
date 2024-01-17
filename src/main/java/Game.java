import board.Board;
import board.Pawn;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;

public class Game {
    Board board;
    Player whitePlayer;
    Player blackPlayer;
    ValidMovesChecker movesChecker;
    int skippedTurns;
    ArrayList<Board> previousSteps;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        skippedTurns = 0;
        this.previousSteps = new ArrayList<>();
        previousSteps.add(this.board.copy());
        this.movesChecker = new ValidMovesChecker(board);
    }

    public void play () {
        while (!board.isFull()) {
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                skippedTurns++;
                thereAreNoAllowedMoves();
                if (skippedTurns == 2) break;
                continue;
            }
            skippedTurns = 0;
            ValidMove chosenMove = tryToSelectAValidMove();
            if (chosenMove == null) continue;
            board.applyMoveToBoard(chosenMove);
            previousSteps.add(board.copy());
        }
        board.GameOver();
        printFinalScores(board);
    }

    private ValidMove tryToSelectAValidMove() {
        try {
            Player currentPlayer = board.isBlackToMove() ? blackPlayer : whitePlayer;
            return currentPlayer.askForAMove(movesChecker);
        } catch (QuitGameException e) {
            System.out.println("Quitting the game.\n Thanks for playing!");
            System.exit(0);
        } catch (UndoException e) {
            undoLastMove();
            return null;
        } catch (Exception e) {
            System.out.println("Something went wrong. Closing the game.\n");
            System.exit(0);
        }
        return null;
    }

    private void undoLastMove() {
        int numberOfHumanPlayers = ((whitePlayer.getClass().equals(Human.class)) ? 1 : 0) + ((blackPlayer.getClass().equals(Human.class)) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            System.out.println("Undoing last move.");
            for (int i = 0; i < numberOfStepsBack; i++)
                previousSteps.remove(previousSteps.size() - 1);
            board.importBoardFrom(previousSteps.get(previousSteps.size()-1));
        } else
            System.out.println("Cannot undo anymore.");
    }

    private void thereAreNoAllowedMoves() {
        if (skippedTurns == 2) {
            board.GameOver();
            System.out.println("No valid moves for both players. Game over.");
            printFinalScores(board);
            // System.exit(0);   // Does not allow benchmarking
            return;
        }
        System.out.println("No valid moves for the current player. Changing turn.");
        board.swapTurn();

    }

    private void printFinalScores(Board board) {
        int whiteScore = board.computeScoreForPlayer(Pawn.WHITE);
        int blackScore = board.computeScoreForPlayer(Pawn.BLACK);
        System.out.println("FINAL SCORE: " + Pawn.WHITE + ": " + whiteScore + ", " + Pawn.BLACK + ": " + blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
    }
}
