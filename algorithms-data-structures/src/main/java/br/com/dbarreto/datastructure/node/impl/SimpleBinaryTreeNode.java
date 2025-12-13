package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;

public class SimpleBinaryTreeNode<T> implements BinaryTreeNode<T> {

    private T value;
    private SimpleBinaryTreeNode<T> left;
    private SimpleBinaryTreeNode<T> right;

    public SimpleBinaryTreeNode(T value) {
        this.value = value;
    }

    @Override
    public SimpleBinaryTreeNode<T> left() {
        return this.left;
    }

    @Override
    public SimpleBinaryTreeNode<T> right() {
        return this.right;
    }

    public void setLeft(SimpleBinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(SimpleBinaryTreeNode<T> right) {
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
