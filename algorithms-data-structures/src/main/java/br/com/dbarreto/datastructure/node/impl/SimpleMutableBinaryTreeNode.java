package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.MutableBinaryTreeNode;

public class SimpleMutableBinaryTreeNode<T> implements MutableBinaryTreeNode<T, SimpleMutableBinaryTreeNode<T>> {
    
    private T value;
    private SimpleMutableBinaryTreeNode<T> left;
    private SimpleMutableBinaryTreeNode<T> right;

    public SimpleMutableBinaryTreeNode(T value) {
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
    public void setLeft(SimpleMutableBinaryTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(SimpleMutableBinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public SimpleMutableBinaryTreeNode<T> leftMutable() {
        return this.left;
    }

    @Override
    public SimpleMutableBinaryTreeNode<T> rightMutable() {
        return this.right;
    }
}
