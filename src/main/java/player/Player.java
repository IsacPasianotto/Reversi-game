package player;

import board.Board;
import board.ValidMove;
import board.coords.Direction;

public class Player {
    public Player() {
    }

    public void makeMove(Board board, ValidMove move) {
        for (Direction direction : move.getValidDirections()){
          board.flipLineOfPawns(move.getPosition(), direction);
        }
        board.changeTurn();
    }




}
