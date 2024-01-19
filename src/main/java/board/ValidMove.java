package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

public class ValidMove {
    final private BoardTile position;
    final private ArrayList<Direction> validDirections;

    public ValidMove(BoardTile position, ArrayList<Direction> validDirections) {
        this.position = position;
        this.validDirections = validDirections;
    }
    public ArrayList<Direction> getValidDirections() {
        return validDirections;
    }
    public BoardTile getPosition() {
        return position;
    }
}
