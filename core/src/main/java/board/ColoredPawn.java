package board;

/**
 * Enum to represent the colored pawns on the board. It contains the symbols to represent the possible situations on a specific
 * board tile: white pawn, black pawn or empty tile.
 */
public enum ColoredPawn {
    WHITE('◯'),
    BLACK('●'),
    EMPTY(' ');
    private final char symbol;

    ColoredPawn(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Assuming the current instance is not EMPTY, returns the opposite color.
     * @return white if the current instance is black, black if it's white
     */
    public ColoredPawn opposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    /**
     * Returns the symbol that represents the current instance.
     * @return the symbol that represents the current instance
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
