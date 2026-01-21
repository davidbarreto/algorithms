package br.com.dbarreto.datastructure.list;

/**
 * Represents a generic Linked List data structure.
 *
 * @param <T> the type of elements stored in the list
 */
public interface Queue<T> extends Iterable<T> {

    /**
     * Adds an element to the beginning of the list.
     *
     * @param value the value to add
     */
    void add(T value);

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element
     */
    T remove();

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list contains no elements, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    int size();

    /**
     * Removes all elements from the list.
     */
    void clear();
}
