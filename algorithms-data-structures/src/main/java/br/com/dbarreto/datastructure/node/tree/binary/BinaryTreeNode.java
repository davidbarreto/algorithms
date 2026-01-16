package br.com.dbarreto.datastructure.node.tree.binary;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.datastructure.node.tree.TreeNode;

/**
 * Represents a node in a binary tree.
 * <p>
 * Each node can have at most two children: a left child and a right child.
 * </p>
 *
 * @param <T> the type of the value held by the node
 */
public interface BinaryTreeNode<T> extends TreeNode<T> {
    /**
     * Returns the left child of this node.
     *
     * @return the left child, or {@code null} if none exists
     */
    BinaryTreeNode<T> left();

    /**
     * Returns the right child of this node.
     *
     * @return the right child, or {@code null} if none exists
     */
    BinaryTreeNode<T> right();

    /**
     * Returns the child node in the specified direction.
     *
     * @param direction the direction (LEFT or RIGHT)
     * @return the child node in the specified direction
     */
    default BinaryTreeNode<T> child(BinaryTreeChildDirection direction) {
        return (direction == BinaryTreeChildDirection.LEFT) ? left() : right();
    }

    /**
     * Calculates the height of the subtree rooted at this node.
     *
     * @return the height of the subtree
     */
    default int height() {
        return BinaryTreeOperations.height(this);
    }

    /**
     * Calculates the total number of nodes in the subtree rooted at this node.
     *
     * @return the size of the subtree
     */
    default int size() {
        return BinaryTreeOperations.size(this);
    }

    /**
     * Checks if the subtree rooted at this node contains the specified value.
     *
     * @param value the value to search for
     * @return {@code true} if the value is found, {@code false} otherwise
     */
    default boolean contains(T value) {
        return BinaryTreeOperations.contains(this, value);
    }

    /**
     * Checks if the subtree rooted at this node is balanced.
     * <p>
     * A binary tree is balanced if the height of the two subtrees of every node never differs by more than 1.
     * </p>
     *
     * @return {@code true} if the subtree is balanced, {@code false} otherwise
     */
    default boolean isBalanced() {
        return BinaryTreeOperations.isBalanced(this);
    }
}
