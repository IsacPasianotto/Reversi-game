package board;

import player.Coords;

public class RelativeNeighbour {
    public RelativeNeighbour(Board board, Coords startingPoint, Coords direction){
        this.startingPoint = startingPoint;
        this.direction = direction;
        this.neighbourPawn = board.getPositionValue(startingPoint.add(direction));
    }

    Coords startingPoint;
    Coords direction;
    Pawn neighbourPawn;


}
