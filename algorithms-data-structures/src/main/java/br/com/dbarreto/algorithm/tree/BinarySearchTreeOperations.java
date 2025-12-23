package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;
import br.com.dbarreto.datastructure.tree.BinarySearchTree;

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
}
