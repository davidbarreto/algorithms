package br.com.dbarreto.datastructure.list;

/**
 * Represents a Double Ended Queue (Deque), which supports element insertion and removal at both ends.
 *
 * @param <T> the type of elements stored in the deque
 */
public interface Deque<T> extends Queue<T> {

    /**
     * Adds an element to the beginning of the list.
     *
     * @param value the value to add
     */
    void addFirst(T value);

    /**
     * Adds an element to the beginning of the list
     * <p>
     * Default implementation calls {@link #addFirst}
     * @see Deque#addFirst
     * @param value the value to add
     */
    @Override
    default void add(T value) {
        addFirst(value);
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element
     */
    T removeFirst();

    /**
     * Removes and returns the first element of the list.
     * <p>
     * Default implementation calls {@link #removeFirst}
     * @return the removed element
     */
    @Override
    default T remove() {
        return removeFirst();
    }

    /**
     * Adds an element to the end of the deque.
     *
     * @param value the value to add
     */
    void addLast(T value);

    /**
     * Removes and returns the last element of the deque.
     *
     * @return the removed element
     */
    T removeLast();
}
