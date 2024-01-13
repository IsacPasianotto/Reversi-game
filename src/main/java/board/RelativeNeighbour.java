package board;

import player.Coords;
import player.Direction;

public class RelativeNeighbour {
    public RelativeNeighbour(Board board, Coords startingPoint, Direction direction){
        this.startingPoint = startingPoint;
        this.direction = direction;
        this.neighbourPawn = board.getPositionValue(startingPoint.add(direction));
    }

    Coords startingPoint;
    Direction direction;
    Pawn neighbourPawn;


}
