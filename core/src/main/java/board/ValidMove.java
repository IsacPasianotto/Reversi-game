package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

public record ValidMove(BoardTile position, ArrayList<Direction> validDirections, ColoredPawn currentColor) {
}
