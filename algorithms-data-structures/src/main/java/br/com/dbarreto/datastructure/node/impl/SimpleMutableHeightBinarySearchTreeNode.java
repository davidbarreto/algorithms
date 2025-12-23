package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.node.MutableHeightBinarySearchTreeNode;

public class SimpleMutableHeightBinarySearchTreeNode<T extends Comparable<T>> implements MutableHeightBinarySearchTreeNode<T, SimpleMutableHeightBinarySearchTreeNode<T>> {
    
    private T value;
    private SimpleMutableHeightBinarySearchTreeNode<T> left;
    private SimpleMutableHeightBinarySearchTreeNode<T> right;
    private int height;

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