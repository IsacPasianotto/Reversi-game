package mechanics;

import board.ValidMove;
import board.coords.BoardTile;

import java.util.ArrayList;

public class InputMoveValidator {

    public InputMoveValidator() {
        isValid = false;
    }

    public void IsValid(ArrayList<ValidMove> acceptable, BoardTile move) {
        for (ValidMove validMove : acceptable) {
            if (validMove.getPosition().equals(move)) {
                this.isValid = true;
                return;
            }
        }
        System.out.println("Invalid move entered.");
        String validMoves = "Valid moves are: ";
        for (ValidMove validMove : acceptable) {
            validMoves += validMove.getPosition().toString() + " ";
        }
        System.out.println(validMoves);
    }

    public boolean isValid() {
        return isValid;
    }

   private boolean isValid;

}
