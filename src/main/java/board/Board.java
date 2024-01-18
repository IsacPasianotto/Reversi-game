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

    public void applyMoveToBoard(ValidMove move) {
        for (Direction direction : move.getValidDirections()) {
            flipLineOfPawns(move.getPosition(), direction);
        }
        setPositionValue(move.getPosition(),getCurrentPlayer());
        swapTurn();
    }
    private void flipLineOfPawns(BoardTile position, Direction direction) {
        BoardTile currentPosition = position.add(direction);
        while (getPositionValue(currentPosition) != getCurrentPlayer()){
            setPositionValue(currentPosition, getCurrentPlayer());
            currentPosition = currentPosition.add(direction);
        }
    }

    public void swapTurn(){
        this.blackToMove = !this.blackToMove;
    }

    public boolean isFull() {
        return Arrays.stream(this.board).allMatch(row -> Arrays.stream(row).noneMatch(pawn -> pawn == Pawn.EMPTY));
    }

    public boolean isGameOver() { return this.gameOver; }

    public void GameOver() {
        this.gameOver = true;
    }
    public void importBoardFrom(Board another){
        for (int i = 0; i < BOARD_SIZE; i++)
            System.arraycopy(another.board[i], 0, this.board[i], 0, BOARD_SIZE);
        this.blackToMove = another.blackToMove;
        this.gameOver = another.gameOver;
    }

    public Board copy(){
        Board newBoard = new Board();
        newBoard.importBoardFrom(this);
        return newBoard;
    }

    public boolean hasTheSamePositionOf(Board board1){
        return Arrays.deepEquals(this.board, board1.board);
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
        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++){
            result.append(" ").append(i+1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++) {
                result.append("  ").append(getPositionValue(i,j)).append((j == BOARD_SIZE - 1) ? "  ┃" : "  ╎");
            }
            result.append((i == BOARD_SIZE - 1) ? "\n" : "\n   ┣-----+-----+-----+-----+-----+-----+-----+-----┫\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        result.append("\nPlayer ").append(getCurrentPlayer()).append(" to move\n");
        result.append("White: ").append(computeScoreForPlayer(Pawn.WHITE)).append(" Black: ").append(computeScoreForPlayer(Pawn.BLACK));
        return result.toString();
    }
}


