package mechanics;

import board.Board;
import board.ValidMove;
import player.Player;
import player.computer.SmartPlayer;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;
import java.util.Optional;

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
            if (gameController.thereAreNoValidMoves()) {
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

    public GameController getGameController() {
        return gameController;
    }

    private Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = gameController.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            return Optional.of(currentPlayer.askForAMove(gameController));
        } catch (QuitGameException | RuntimeException e) {
            exit();
        } catch (UndoException e) {
            undoLastMove();
        }
        return Optional.empty();
    }

    protected void undoLastMove() {
        int numberOfStepsBack = thereIsAComputerPlayer()? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            gameController.undo(numberOfStepsBack, previousSteps);
    }

    protected void exit() {
        blackPlayer.close();
        whitePlayer.close();
        System.exit(0);
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

    protected boolean thereIsAComputerPlayer() {
        return !isHumanPlayer(whitePlayer) || !isHumanPlayer(blackPlayer);
    }

    protected boolean difficultyIsHard() {
        return whitePlayer.getClass().equals(SmartPlayer.class) ||
                blackPlayer.getClass().equals(SmartPlayer.class);
    }

}
