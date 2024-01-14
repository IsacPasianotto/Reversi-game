package board;

public enum Pawn {
    WHITE('◯'),
    BLACK('●'),
    EMPTY(' ');

    private final char symbol;

    Pawn(char symbol) {
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

}
