package br.com.dbarreto.datastructure.node.list;

/**
 * Represents a list node whose value can be modified.
 *
 * @param <T> the type of the value held by the node
 */
public interface MutableListNode<E> extends ListNode<E> {

    /**
     * Sets the value of this node.
     *
     * @param value the new value
     */
    void setValue(E value);
}
