package board.coords;

public interface Couple {
    int x();

    int y();

    @Override
    boolean equals(Object other);

    @Override
    String toString();
}
