package mechanics;

import board.Board;
import board.ValidMove;
import player.Player;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class Game {
    protected final Player whitePlayer;
    protected final Player blackPlayer;
    private final GameController gameController;
    protected boolean gameOver;
    protected final ArrayList<Board> previousSteps;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        gameController = new GameController(board);
        previousSteps = new ArrayList<>(0);
        previousSteps.add(board.copy());
        gameOver = false;
    }

    public void play() {
        int skippedTurns = 0;
        while (!gameController.isBoardFull() && (skippedTurns < 2)) {
            gameController.computeValidMoves();
            if (gameController.numberOfValidMoves() == 0) {
                skippedTurns++;
            } else {
                skippedTurns = 0;
                Optional<ValidMove> chosenMove = selectAValidMoveOrUndo();
                if (chosenMove.isEmpty()) continue;
                gameController.applyMoveToBoard(chosenMove.get());
                previousSteps.add(gameController.getBoard());
            }
            gameController.swapTurn();
        }
        GameOver();
        blackPlayer.close();
        whitePlayer.close();
    }

    private Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = gameController.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            return Optional.of(currentPlayer.askForAMove(gameController));
        } catch (QuitGameException | RuntimeException e) {
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        } catch (UndoException e) {
            undoLastMove();
        }
        return Optional.empty();
    }

    protected void undoLastMove() {
        int numberOfHumanPlayers = (isHumanPlayer(whitePlayer) ? 1 : 0) +
                (isHumanPlayer(blackPlayer) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            gameController.importBoardFrom(previousSteps.getLast());
            IntStream.range(0, numberOfStepsBack).forEach(i -> gameController.swapTurn());
        }
    }

    protected boolean isGameOver() {
        return gameOver;
    }

    protected void GameOver() {
        gameOver = true;
    }

    protected boolean isHumanPlayer(Player player) {
        return player.getClass().equals(Human.class);
    }
}
