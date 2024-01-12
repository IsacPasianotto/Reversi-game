package board;

import player.Coords;

public class RelativeNeighbour {
    public RelativeNeighbour(Board board, Coords startingPoint, Coords position){
        this.startingPoint = startingPoint;
        this.position = position;
        this.neighbourPawn = board.getPositionValue(position);
    }

    Coords startingPoint;
    Coords position;
    Pawn neighbourPawn;

}
