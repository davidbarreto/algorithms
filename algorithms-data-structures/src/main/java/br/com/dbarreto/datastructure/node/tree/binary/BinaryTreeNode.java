package br.com.dbarreto.datastructure.node.tree.binary;

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
}
