package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.datastructure.node.impl.SimpleMutableHeightBinarySearchTreeNode;
import br.com.dbarreto.datastructure.tree.SelfBalancingBinarySearchTree;

public class AvlTree<T extends Comparable<T>> extends SimpleBinarySearchTree<T> implements SelfBalancingBinarySearchTree<T, SimpleMutableHeightBinarySearchTreeNode<T>> {

    private SimpleMutableHeightBinarySearchTreeNode<T> root;

    public AvlTree() {
        this(null);
    }

    public AvlTree(SimpleMutableHeightBinarySearchTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> root() {
        return this.root;
    }

    @Override
    public int height() {
        return height(this.root);
    }

    private int height(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        return node == null ? 0 : node.height();
    }

    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> rotateLeft(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        var right = node.rightMutable();
        var temp = right.leftMutable();

        right.setLeft(node);
        node.setRight(temp);

        node.setHeight(Math.max(height(node.leftMutable()), height(node.rightMutable())) + 1);
        right.setHeight(Math.max(height(right.leftMutable()), height(right.rightMutable())) + 1);
        
        return right;
    }

    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> rotateRight(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        var left = node.leftMutable();
        var temp = left.rightMutable();

        left.setRight(node);
        node.setLeft(temp);

        node.setHeight(Math.max(height(node.leftMutable()), height(node.rightMutable())) + 1);
        left.setHeight(Math.max(height(left.leftMutable()), height(left.rightMutable())) + 1);

        return left;
    }

    @Override
    public void insert(T value) {
        this.root = insert(this.root, value);
    }

    private SimpleMutableHeightBinarySearchTreeNode<T> insert(SimpleMutableHeightBinarySearchTreeNode<T> root, T value) {
        if (root == null) {
            return new SimpleMutableHeightBinarySearchTreeNode<>(value);
        }

        if (value.compareTo(root.value()) < 0) {
            root.setLeft(insert(root.leftMutable(), value));
        } else {
            root.setRight(insert(root.rightMutable(), value));
        }

        root.setHeight(1 + Math.max(height(root.leftMutable()), height(root.rightMutable())));
        int balance = getBalance(root);

        // Left-Left case
        if (balance > 1 && value.compareTo(root.left().value()) < 0) {
            return rotateRight(root);
        }

        // Right-Right case
        if (balance < -1 && value.compareTo(root.right().value()) > 0) {
            return rotateLeft(root);
        }

        // Left-Right case
        if (balance > 1 && value.compareTo(root.left().value()) > 0) {
            root.setLeft(rotateLeft(root.leftMutable()));
            return rotateRight(root);
        }

        // Right-Left case
        if (balance < -1 && value.compareTo(root.right().value()) < 0) {
            root.setRight(rotateRight(root.rightMutable()));
            return rotateLeft(root);
        }

        return root;
    }

    private int getBalance(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return BinaryTreeOperations.height(node.left()) - BinaryTreeOperations.height(node.right());
    }
}
