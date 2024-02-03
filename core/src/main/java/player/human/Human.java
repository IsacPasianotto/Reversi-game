package player.human;

import board.ColoredPawn;
import board.ValidMove;
import mechanics.GameController;
import player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class Human implements Player {
    private final UserInputReader reader;
    private final ColoredPawn color;

    public Human(ColoredPawn color) {
        reader = new UserInputReader(new BufferedReader(new InputStreamReader(System.in)));
        this.color = color;
    }

    public ValidMove askForAMove(GameController gameController) throws UndoException, QuitGameException {
        Optional<ValidMove> enteredMove = Optional.empty();
        while (enteredMove.isEmpty()) {
            String readInput = reader.readInput();
            enteredMove = gameController.getMove(readInput);
        }
        return enteredMove.get();
    }

    public void close() {
        reader.close();
    }

    public ColoredPawn getPlayerColor(){
        return color;
    }
}
