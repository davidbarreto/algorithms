package br.com.dbarreto.datastructure.node.list;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <E> the type of the value held by the node
 */
public interface DoublyLinkedNode<E, N extends DoublyLinkedNode<E, N>> extends SinglyLinkedListNode<E, N> {

    /**
     * Returns the previous node in the list.
     *
     * @return the previous node, or {@code null} if this is the first node
     */
    N previous();

    /**
     * Sets the previous node in the list.
     *
     * @param previous the previous node
     */
    void setPrevious(N previous);
}
