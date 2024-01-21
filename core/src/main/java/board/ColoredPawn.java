package board;

public enum ColoredPawn {
    WHITE('◯'),
    BLACK('●'),
    EMPTY(' ');

    private final char symbol;

    ColoredPawn(char symbol) {
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
