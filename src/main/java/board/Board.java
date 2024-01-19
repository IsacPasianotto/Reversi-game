package board;


import board.coords.BoardTile;
import board.coords.Direction;

import java.util.Arrays;

public class Board {
    public static final int BOARD_SIZE = 8;
    private boolean blackToMove;
    private final ColoredPawn[][] board;
    private boolean gameOver;

    public Board() {
        board = new ColoredPawn[BOARD_SIZE][BOARD_SIZE];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, ColoredPawn.EMPTY));
        Arrays.asList("d4", "e5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        Arrays.asList("e4", "d5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
        this.blackToMove = true;
    }

    public boolean isBlackToMove() {
        return this.blackToMove;
    }
    public ColoredPawn getCurrentPlayerColor() {
        return blackToMove ? ColoredPawn.BLACK : ColoredPawn.WHITE;
    }
    public ColoredPawn getCurrentOpponentColor() { return blackToMove ? ColoredPawn.WHITE : ColoredPawn.BLACK; }

    public ColoredPawn getPositionColor(BoardTile position) {
        return board[position.getX()][position.getY()];
    }
    public ColoredPawn getPositionColor(int x, int y) {
        return board[x][y];
    }
    public void setPositionColor(BoardTile position, ColoredPawn color) {
        board[position.getX()][position.getY()] = color;
    }

    public void applyMoveToBoard(ValidMove move) {
        move.getValidDirections().forEach(direction -> flipLineOfPawns(move.getPosition(), direction));
        setPositionColor(move.getPosition(), getCurrentPlayerColor());
        swapTurn();
    }
    private void flipLineOfPawns(BoardTile position, Direction direction) {
        BoardTile currentPosition = position.add(direction);
        while (getPositionColor(currentPosition) != getCurrentPlayerColor()){
            setPositionColor(currentPosition, getCurrentPlayerColor());
            currentPosition = currentPosition.add(direction);
        }
    }

    public void swapTurn(){
        this.blackToMove = !this.blackToMove;
    }

    public boolean isFull() {
        return Arrays.stream(this.board).allMatch(row -> Arrays.stream(row).noneMatch(pawn -> pawn == ColoredPawn.EMPTY));
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

    public int computeScoreForPlayer(ColoredPawn player) {
        return Arrays.stream(board).mapToInt(row -> (int) Arrays.stream(row).filter(pawn -> pawn == player).count()).sum();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++){
            result.append(" ").append(i+1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++)
                result.append("  ").append(getPositionColor(i, j)).append((j == BOARD_SIZE - 1) ? "  ┃" : "  ╎");
            result.append((i == BOARD_SIZE - 1) ? "\n" : "\n   ┣-----+-----+-----+-----+-----+-----+-----+-----┫\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        result.append("\nPlayer ").append(getCurrentPlayerColor()).append(" to move\n");
        result.append("White: ").append(computeScoreForPlayer(ColoredPawn.WHITE)).append(" Black: ").append(computeScoreForPlayer(ColoredPawn.BLACK));
        return result.toString();
    }
}


