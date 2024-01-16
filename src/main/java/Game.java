import board.Board;
import board.ValidMove;
import board.coords.BoardTile;
import player.human.UndoException;
import mechanics.ValidMovesChecker;
import player.human.UserInputReader;

import java.util.ArrayList;
import java.util.Optional;

public class Game {

    public void play(Board board, UserInputReader reader) {
        ValidMovesChecker movesChecker = new ValidMovesChecker(board);
        int skippedTurns = 0;
        ArrayList<Board> boards = new ArrayList<>();

        while (!board.isFull()) {
            Board tmp = new Board();
            tmp.copy(board);
            boards.add(tmp);
            System.out.println(board);
            movesChecker.computeValidMoves();
            if (movesChecker.getValidMoves().isEmpty()) {
                skippedTurns++;
                if (skippedTurns == 2) {
                    System.out.println("No valid moves for both players. Game over.");
                    break;
                }
                System.out.println("No valid moves for the current player. Changing turn.");
                board.changeTurn();
                continue;
            }
            skippedTurns = 0;
            ValidMove validMove;
            try {
                validMove = askForAMove(reader, movesChecker);
            }catch (UndoException e){
                if (boards.size() > 1) {
                    System.out.println("Undoing last move.");
                    boards.remove(boards.size() - 1);
                    board.copy(boards.get(boards.size() - 1));
                } else {
                    System.out.println("Cannot undo anymore.");
                }
                continue;
            }
            board.makeMove(validMove);
        }
        board.GameOver();
    }

    ValidMove askForAMove(UserInputReader reader, ValidMovesChecker movesChecker) throws UndoException{
        System.out.print("Enter your move: ");
        while (true) {
            try {
                return getMove(reader, movesChecker);
            } catch (IllegalArgumentException e) {
                movesChecker.printErrorMessage();
            }
        }
    }

    ValidMove getMove(UserInputReader reader, ValidMovesChecker movesChecker) throws IllegalArgumentException, UndoException {
        String readInput = reader.readInput();
        if (readInput.equalsIgnoreCase("undo")){
            throw new UndoException();
        }
        BoardTile chosen = new BoardTile(readInput);
        Optional<ValidMove> validMove = movesChecker.IsValid(chosen);
        if (validMove.isEmpty()) {
            throw new IllegalArgumentException("Not acceptable move entered.");
        } else  {
            return new ValidMove(chosen, validMove.get().getValidDirections());
        }
    }

    public static void main(String[] args) {
        System.out.println("There will be the start of the game");
        Board board = new Board();
        //Player bot = new Player();
        Game game = new Game();

        try (UserInputReader reader = new UserInputReader()) {
            game.play(board, reader);
        } catch (Exception e) {
            System.out.println("Game unexpectedly closed");
        }
    }
}
