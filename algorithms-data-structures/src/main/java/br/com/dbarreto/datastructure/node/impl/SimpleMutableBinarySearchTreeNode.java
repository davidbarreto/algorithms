package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.MutableBinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.MutableBinaryTreeNode;

public class SimpleMutableBinarySearchTreeNode<T extends Comparable<T>> implements MutableBinarySearchTreeNode<T> {
    
    private int height;
    private T value;
    private MutableBinarySearchTreeNode<T> left;
    private MutableBinarySearchTreeNode<T> right;

    public SimpleMutableBinarySearchTreeNode(T value) {
        this.value = value;
        this.height = 1;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public T value() {
        return this.value;
    }

    @Override
    public void setLeft(MutableBinaryTreeNode<T> left) {
        this.left = (MutableBinarySearchTreeNode<T>) left;
    }

    @Override
    public void setRight(MutableBinaryTreeNode<T> right) {
        this.right = (MutableBinarySearchTreeNode<T>) right;
    }

    @Override
    public MutableBinarySearchTreeNode<T> leftMutable() {
        return this.left;
    }

    @Override
    public MutableBinarySearchTreeNode<T> rightMutable() {
        return this.right;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}