package board;


import player.Coords;

import java.util.ArrayList;

public class Board {

    public Board() {
        board = new Pawn[BOARD_SIZE][BOARD_SIZE];
        // initialize the board with starting position
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = Pawn.EMPTY;
        board[3][3] = board[4][4] = Pawn.WHITE;
        board[3][4] = board[4][3] = Pawn.BLACK;
        boolean blackToMove = true;
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






    public Pawn getPositionValue(Coords position) {
        return board[position.getX()][position.getY()];
    }

    public Pawn getPositionValue(int x, int y) {
        return board[x][y];
    }

    boolean blackToMove;
    final int BOARD_SIZE = 8;
    Pawn[][] board;
}


