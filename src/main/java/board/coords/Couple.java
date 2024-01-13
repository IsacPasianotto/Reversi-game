package board.coords;

public interface Couple {
    int getX();
    int getY();

    default boolean equals(Couple other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

}
