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

public class Game {
    protected final Player whitePlayer;
    protected final Player blackPlayer;
    protected final ArrayList<Board> previousSteps;
    private final GameController gameController;
    protected int skippedTurns;
    private boolean blackToMove;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        gameController = new GameController(board);
        previousSteps = new ArrayList<>(Board.BOARD_SIZE*Board.BOARD_SIZE);
        previousSteps.add(board.copy());
        skippedTurns = 0;
        blackToMove = true;
    }

    public void play() {
        while (skippedTurns < 2) playASingleTurn();
        blackPlayer.close();
        whitePlayer.close();
    }

    public GameController getGameController() {
        return gameController;
    }

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

    protected void undoLastMove() {
        int numberOfStepsBack = thereIsAComputerPlayer()? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack)
            undo(numberOfStepsBack);
    }

    protected void exit() {
        blackPlayer.close();
        whitePlayer.close();
        System.exit(0);
    }

    protected boolean thereIsAComputerPlayer() {
        return !Player.isHumanPlayer(whitePlayer) || !Player.isHumanPlayer(blackPlayer);
    }

    protected void undo(int numberOfStepsBack) {
        IntStream.range(0, numberOfStepsBack).forEach(i -> previousSteps.removeLast());
        getGameController().importBoardFrom(previousSteps.getLast());
        IntStream.range(0, numberOfStepsBack).forEach(i -> swapTurn());
    }

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

    public ColoredPawn getCurrentPlayerColor() {
        return blackToMove ? ColoredPawn.BLACK : ColoredPawn.WHITE;
    }

    public boolean isBlackToMove() {
        return blackToMove;
    }

    public void swapTurn() {
        blackToMove = !blackToMove;
    }



}
