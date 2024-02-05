package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

/**
 * Record class to represent a valid move on the board. A valid move is a position on the board and a list of directions
 * where the pawns of the opposite color can be flipped. The current color is also stored, to be used when applying the
 * move to the board.
 * @see BoardTile
 * @see Direction
 * @see ColoredPawn
 * @param position the position of the move
 * @param validDirections the list of directions where the pawns of the opposite color can be flipped
 * @param currentColor the color of the pawns that are going to be flipped
 */
public record ValidMove(BoardTile position, ArrayList<Direction> validDirections, ColoredPawn currentColor) {
}
