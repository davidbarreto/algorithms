package br.com.dbarreto.datastructure.node;

/**
 * Represents a node in a Binary Search Tree (BST).
 * <p>
 * In a BST, for every node, all elements in the left subtree are smaller,
 * and all elements in the right subtree are larger than the node's value.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 */
public interface BinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    /**
     * Returns the left child of this node as a {@code BinarySearchTreeNode}.
     *
     * @return the left child, or {@code null} if none exists
     */
    BinarySearchTreeNode<T> left();

    /**
     * Returns the right child of this node as a {@code BinarySearchTreeNode}.
     *
     * @return the right child, or {@code null} if none exists
     */
    BinarySearchTreeNode<T> right();
}
