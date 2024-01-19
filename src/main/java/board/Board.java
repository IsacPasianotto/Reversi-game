package board;


import board.coords.BoardTile;
import board.coords.Direction;

import java.util.Arrays;

public class Board {
    public static final int BOARD_SIZE = 8;
    private boolean blackToMove;
    private final Pawn[][] board;
    private boolean gameOver;

    public Board() {
        board = new Pawn[BOARD_SIZE][BOARD_SIZE];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, Pawn.EMPTY));
        Arrays.asList("d4", "e5").forEach(s -> setPositionValue(new BoardTile(s), Pawn.WHITE));
        Arrays.asList("e4", "d5").forEach(s -> setPositionValue(new BoardTile(s), Pawn.BLACK));
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
        move.getValidDirections().forEach(direction -> flipLineOfPawns(move.getPosition(), direction));
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

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Board otherBoard = (Board) other;
        return Arrays.deepEquals(this.board, otherBoard.board) && this.blackToMove == otherBoard.blackToMove;
    }

    public int computeScoreForPlayer(Pawn player) {
        return Arrays.stream(board).mapToInt(row -> (int) Arrays.stream(row).filter(pawn -> pawn == player).count()).sum();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++){
            result.append(" ").append(i+1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++)
                result.append("  ").append(getPositionValue(i, j)).append((j == BOARD_SIZE - 1) ? "  ┃" : "  ╎");
            result.append((i == BOARD_SIZE - 1) ? "\n" : "\n   ┣-----+-----+-----+-----+-----+-----+-----+-----┫\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        result.append("\nPlayer ").append(getCurrentPlayer()).append(" to move\n");
        result.append("White: ").append(computeScoreForPlayer(Pawn.WHITE)).append(" Black: ").append(computeScoreForPlayer(Pawn.BLACK));
        return result.toString();
    }
}


