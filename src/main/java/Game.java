import board.Board;
import board.Pawn;
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
        Board tmp = new Board();
        tmp.copy(board);
        boards.add(tmp);
        while (!board.isFull()) {
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
            tmp = new Board();
            tmp.copy(board);
            boards.add(tmp);
        }
        board.GameOver();
        int whiteScore = 0;
        int blackScore = 0;
        for (int i = 0; i < Board.BOARD_SIZE; i++){
            for (int j = 0; j < Board.BOARD_SIZE; j++){
                if (board.getPositionValue(i, j) == Pawn.WHITE) whiteScore++;
                if (board.getPositionValue(i, j) == Pawn.BLACK) blackScore++;
            }
        }
        System.out.println("FINAL SCORE: "+Pawn.WHITE+": "+whiteScore+", "+Pawn.BLACK+": "+blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
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
