package board;


import board.coords.BoardTile;
import board.coords.Direction;

public class Board {

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

    public void printBoard() {

        System.out.println("\t  A\t  B\t  C\t  D\t  E\t  F\t  G\t  H");
        System.out.println("\t---------------------------------");
        for (int i = 0; i < BOARD_SIZE; i++){
            System.out.print((i+1) + "\t");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("| " + board[i][j] +  " ");
            }
            System.out.println("|");
        }
        System.out.println("\t---------------------------------");
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

    public boolean isBlackToMove() {
        return this.blackToMove;
    }

    public void changeTurn(){
        blackToMove = !blackToMove;
    }

    private boolean blackToMove;
    public final int BOARD_SIZE = 8;
    private Pawn[][] board;
}


