public class Board {

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        // initialize the board with starting position
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = ' ';
        board[3][3] = board[4][4] = WHITE_SYMBOL;
        board[3][4] = board[4][3] = BLACK_SYMBOL;
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("| " + board[i][j] +  " ");
            }
            System.out.println("|");
        }
    }

    public char getSquareValue(int x, int y) {
        return board[x][y];
    }

    final int BOARD_SIZE = 8;
    private final char BLACK_SYMBOL = 'B';
    private final char WHITE_SYMBOL = 'W';
    char[][] board;
}
