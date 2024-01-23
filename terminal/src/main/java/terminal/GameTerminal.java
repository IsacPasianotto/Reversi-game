package terminal;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.Game;
import player.Player;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.Optional;
import java.util.stream.IntStream;

public class GameTerminal extends Game {

    public GameTerminal(Board board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerTerminal(new Board());
    }

    public void play() {
        while (!gameController.isBoardFull() && (skippedTurns < 2)) {
            System.out.println(gameController.getBoard());
            System.out.println("Current player: " + gameController.getCurrentPlayerColor());
            gameController.computeValidMoves();
            if (gameController.numberOfValidMoves() == 0) {
                skippedTurns++;
                if (skippedTurns == 1) System.out.println("No valid moves for the current player. Changing turn.");
                else System.out.println("No valid moves for both players. Game over.");
            } else {
                skippedTurns = 0;
                Optional<ValidMove> chosenMove = selectAValidMoveOrUndo();
                if (chosenMove.isEmpty()) continue;
                gameController.applyMoveToBoard(chosenMove.get());
                previousSteps.add(gameController.getBoard().copy());
            }
            gameController.swapTurn();
        }
        GameOver();
        System.out.println(gameController.getBoard());
        printFinalScores();
        blackPlayer.close();
        whitePlayer.close();
    }

    private Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = gameController.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            if (currentPlayer.getClass().equals(Human.class))
                System.out.print("Enter your move: ");
            return Optional.of(currentPlayer.askForAMove(gameController));
        } catch (QuitGameException e) {
            System.out.println(e.getMessage());
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        } catch (UndoException e) {
            undoLastMove();
        } catch (RuntimeException e) {
            System.out.println("\nSomething went wrong. Closing the game.\n");
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        }
        return Optional.empty();
    }

    private void GameOver() {
        gameOver = true;
    }

    public void undoLastMove() {
        int numberOfHumanPlayers = (whitePlayer.getClass().equals(Human.class) ? 1 : 0) +
                (blackPlayer.getClass().equals(Human.class) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            System.out.println("Undoing last move.");
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.removeLast());
            gameController.importBoardFrom(previousSteps.getLast());
            IntStream.range(0, numberOfStepsBack).forEach(i -> gameController.swapTurn());
        } else
            System.out.println("Cannot undo anymore.");
    }

    private void printFinalScores() {
        int whiteScore = gameController.computeScoreForPlayer(ColoredPawn.WHITE);
        int blackScore = gameController.computeScoreForPlayer(ColoredPawn.BLACK);
        System.out.println("FINAL SCORE: " + ColoredPawn.WHITE + ": " + whiteScore + ", " + ColoredPawn.BLACK + ": " + blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
    }

}
