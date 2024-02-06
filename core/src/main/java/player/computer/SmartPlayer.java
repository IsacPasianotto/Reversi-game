package player.computer;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Computer player that makes the move based on the future score of the board after the move.
 * It chooses the move that will lead to the highest score in the next turn for the player (does not consider more depth calculations).
 * It is used to implement the harder difficulty level.
 * @see Player
 */
public class SmartPlayer implements Player {

    private final ColoredPawn color;

    /**
     * Constructor for the SmartPlayer class.
     * @param color The color, represented by a ColoredPawn, the smart player will play as.
     */
    public SmartPlayer(ColoredPawn color) {
        this.color = color;
    }

    /**
     * Asks the GameController for the list of valid moves in the current state of the game and returns the one that will lead to the highest score in the next turn for the player.
     * @param gameController The GameController object that is used to get the list of valid moves.
     * @see ValidMove
     * @return The move, as a ValidMove object, which is the strongest for the player for a depth of 1 computation.
     */
    @Override
    public ValidMove askForAMove(GameController gameController) {
        List<Integer> futureScores = new ArrayList<>(0);
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        for (ValidMove validMove : validMoves) {
            Board fakeBoard = gameController.getBoard().copy();
            fakeBoard.applyMoveToBoard(validMove);
            futureScores.add(fakeBoard.computeScoreForPlayer(color));
        }
        int maxScore = Collections.max(futureScores);
        return validMoves.get(futureScores.indexOf(maxScore));
    }

    /**
     * Checks if the player is a human player or not
     * @return true if the player is a human player, false otherwise
     */
    public boolean isHumanPlayer() {
        return false;
    }

    /**
     * Closes the SmartPlayer object.
     */
    public void close() { }

    /**
     * Returns the color of the pawns the SmartPlayer is playing as.
     * @return The color of the pawns the SmartPlayer is playing as.
     */
    public ColoredPawn getPlayerColor(){
        return color;
    }

}
