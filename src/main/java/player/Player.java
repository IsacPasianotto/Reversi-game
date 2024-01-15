package player;

import board.Board;
import board.Pawn;
import board.ValidMove;
import board.coords.Direction;

public class Player {
    public Player() {}

    public void makeMove(Board board, ValidMove move) {
        for (Direction direction : move.getValidDirections()) {
            System.out.println(direction);
            board.flipLineOfPawns(move.getPosition(), direction);
        }
        Pawn currentPawn = board.isBlackToMove() ? Pawn.BLACK : Pawn.WHITE;
        board.setPositionValue(move.getPosition(),currentPawn);
        board.changeTurn();
    }

}
