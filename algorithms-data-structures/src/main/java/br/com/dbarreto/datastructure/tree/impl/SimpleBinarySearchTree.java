package br.com.dbarreto.datastructure.tree.impl;

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
            root.setValue(succ.value());
            root.setRight(delete(root.rightMutable(), succ.value()));
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
    public T min() {
        return min(root);
    }

    private T min(SimpleMutableBinarySearchTreeNode<T> root) {
        if (root == null) {
            return null;
        }

        var left = root.leftMutable();
        if (left == null) {
            return root.value();
        }

        return min(left);
    }

    @Override
    public T max() {
        return max(root);
    }

    private T max(SimpleMutableBinarySearchTreeNode<T> root) {
        if (root == null) {
            return null;
        }

        var right = root.rightMutable();
        if (right == null) {
            return root.value();
        }

        return max(right);
    }

    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    @Override
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(BinaryTreeNode<T> root, T value) {
        if (root != null && value != null) {
            if (root.value().compareTo(value) > 0) {
                return contains(root.left(), value);
            } else if (root.value().compareTo(value) < 0) {
                return contains(root.right(), value);
            } else {
                return true;
            }
        }
        return false;
    }
}
