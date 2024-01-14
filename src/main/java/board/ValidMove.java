package board;

import board.coords.BoardTile;
import board.coords.Direction;

import java.util.ArrayList;

public class ValidMove {
    private BoardTile position;
    private ArrayList<Direction> validDirections;

    public ValidMove(BoardTile position, ArrayList<Direction> validDirections) {
        this.position = position;
        this.validDirections = validDirections;
    }
    public ValidMove(BoardTile position) {
        this.position = position;
        validDirections = new ArrayList<>();
    }

    public ArrayList<Direction> getValidDirections() {
        return validDirections;
    }
    public BoardTile getPosition() {
        return position;
    }

    public void addDirection(Direction direction) {
        validDirections.add(direction);
    }
    }
