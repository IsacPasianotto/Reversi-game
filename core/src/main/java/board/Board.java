package board;


import board.coords.BoardTile;
import board.coords.Direction;

import java.util.Arrays;
import java.util.stream.Stream;

public class Board {
    public static final int BOARD_SIZE = 8;
    private final ColoredPawn[][] board;


    public Board() {
        board = new ColoredPawn[BOARD_SIZE][BOARD_SIZE];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, ColoredPawn.EMPTY));
        Arrays.asList("d4", "e5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.WHITE));
        Arrays.asList("e4", "d5").forEach(s -> setPositionColor(new BoardTile(s), ColoredPawn.BLACK));
    }

    public void applyMoveToBoard(ValidMove move) {
        BoardTile startingPosition = move.position();
        ColoredPawn currentColor = move.currentColor();
        move.validDirections().forEach(direction -> flipLineOfPawns(startingPosition, direction, currentColor));
        setPositionColor(startingPosition, currentColor);
    }

//    private void flipLineOfPawns(BoardTile startingPosition, Direction direction, ColoredPawn currentPlayerColor) {
//        BoardTile currentPosition = startingPosition.add(direction);
//        while (getPositionColor(currentPosition) != currentPlayerColor) {
//            setPositionColor(currentPosition, currentPlayerColor);
//            currentPosition = currentPosition.add(direction);
//        }
//    }

    private void flipLineOfPawns(BoardTile startingPosition, Direction direction, ColoredPawn currentPlayerColor) {
        Stream.iterate(startingPosition
                .add(direction), position -> getPositionColor(position) != currentPlayerColor, position -> position.add(direction))
                .forEach(position -> setPositionColor(position, currentPlayerColor));
    }


    public boolean isFull() {
        return Arrays.stream(board).allMatch(row -> Arrays.stream(row).noneMatch(pawn -> pawn == ColoredPawn.EMPTY));
    }

    public void importBoardFrom(Board another) {
        for (int i = 0; i < BOARD_SIZE; i++)
            System.arraycopy(another.board[i], 0, board[i], 0, BOARD_SIZE);
    }

    public Board copy() {
        Board newBoard = new Board();
        newBoard.importBoardFrom(this);
        return newBoard;
    }

    public ColoredPawn getPositionColor(BoardTile position) {
        return board[position.x()][position.y()];
    }

    public ColoredPawn getPositionColor(int x, int y) {
        return board[x][y];
    }

    public void setPositionColor(BoardTile position, ColoredPawn color) {
        board[position.x()][position.y()] = color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Board otherBoard = (Board) other;
        return Arrays.deepEquals(board, otherBoard.board);
    }

    public int computeScoreForPlayer(ColoredPawn player) {
        return Arrays.stream(board).mapToInt(row -> (int) Arrays.stream(row).filter(pawn -> pawn == player).count()).sum();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++) {
            result.append(" ").append(i + 1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++)
                result.append("  ").append(getPositionColor(i, j)).append((j == BOARD_SIZE - 1) ? "  ┃" : "  ╎");
            result.append((i == BOARD_SIZE - 1) ? "\n" : "\n   ┣-----+-----+-----+-----+-----+-----+-----+-----┫\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        result.append("Current score: ").append(ColoredPawn.WHITE).append(" ").append(computeScoreForPlayer(ColoredPawn.WHITE)).
                append(" - ").append(computeScoreForPlayer(ColoredPawn.BLACK)).append(" ").append(ColoredPawn.BLACK);
        return result.toString();
    }
}


