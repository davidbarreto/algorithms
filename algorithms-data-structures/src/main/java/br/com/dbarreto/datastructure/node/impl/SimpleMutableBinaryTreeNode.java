package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.MutableBinaryTreeNode;

public class SimpleMutableBinaryTreeNode<T> implements MutableBinaryTreeNode<T> {
    
    private T value;
    private MutableBinaryTreeNode<T> left;
    private MutableBinaryTreeNode<T> right;

    public SimpleMutableBinaryTreeNode() {
        this(null, null, null);
    }

    public SimpleMutableBinaryTreeNode(T value) {
        this(value, null, null);
    }

    public SimpleMutableBinaryTreeNode(T value, MutableBinaryTreeNode<T> left, MutableBinaryTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
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
    public void setLeft(MutableBinaryTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(MutableBinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public MutableBinaryTreeNode<T> leftMutable() {
        return this.left;
    }

    @Override
    public MutableBinaryTreeNode<T> rightMutable() {
        return this.right;
    }
}
