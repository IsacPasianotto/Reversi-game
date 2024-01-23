package mechanics;

import board.Board;
import board.ValidMove;
import player.Player;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Game {

    protected final Board board;
    protected final Player whitePlayer;
    protected final Player blackPlayer;
    protected int skippedTurns;
    protected final ValidMovesChecker validMovesChecker;
    protected boolean gameOver;

    protected final ArrayList<Board> previousSteps;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.board = board;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        skippedTurns = 0;
        previousSteps = new ArrayList<>(0);
        previousSteps.add(this.board.copy());
        validMovesChecker = new ValidMovesChecker(this.board);
        gameOver = false;
    }

    public void play() {
        while (!board.isFull() && (skippedTurns < 2)) {
            validMovesChecker.computeValidMoves();
            if (validMovesChecker.numberOfValidMoves() == 0) {
                skippedTurns++;
            } else {
                skippedTurns = 0;
                ValidMove chosenMove = selectAValidMoveOrUndo();
                if (chosenMove == null) continue;
                board.applyMoveToBoard(chosenMove);
                previousSteps.add(board.copy());
            }
            validMovesChecker.swapTurn();
        }
        GameOver();
    }

    private ValidMove selectAValidMoveOrUndo() {
        Player currentPlayer = validMovesChecker.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            return currentPlayer.askForAMove(validMovesChecker);
        } catch (QuitGameException e) {
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        } catch (UndoException e) {
            undoLastMove();
        } catch (RuntimeException e) {
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        }
        return null;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void GameOver() {
        gameOver = true;
    }

    public Board getBoard() {
        return board.copy();
    }

    public void undoLastMove() {
        int numberOfHumanPlayers = (whitePlayer.getClass().equals(Human.class) ? 1 : 0) +
                (blackPlayer.getClass().equals(Human.class) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            board.importBoardFrom(previousSteps.getLast());
            IntStream.range(0, numberOfStepsBack).forEach(i -> validMovesChecker.swapTurn());
        }
    }
}
