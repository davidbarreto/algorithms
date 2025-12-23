package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.node.impl.SimpleMutableBinarySearchTreeNode;
import br.com.dbarreto.datastructure.tree.BinarySearchTree;

public class SimpleBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private SimpleMutableBinarySearchTreeNode<T> root;

    public SimpleBinarySearchTree() {
    }

    public SimpleBinarySearchTree(SimpleMutableBinarySearchTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public void insert(T value) {
        this.root = insert(this.root, value);
    }

    private SimpleMutableBinarySearchTreeNode<T> insert(SimpleMutableBinarySearchTreeNode<T> root, T value) {
        if (root == null) {
            return new SimpleMutableBinarySearchTreeNode<>(value);
        }

        if (value.compareTo(root.value()) < 0) {
            root.setLeft(insert(root.leftMutable(), value));
        } else {
            root.setRight(insert(root.rightMutable(), value));
        }

        return root;
    }

    @Override
    public void delete(T value) {
        this.root = delete(root, value);
    }

    private SimpleMutableBinarySearchTreeNode<T> delete(SimpleMutableBinarySearchTreeNode<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (root.value().compareTo(value) > 0) {
            root.setLeft(delete(root.leftMutable(), value));
        } else if (root.value().compareTo(value) < 0) {
            root.setRight(delete(root.rightMutable(), value));
        } else {
            if (root.left() == null) {
                return root.rightMutable();
            }
            if (root.right() == null) {
                return root.leftMutable();
            }
            var succ = successor(root);
            var valSucc = succ != null ? succ.value() : null;
            root.setValue(valSucc);
            root.setRight(delete(root.rightMutable(), valSucc));
        }
        return root;
    }

    private BinaryTreeNode<T> successor(BinaryTreeNode<T> current) {
        current = current.right();
        while (current != null && current.left() != null) {
            current = current.left();
        }
        return current;
    }

    @Override
    public BinarySearchTreeNode<T> root() {
        return root;
    }
}
