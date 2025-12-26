package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.BinaryTree;

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

    public static <T extends Comparable<T>> boolean isBinarySearchTree(BinaryTree<T> tree) {

        if (tree == null) {
            throw new NullPointerException("Tree object cannot be null");
        }

        var treeSize = tree.size();
        if (treeSize == 0 || treeSize == 1) {
            return true;
        }

        return isBinarySearchTree(tree.root());
    }

    public static <T extends Comparable<T>> boolean isBinarySearchTree(BinaryTreeNode<T> root) {
        if (root == null) {
            return true;
        }

        var value = root.value();
        var left = root.left();
        var right = root.right();

        if (left != null && left.value().compareTo(value) > 0 || right != null && right.value().compareTo(value) < 0) {
            return false;
        }

        return isBinarySearchTree(left) && isBinarySearchTree(right);
    }
}
