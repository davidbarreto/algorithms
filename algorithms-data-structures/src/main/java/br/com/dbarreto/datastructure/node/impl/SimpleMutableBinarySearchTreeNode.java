package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.MutableBinarySearchTreeNode;

/**
 * A simple implementation of {@link MutableBinarySearchTreeNode}.
 * <p>
 * Represents a node in a Binary Search Tree that can be modified.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 */
public class SimpleMutableBinarySearchTreeNode<T extends Comparable<T>> implements MutableBinarySearchTreeNode<T, SimpleMutableBinarySearchTreeNode<T>> {

    private T value;
    private SimpleMutableBinarySearchTreeNode<T> left;
    private SimpleMutableBinarySearchTreeNode<T> right;

    /**
     * Creates a new mutable binary search tree node with the specified value.
     *
     * @param value the value to be held by the node
     */
    public SimpleMutableBinarySearchTreeNode(T value) {
        this.value = value;
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
    public void setLeft(SimpleMutableBinarySearchTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(SimpleMutableBinarySearchTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public SimpleMutableBinarySearchTreeNode<T> leftMutable() {
        return this.left;
    }

    @Override
    public SimpleMutableBinarySearchTreeNode<T> rightMutable() {
        return this.right;
    }
}
