package br.com.dbarreto.datastructure.node;

/**
 * Represents a mutable Binary Search Tree node that maintains its height.
 * <p>
 * Allows modifying the node's height, which is essential for self-balancing algorithms like AVL.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 * @param <N> the type of the mutable height binary search tree node itself (recursive generic type)
 */
public interface MutableHeightBinarySearchTreeNode<T extends Comparable<T>, N extends MutableHeightBinarySearchTreeNode<T,N>>
        extends MutableBinarySearchTreeNode<T, N>, HeightBinarySearchTreeNode<T> {

    /**
     * Returns the left child as a mutable node type {@code N}.
     *
     * @return the left child
     */
    @Override
    N leftMutable();

    /**
     * Returns the right child as a mutable node type {@code N}.
     *
     * @return the right child
     */
    @Override
    N rightMutable();

    /**
     * Returns the right child as a mutable node type {@code N}.
     *
     * @return the right child
     */
    @Override
    default N right() {
        return rightMutable();
    }

    /**
     * Returns the left child as a mutable node type {@code N}.
     *
     * @return the left child
     */
    @Override
    default N left() {
        return leftMutable();
    }

    /**
     * Sets the height of this node.
     *
     * @param height the new height
     */
    void setHeight(int height);
}
