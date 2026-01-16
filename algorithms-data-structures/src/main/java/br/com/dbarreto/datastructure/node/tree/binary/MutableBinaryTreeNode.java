package br.com.dbarreto.datastructure.node.tree.binary;

import br.com.dbarreto.datastructure.node.tree.MutableTreeNode;

/**
 * Represents a binary tree node that can be modified.
 * <p>
 * Allows setting the left and right children, as well as the node's value.
 * </p>
 *
 * @param <T> the type of the value held by the node
 * @param <N> the type of the mutable binary tree node itself (recursive generic type)
 */
public interface MutableBinaryTreeNode<T, N extends MutableBinaryTreeNode<T, N>> extends BinaryTreeNode<T>, MutableTreeNode<T> {

    /**
     * Sets the left child of this node.
     *
     * @param left the new left child
     */
    void setLeft(N left);

    /**
     * Sets the right child of this node.
     *
     * @param right the new right child
     */
    void setRight(N right);

    /**
     * Sets the child node in the specified direction.
     *
     * @param childNode the new child node
     * @param direction the direction (LEFT or RIGHT)
     */
    default void setChild(N childNode, BinaryTreeChildDirection direction) {
        if (direction == BinaryTreeChildDirection.LEFT) {
            setLeft(childNode);
        } else {
            setRight(childNode);
        }
    }

    /**
     * Returns the left child as a {@code BinaryTreeNode}.
     *
     * @return the left child
     */
    @Override
    default BinaryTreeNode<T> left() {
        return leftMutable();
    }

    /**
     * Returns the right child as a {@code BinaryTreeNode}.
     *
     * @return the right child
     */
    @Override
    default BinaryTreeNode<T> right() {
        return rightMutable();
    }

    /**
     * Returns the left child as a mutable node type {@code N}.
     *
     * @return the left child
     */
    N leftMutable();

    /**
     * Returns the right child as a mutable node type {@code N}.
     *
     * @return the right child
     */
    N rightMutable();

    /**
     * Returns the child node in the specified direction as a mutable node type {@code N}.
     *
     * @param direction the direction (LEFT or RIGHT)
     * @return the child node
     */
    default N childMutable(BinaryTreeChildDirection direction) {
        return (direction == BinaryTreeChildDirection.LEFT) ? leftMutable() : rightMutable();
    }
}
