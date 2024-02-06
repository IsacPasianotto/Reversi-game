package board.coords;

/**
 * A simple interface to represent objects whose characteristic is to be defined by a couple of values.
 */
public interface Couple {
    /**
     * Checks if the current couple is equal to a given one.
     * @param other an object to compare to
     * @return true if the two couples are equal, false otherwise
     */
    @Override
    boolean equals(Object other);
    /**
     * Returns a string representation of the current couple.
     * @return a string representation of the current couple
     */
    @Override
    String toString();
}
