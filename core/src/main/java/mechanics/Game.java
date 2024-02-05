package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import player.Player;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * This class is responsible for managing the game mechanics. It retrieves the desired moves from the players,
 * ensures with its GameController that the moves are legitimate and applies them to the board.
 * It also keeps track of the game state and the previous steps to allow the players to undo their moves.
 * It does that in a loop until the game is over.
 * @see GameController
 * @see Player
 */
public class Game {
    /**
     * The Player instances representing the white player
     * @see Player
     */
    protected final Player whitePlayer;
    /**
     * The Player instances representing the black player
     * @see Player
     */
    protected final Player blackPlayer;
    /**
     * The list of previous steps of the game, used to allow the players to undo their moves.
     * @see Board
     */
    protected final ArrayList<Board> previousSteps;
    /**
     * The number of turns skipped by the current player. If it reaches 2, the game is over.
     */
    protected int skippedTurns;
    private final GameController gameController;
    private boolean blackToMove;

    /**
     * Constructor for the Game class that initializes the game with the given board and players.
     * @see Player
     * @param board the board the game will be played on, likely a new board.
     * @param blackPlayer a Player instance representing the black player.
     * @param whitePlayer a Player instance representing the white player.
     */
    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        gameController = new GameController(board);
        previousSteps = new ArrayList<>(Board.BOARD_SIZE*Board.BOARD_SIZE);
        previousSteps.add(board.copy());
        skippedTurns = 0;
        blackToMove = true;
    }

    /**
     * Return the GameController instance used by the Game.
     * @return the GameController instance used by the Game.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Return the current player color as a ColoredPawn.
     * @see ColoredPawn
     * @return black if it is the black player's turn, white otherwise.
     */
    public ColoredPawn getCurrentPlayerColor() {
        return blackToMove ? ColoredPawn.BLACK : ColoredPawn.WHITE;
    }

    /**
     * Check if it is the black player's turn or white player's turn.
     * @return true if it is the black player's turn, false otherwise.
     */
    public boolean isBlackToMove() {
        return blackToMove;
    }

    /**
     * Swap the current player's turn.
     */
    public void swapTurn() {
        blackToMove = !blackToMove;
    }

    /**
     * Play the game until it is over.
     */
    public void play() {
        while (skippedTurns < 2) playASingleTurn();
        blackPlayer.close();
        whitePlayer.close();
    }

    /**
     * Play a single turn of the game, which means:
     * <ul>
     *     <li> Check if the current player has any valid moves. If not, skip the turn. </li>
     *     <li> Wait for the current player input (a move or an undo request). </li>
     *     <li> ensure the move is valid, ask again if it is not. </li>
     *     <li> Apply the move to the board and change the current player. </li>
     * </ul>
     */
    protected void playASingleTurn() {
        getGameController().computeValidMoves(getCurrentPlayerColor());
        if (getGameController().thereAreNoValidMoves()) {
            skippedTurns++;
            swapTurn();
        } else {
            skippedTurns = 0;
            Optional<ValidMove> chosenMove = selectAValidMoveOrUndo();
            if (chosenMove.isPresent()) {
                getGameController().applyMoveToBoard(chosenMove.get());
                previousSteps.add(getGameController().getBoard().copy());
                swapTurn();
            }
        }
    }

    /**
     * Process the current player input and return the chosen move as an ValidMove or an empty Optional object.
     * It also catches the QuitGameException and the UndoException the Human player can throw.
     * @see ValidMove
     * @return a ValidMove object if the player chose a legal move, an empty Optional object otherwise.
     */
    protected Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            return Optional.of(currentPlayer.askForAMove(getGameController()));
        } catch (QuitGameException | RuntimeException e) {
            exit();
        } catch (UndoException e) {
            undoLastMove();
        }
        return Optional.empty();
    }

    /**
     * Close the game and exit the program.
     */
    protected void exit() {
        blackPlayer.close();
        whitePlayer.close();
        System.exit(0);
    }

    /**
     * If there is at least one move to undo, undo the last move.
     * If there is a computer player, this will undo two moves to get back to the last human player turn.
     */
    protected void undoLastMove() {
        int numberOfStepsBack = thereIsAComputerPlayer()? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            undo(numberOfStepsBack);
    }

    /**
     * Undo the last numberOfStepsBack moves and swap the turn accordingly.
     * @param numberOfStepsBack the number of moves to undo.
     */
    protected void undo(int numberOfStepsBack) {
        IntStream.range(0, numberOfStepsBack).forEach(i -> previousSteps.removeLast());
        getGameController().importBoardFrom(previousSteps.getLast());
        IntStream.range(0, numberOfStepsBack).forEach(i -> swapTurn());
    }

    /**
     * Check if at least one of the players is a computer player.
     * @see Player
     * @see player.human.Human
     * @see player.computer.SmartPlayer
     * @see player.computer.RandomPlayer
     * @return true if at least one of the players is a computer player, false otherwise.
     */
    protected boolean thereIsAComputerPlayer() {
        return !Player.isHumanPlayer(whitePlayer) || !Player.isHumanPlayer(blackPlayer);
    }

}
