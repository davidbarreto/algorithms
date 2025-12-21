package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.impl.RedBlackTreeNode;
import br.com.dbarreto.datastructure.tree.SelfBalancingBinarySearchTree;

public class RedBlackTree<T extends Comparable<T>> extends SimpleBinarySearchTree<T> implements SelfBalancingBinarySearchTree<T, RedBlackTreeNode<T>> {

    private RedBlackTreeNode<T> root;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(RedBlackTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public RedBlackTreeNode<T> rotateLeft(RedBlackTreeNode<T> node) {

        var right = node.rightMutable();
        var temp = right.leftMutable();
        var parent = node.parentMutable();

        node.setRight(temp);
        if (temp != null) {
            temp.setParent(node);
        }

        right.setParent(parent);

        if (parent == null) {
            this.root = right;
        } else if (node == parent.leftMutable()) {
            parent.setLeft(right);
        } else {
            parent.setRight(right);
        }

        right.setLeft(node);
        node.setParent(right);

        return this.root;
    }

    @Override
    public RedBlackTreeNode<T> rotateRight(RedBlackTreeNode<T> node) {

        var left = node.leftMutable();
        var temp = left.rightMutable();
        var parent = node.parentMutable();

        if (temp != null) {
            temp.setParent(node);
        }
        left.setParent(parent);

        if (parent == null) {
            this.root = left;
        } else if (node == parent.rightMutable()) {
            parent.setRight(left);
        } else {
            parent.setLeft(left);
        }

        left.setRight(node);
        node.setParent(left);

        return this.root;
    }


}
