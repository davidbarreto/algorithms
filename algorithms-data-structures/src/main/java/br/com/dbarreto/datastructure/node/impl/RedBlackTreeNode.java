package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.MutableColoredBinarySearchTreeNode;

public class RedBlackTreeNode<T extends Comparable<T>> implements MutableColoredBinarySearchTreeNode<T, RedBlackTreeNode<T>> {

    private RedBlackTreeNode<T> left;
    private RedBlackTreeNode<T> right;
    private RedBlackTreeNode<T> parent;
    private T value;
    private Color color;

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
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public void turnBlack() {
        setColor(Color.BLACK);
    }

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
}
