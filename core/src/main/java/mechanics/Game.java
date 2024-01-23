package mechanics;

import board.Board;
import board.ValidMove;
import player.Player;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;
import board.ColoredPawn;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Game {
    protected final Player whitePlayer;
    protected final Player blackPlayer;
    protected int skippedTurns;
    public GameController gameController;
    protected boolean gameOver;
    protected final ArrayList<Board> previousSteps;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        skippedTurns = 0;
        gameController = new GameController(new Board());
        previousSteps = new ArrayList<>(0);
        previousSteps.add(gameController.getBoard().copy());
        gameOver = false;
    }

    public void play() {
        while (!gameController.isBoardFull() && (skippedTurns < 2)) {
            gameController.computeValidMoves();
            if (gameController.numberOfValidMoves() == 0) {
                skippedTurns++;
            } else {
                skippedTurns = 0;
                ValidMove chosenMove = selectAValidMoveOrUndo();
                if (chosenMove == null) continue;
                gameController.applyMoveToBoard(chosenMove);
                previousSteps.add(gameController.getBoard().copy());
            }
            gameController.swapTurn();
        }
        GameOver();
        whitePlayer.close();
        blackPlayer.close();
    }

    private ValidMove selectAValidMoveOrUndo() {
        Player currentPlayer = gameController.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            return currentPlayer.askForAMove(gameController);
        } catch (QuitGameException | RuntimeException e) {
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        } catch (UndoException e) {
            undoLastMove();
        }
        return null;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void GameOver() {
        gameOver = true;
    }


    public void undoLastMove() {
        int numberOfHumanPlayers = (whitePlayer.getClass().equals(Human.class) ? 1 : 0) +
                (blackPlayer.getClass().equals(Human.class) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            gameController.importBoardFrom(previousSteps.getLast());
            for (int i = 0; i < numberOfStepsBack; i++) gameController.swapTurn();
        }
    }
}
