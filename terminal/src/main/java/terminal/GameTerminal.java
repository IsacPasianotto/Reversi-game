package terminal;

import board.Board;
import board.ValidMove;
import mechanics.Game;
import player.Player;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.Optional;

public class GameTerminal extends Game {
    //private final GameControllerTerminal gameController;
    public GameTerminal(Board board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerTerminal(board);
    }

    @Override
    public void play() {
        while (getGameController().isBoardNotFull() && (skippedTurns < 2)) {
            System.out.println(getGameController().getBoard());
            System.out.println("Current player: " + getCurrentPlayerColor());
            playASingleTurn();
            if (skippedTurns == 1) System.out.println("No valid moves for the current player. Changing turn.");
            else if (skippedTurns == 2) System.out.println("No valid moves for both players. Game over.");
        }
        getGameController().printFinalScores();
        blackPlayer.close();
        whitePlayer.close();
    }

    @Override
    public GameControllerTerminal getGameController() {
        return (GameControllerTerminal) this.gameController;
    }

    @Override
    protected Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            if (Player.isHumanPlayer(currentPlayer))
                System.out.print("Enter your move: ");
            return Optional.of(currentPlayer.askForAMove(getGameController()));
        } catch (QuitGameException e) {
            System.out.println(e.getMessage());
            exit();
        } catch (UndoException e) {
            undoLastMove();
        } catch (RuntimeException e) {
            System.out.println("\nSomething went wrong. Closing the game.\n");
            exit();
        }
        return Optional.empty();
    }

    @Override
    protected void undoLastMove() {
        int numberOfStepsBack = thereIsAComputerPlayer()? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            System.out.println("Undoing last move.");
            undo(numberOfStepsBack);
        } else
            System.out.println("Cannot undo anymore.");
    }
}
