package br.com.dbarreto.datastructure.node;

/**
 * Represents a tree node whose value can be modified.
 *
 * @param <T> the type of the value held by the node
 */
public interface MutableTreeNode<T> extends TreeNode<T> {
    /**
     * Sets the value of this node.
     *
     * @param value the new value
     */
    void setValue(T value);
}
