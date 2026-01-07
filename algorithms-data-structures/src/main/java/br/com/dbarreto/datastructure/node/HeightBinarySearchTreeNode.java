package br.com.dbarreto.datastructure.node;

/**
 * Represents a Binary Search Tree node that explicitly stores or calculates its height.
 * <p>
 * This is useful for self-balancing trees like AVL trees where height information is frequently needed.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 */
public interface HeightBinarySearchTreeNode<T extends Comparable<T>> extends BinarySearchTreeNode<T> {
    /**
     * Returns the height of the subtree rooted at this node.
     *
     * @return the height
     */
    @Override
    int height();
}
