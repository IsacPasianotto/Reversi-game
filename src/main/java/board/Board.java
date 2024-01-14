package board;


import board.coords.BoardTile;
import board.coords.Direction;

public class Board {
    public static final int BOARD_SIZE = 8;
    private boolean blackToMove;
    private Pawn[][] board;

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
    public Pawn getPositionValue(BoardTile position) {
        return board[position.getX()][position.getY()];
    }
    public Pawn getPositionValue(int x, int y) {
        return board[x][y];
    }
    public void setPositionValue(BoardTile position, Pawn value) {
        board[position.getX()][position.getY()] = value;
    }


    public void flipLineOfPawns(BoardTile position, Direction direction) {
        BoardTile currentPosition = position;
        Pawn currentPawn  = blackToMove ? Pawn.BLACK : Pawn.WHITE;
        while (this.getPositionValue(currentPosition) != currentPawn){
            this.setPositionValue(currentPosition, currentPawn);
            currentPosition = currentPosition.add(direction);
        }
    }

    public void changeTurn(){
        blackToMove = !blackToMove;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("      A     B     C     D     E     F     G     H  \n   ┏─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┓\n");
        for (int i = 0; i < BOARD_SIZE; i++){
            result.append(" ").append(i+1).append(" ┃");
            for (int j = 0; j < BOARD_SIZE; j++) {
                result.append("  ").append(board[i][j]);
                if (j != BOARD_SIZE - 1)
                    result.append("  ╎");
                else
                    result.append("  ┃");
            }
            if (i != BOARD_SIZE - 1)
                result.append("\n   ┃-----+-----+-----+-----+-----+-----+-----+-----┃");
            result.append("\n");
        }
        result.append("   ┗─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┛\n");
        if (blackToMove)
            result.append("\nPlayer ").append(Pawn.BLACK).append(" to move");
        else
            result.append("\nPlayer ").append(Pawn.WHITE).append(" to move");
        return result.toString();
    }
}


