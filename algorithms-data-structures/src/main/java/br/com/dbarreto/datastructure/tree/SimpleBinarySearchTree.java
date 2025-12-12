package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.node.SimpleBinaryTreeNode;

public class SimpleBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private SimpleBinaryTreeNode<T> root;

    @Override
    public void insert(T value) {
        this.root = insert(root, value);
    }

    private SimpleBinaryTreeNode<T> insert(SimpleBinaryTreeNode<T> root, T value) {
        if (root == null) {
            return new SimpleBinaryTreeNode<>(value);
        }

        if (value.compareTo(root.value()) < 0) {
            root.setLeft(insert(root.left(), value));
        } else {
            root.setRight(insert(root.right(), value));
        }

        return root;
    }

    @Override
    public void delete(T value) {
        this.root = delete(root, value);
    }

    private SimpleBinaryTreeNode<T> delete(SimpleBinaryTreeNode<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (root.value().compareTo(value) > 0) {
            root.setLeft(delete(root.left(), value));
        } else if (root.value().compareTo(value) < 0) {
            root.setRight(delete(root.right(), value));
        } else {
            if (root.left() == null) {
                return root.right();
            }
            if (root.right() == null) {
                return root.left();
            }
            SimpleBinaryTreeNode<T> succ = successor(root);
            root.setValue(succ.value());
            root.setRight(delete(root.right(), succ.value()));
        }
        return root;
    }

    private SimpleBinaryTreeNode<T> successor(SimpleBinaryTreeNode<T> current) {
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

    private T min(SimpleBinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }

        if (root.left() == null) {
            return root.value();
        }

        return min(root.left());
    }

    @Override
    public T max() {
        return max(root);
    }

    private T max(SimpleBinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }

        if (root.right() == null) {
            return root.value();
        }

        return max(root.right());
    }

    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    @Override
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(SimpleBinaryTreeNode<T> root, T value) {

        if (root != null && value != null) {
            if (root.value().compareTo(value) > 0) {
                return contains(root.left(), value);
            } else if (root.value().compareTo(value) < 0){
                return contains(root.right(), value);
            } else {
                return true;
            }
        }
        return false;
    }

    public String print() {
        StringBuilder buffer = new StringBuilder(50);
        root.print(buffer, "", "", 'r');
        return buffer.toString();
    }
}
