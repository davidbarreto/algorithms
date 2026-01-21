package br.com.dbarreto.datastructure.node.list;

public interface SinglyLinkedListNode<E, N extends SinglyLinkedListNode<E, N>> extends MutableListNode<E> {

    /**
     * Returns the next node in the list.
     *
     * @return the next node, or {@code null} if this is the last node
     */
    N next();

    /**
     * Sets the next node in the list.
     *
     * @param next the next node
     */
    void setNext(N next);
}
