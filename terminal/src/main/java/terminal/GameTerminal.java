package terminal;

import board.Board;
import board.ValidMove;
import mechanics.Game;
import player.Player;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.Optional;

/**
 * Class which overrides the Game class to implement the terminal version of the game.
 * It uses the GameControllerTerminal class to handle the game mechanics and the player inputs and prints the game status in the terminal.
 * @see Game
 */
public class GameTerminal extends Game {
    private final GameControllerTerminal gameController;

    /**
     * Calls the constructor of the superclass and initializes the GameControllerTerminal.
     * @see Player
     * @param board the board the game will be played on, likely a new board.
     * @param blackPlayer a Player instance representing the black player.
     * @param whitePlayer a Player instance representing the white player.
     */
    public GameTerminal(Board board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerTerminal(board);
    }

    /**
     * Main loop for the game. It will keep asking the players for a move until the game is over, printing the game status in the terminal.
     */
    @Override
    public void play() {
        while (skippedTurns < 2) {
            System.out.println(gameController.getBoard());
            System.out.println("Current player: " + getCurrentPlayerColor());
            playASingleTurn();
            if (skippedTurns == 1) System.out.println("No valid moves for the current player. Changing turn.");
            else if (skippedTurns == 2) System.out.println("No valid moves for both players. Game over.");
        }
        gameController.printFinalScores();
        blackPlayer.close();
        whitePlayer.close();
    }

    /**
     * Return the current instance of GameControllerTerminal.
     * @return the current instance of GameControllerTerminal.
     * @see GameControllerTerminal
     */
    @Override
    public GameControllerTerminal getGameController() {
        return gameController;
    }

    /**
     * Method to get the move from the player, asking for the input in the terminal.
     * @return an Optional containing the move as a ValidMove object if the move is valid, an empty Optional otherwise.
     */
    @Override
    protected Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            if (Player.isHumanPlayer(currentPlayer))
                System.out.print("Enter your move: ");
            return Optional.of(currentPlayer.askForAMove(gameController));
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

    /**
     *  Calls the undo method of the superclass to undo the last move and inform the user printing a message in the terminal.
     */
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
