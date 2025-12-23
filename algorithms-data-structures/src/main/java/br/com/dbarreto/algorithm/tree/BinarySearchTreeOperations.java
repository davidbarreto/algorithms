package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;
import br.com.dbarreto.datastructure.tree.BinarySearchTree;

import java.util.Objects;

public class BinarySearchTreeOperations {

    private BinarySearchTreeOperations() {}

    public static <T extends Comparable<T>> T min(BinarySearchTree<T> tree) {
        return min(tree.root());
    }

    public static <T extends Comparable<T>> T min(BinarySearchTreeNode<T> root) {
        if (root == null) {
            return null;
        }

        var left = root.left();
        if (left == null) {
            return root.value();
        }

        return min(left);
    }

    public static <T extends Comparable<T>> T max(BinarySearchTree<T> tree) {
        return max(tree.root());
    }

    public static <T extends Comparable<T>> T max(BinarySearchTreeNode<T> root) {
        if (root == null) {
            return null;
        }

        var right = root.right();
        if (right == null) {
            return root.value();
        }

        return max(right);
    }

    public static <T extends Comparable<T>> boolean contains(BinarySearchTree<T> binaryTree, T value) {
        return contains(binaryTree.root(), value);
    }

    public static <T extends Comparable<T>> boolean contains(BinarySearchTreeNode<T> root, T value) {
        if (root == null || value == null) return false;
        if (Objects.equals(root.value(), value)) return true;

        return value.compareTo(root.value()) < 0 ?
                contains(root.left(), value) : contains(root.right(), value);
    }
}
