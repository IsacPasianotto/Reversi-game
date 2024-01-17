package board;


import board.coords.BoardTile;
import board.coords.Direction;

import java.util.Arrays;

public class Board {
    public static final int BOARD_SIZE = 8;
    private boolean blackToMove;
    private Pawn[][] board;
    private boolean gameOver;

    public Board() {
        board = new Pawn[BOARD_SIZE][BOARD_SIZE];
        // initialize the board with starting position
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = Pawn.EMPTY;
        setPositionValue(new BoardTile("d4"), Pawn.WHITE);
        setPositionValue(new BoardTile("e5"), Pawn.WHITE);
        setPositionValue(new BoardTile("e4"), Pawn.BLACK);
        setPositionValue(new BoardTile("d5"), Pawn.BLACK);
        this.blackToMove = true;
    }

    public boolean isBlackToMove() {
        return this.blackToMove;
    }
    public Pawn getCurrentPlayer() {
        return blackToMove ? Pawn.BLACK : Pawn.WHITE;
    }

    public Pawn getCurrentOpponent() { return blackToMove ? Pawn.WHITE : Pawn.BLACK; }

    public Pawn getPositionValue(BoardTile position) {
        return board[position.getX()][position.getY()];
    }
    public Pawn getPositionValue(int x, int y) {
        return board[x][y];
    }
    public void setPositionValue(BoardTile position, Pawn value) {
        board[position.getX()][position.getY()] = value;
    }

    public void makeMove(ValidMove move) {
        for (Direction direction : move.getValidDirections()) {
            flipLineOfPawns(move.getPosition(), direction);
        }
        setPositionValue(move.getPosition(),getCurrentPlayer());
        changeTurn();
    }
    private void flipLineOfPawns(BoardTile position, Direction direction) {
        BoardTile currentPosition = position.add(direction);
        while (getPositionValue(currentPosition) != getCurrentPlayer()){
            setPositionValue(currentPosition, getCurrentPlayer());
            currentPosition = currentPosition.add(direction);
        }
    }

    public void changeTurn(){
        this.blackToMove = !this.blackToMove;
    }

    public boolean isFull() {
        return Arrays.stream(this.board).allMatch(row -> Arrays.stream(row).noneMatch(pawn -> pawn == Pawn.EMPTY));
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void GameOver() {
        this.gameOver = true;
    }
    public void copy(Board another){
        for (int i = 0; i < BOARD_SIZE; i++)
            System.arraycopy(another.board[i], 0, this.board[i], 0, BOARD_SIZE);
        this.blackToMove = another.blackToMove;
        this.gameOver = another.gameOver;
    }

    public int computeScoreForPlayer(Pawn player) {
        int score = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++)
                if (this.getPositionValue(i, j) == player) score++;
        }
        return score;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int whiteScore = 0;
        int blackScore = 0;

        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++){
            result.append(" ").append(i+1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++) {
                result.append("  ").append(getPositionValue(i,j)).append((j == BOARD_SIZE - 1) ? "  ┃" : "  ╎");
                   if (getPositionValue(i, j) == Pawn.WHITE) whiteScore++;
                   if (getPositionValue(i, j) == Pawn.BLACK) blackScore++;
            }
            result.append((i == BOARD_SIZE - 1) ? "\n" : "\n   ┣-----+-----+-----+-----+-----+-----+-----+-----┫\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        result.append("\nPlayer ").append(blackToMove ? Pawn.BLACK : Pawn.WHITE).append(" to move\n");
        result.append("White: ").append(whiteScore).append(" Black: ").append(blackScore);
        return result.toString();
    }
}


