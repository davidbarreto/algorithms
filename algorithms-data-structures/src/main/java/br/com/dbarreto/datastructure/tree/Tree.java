package br.com.dbarreto.datastructure.tree;

/**
 * Represents a generic Tree data structure.
 *
 * @param <T> the type of elements stored in the tree
 */
public interface Tree<T> {
    /**
     * Returns the height of the tree.
     * <p>
     * The height is the number of edges on the longest path from the root to a leaf.
     * An empty tree has height -1, and a tree with a single node has height 0.
     * </p>
     *
     * @return the height of the tree
     */
    int height();

    /**
     * Returns the number of elements (nodes) in the tree.
     *
     * @return the size of the tree
     */
    int size();

    /**
     * Checks if the tree contains the specified value.
     *
     * @param value the value to search for
     * @return {@code true} if the value is found, {@code false} otherwise
     */
    boolean contains(T value);

    /**
     * Checks if the tree is empty.
     *
     * @return {@code true} if the tree contains no elements, {@code false} otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}
