package br.com.dbarreto.datastructure.node.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.MutableHeightBinarySearchTreeNode;

/**
 * A simple implementation of {@link MutableHeightBinarySearchTreeNode}.
 * <p>
 * Maintains the height of the node, initialized to 1.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 */
public class SimpleMutableHeightBinarySearchTreeNode<T extends Comparable<T>> implements MutableHeightBinarySearchTreeNode<T, SimpleMutableHeightBinarySearchTreeNode<T>> {
    
    private T value;
    private SimpleMutableHeightBinarySearchTreeNode<T> left;
    private SimpleMutableHeightBinarySearchTreeNode<T> right;
    private int height;

    /**
     * Creates a new mutable height binary search tree node with the specified value.
     * The initial height is 1.
     *
     * @param value the value to be held by the node
     */
    public SimpleMutableHeightBinarySearchTreeNode(T value) {
        this.value = value;
        this.height = 1;
    }

    @Override
    public T value() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setLeft(SimpleMutableHeightBinarySearchTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(SimpleMutableHeightBinarySearchTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> leftMutable() {
        return this.left;
    }

    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> rightMutable() {
        return this.right;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
