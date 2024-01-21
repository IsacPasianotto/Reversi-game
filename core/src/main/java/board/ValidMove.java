package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

public class ValidMove {
    final private ColoredPawn currentColor;
    final private BoardTile position;
    final private ArrayList<Direction> validDirections;

    public ValidMove(BoardTile position, ArrayList<Direction> validDirections, ColoredPawn currentColor) {
        this.position = position;
        this.validDirections = validDirections;
        this.currentColor = currentColor;
    }

    public ArrayList<Direction> getValidDirections() {
        return validDirections;
    }

    public BoardTile getPosition() {
        return position;
    }

    public ColoredPawn getCurrentColor() {
        return currentColor;
    }
}
