package br.com.dbarreto.datastructure.node.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;

/**
 * A simple implementation of {@link BinaryTreeNode}.
 * <p>
 * Stores a value and references to left and right children.
 * </p>
 *
 * @param <T> the type of the value held by the node
 */
public class SimpleBinaryTreeNode<T> implements BinaryTreeNode<T> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    /**
     * Creates a new binary tree node with the specified value and no children.
     *
     * @param value the value to be held by the node
     */
    public SimpleBinaryTreeNode(T value) {
        this(value, null, null);
    }

    /**
     * Creates a new binary tree node with the specified value and children.
     *
     * @param value the value to be held by the node
     * @param left  the left child
     * @param right the right child
     */
    public SimpleBinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public BinaryTreeNode<T> left() {
        return this.left;
    }

    @Override
    public BinaryTreeNode<T> right() {
        return this.right;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the new left child
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the new right child
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public T value() {
        return value;
    }

    /**
     * Sets the value of this node.
     *
     * @param value the new value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
