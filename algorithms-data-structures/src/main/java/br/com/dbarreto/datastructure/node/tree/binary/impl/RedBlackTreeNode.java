package br.com.dbarreto.datastructure.node.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.MutableColoredBinarySearchTreeNode;

/**
 * Implementation of a node for a Red-Black Tree.
 * <p>
 * Maintains color, parent, left, and right references.
 * Newly created nodes are colored RED by default.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 */
public class RedBlackTreeNode<T extends Comparable<T>> implements MutableColoredBinarySearchTreeNode<T, RedBlackTreeNode<T>> {

    private RedBlackTreeNode<T> left;
    private RedBlackTreeNode<T> right;
    private RedBlackTreeNode<T> parent;
    private T value;
    private Color color;

    /**
     * Creates a new Red-Black Tree node with the specified value.
     * The initial color is RED.
     *
     * @param value the value to be held by the node
     */
    public RedBlackTreeNode(T value) {
        this.value = value;
        color = Color.RED;
    }

    @Override
    public Color color() {
        return this.color;
    }

    @Override
    public void setRight(RedBlackTreeNode<T> right) {
        this.right = right;
        updateParent(right);
    }

    @Override
    public RedBlackTreeNode<T> leftMutable() {
        return this.left;
    }

    @Override
    public RedBlackTreeNode<T> rightMutable() {
        return this.right;
    }

    @Override
    public void setLeft(RedBlackTreeNode<T> left) {
        this.left = left;
        updateParent(left);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the node's color to BLACK.
     */
    public void turnBlack() {
        setColor(Color.BLACK);
    }

    /**
     * Sets the node's color to RED.
     */
    public void turnRed() {
        setColor(Color.RED);
    }

    @Override
    public RedBlackTreeNode<T> parentMutable() {
        return this.parent;
    }

    @Override
    public void setParent(RedBlackTreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T value() {
        return this.value;
    }

    private void updateParent(RedBlackTreeNode<T> node) {
        if (node != null) {
            node.setParent(this);
        }
    }
}
