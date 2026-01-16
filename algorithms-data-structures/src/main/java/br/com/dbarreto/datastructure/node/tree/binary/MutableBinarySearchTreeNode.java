package br.com.dbarreto.datastructure.node.tree.binary;

/**
 * Represents a mutable node in a Binary Search Tree.
 * <p>
 * Combines the properties of a Binary Search Tree node and a Mutable Binary Tree node.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 * @param <N> the type of the mutable binary search tree node itself (recursive generic type)
 */
public interface MutableBinarySearchTreeNode<T extends Comparable<T>, N extends MutableBinarySearchTreeNode<T,N>> extends BinarySearchTreeNode<T>, MutableBinaryTreeNode<T, N> {

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
     * Returns the right child as a mutable node type {@code N}.
     *
     * @return the right child
     */
    @Override
    default N right() {
        return rightMutable();
    }
}
