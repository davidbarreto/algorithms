package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;

public class SimpleBinaryTreeNode<T> implements BinaryTreeNode<T> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public SimpleBinaryTreeNode(T value) {
        this(value, null, null);
    }

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

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
