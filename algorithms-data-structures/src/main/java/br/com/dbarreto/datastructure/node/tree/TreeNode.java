package br.com.dbarreto.datastructure.node.tree;

/**
 * Represents a node in a tree data structure.
 *
 * @param <T> the type of the value held by the node
 */
public interface TreeNode<T> {
    /**
     * Returns the value stored in this tree node.
     *
     * @return the value
     */
    T value();
}
