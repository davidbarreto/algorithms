package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.MutableBinarySearchTreeNode;

public class SimpleMutableBinarySearchTreeNode<T extends Comparable<T>> implements MutableBinarySearchTreeNode<T, SimpleMutableBinarySearchTreeNode<T>> {

    private T value;
    private SimpleMutableBinarySearchTreeNode<T> left;
    private SimpleMutableBinarySearchTreeNode<T> right;

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
