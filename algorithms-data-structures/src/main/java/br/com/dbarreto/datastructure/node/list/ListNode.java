package br.com.dbarreto.datastructure.node.list;

/**
 * Represents a node in a list data structure.
 *
 * @param <T> the type of the value held by the node
 */
public interface ListNode<E> {

    /**
     * Returns the value stored in this list node.
     *
     * @return the value
     */
    E value();
}
