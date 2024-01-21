package board.coords;

public interface Couple {
    int getX();

    int getY();

    @Override
    boolean equals(Object other);

    @Override
    String toString();
}
