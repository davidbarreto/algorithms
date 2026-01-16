package br.com.dbarreto.algorithm.tree;

import java.util.Objects;
import java.util.function.Function;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.node.tree.binary.MutableBinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;

public class BinaryTreeOperations {

    private BinaryTreeOperations() {}

    public static <T> int height(BinaryTree<T> binaryTree) {
        return height(binaryTree.root());
    }

    public static <T> int height(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left()), height(root.right()));
    }

    public static <T> int size(BinaryTree<T> binaryTree) {
        return size(binaryTree.root());
    }

    public static <T> int size(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + size(root.left()) + size(root.right());
    }

    public static <T> boolean contains(BinaryTree<T> binaryTree, T value) {
        return contains(binaryTree.root(), value);
    }

    public static <T> boolean contains(BinaryTreeNode<T> root, T value) {
        if (root == null) return false;
        if (Objects.equals(root.value(), value)) return true;

        return contains(root.left(), value) || contains(root.right(), value);
    }

    public static <T> boolean isBalanced(BinaryTree<T> binaryTree) {
        return isBalanced(binaryTree.root());
    }

    public static <T> boolean isBalanced(BinaryTreeNode<T> root) {
        
        if (root == null) return true;
        int leftHeight = height(root.left());
        int rightHeight = height(root.right());

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left()) && isBalanced(root.right());
    }

    public static <T> boolean equals(BinaryTree<T> tree1, BinaryTree<T> tree2) {
        return equals(tree1.root(), tree2.root());
    }

    public static <T> boolean equals(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (!Objects.equals(node1.value(), node2.value())) {
            return false;
        }
        return equals(node1.left(), node2.left()) && equals(node1.right(), node2.right());
    }

    public static <T, M extends BinaryTree<T>, N extends MutableBinaryTreeNode<T, N>> M deepCopy(BinaryTree<T> oldTree, Function<N, M> treeConstructor, Function<T, N> nodeConstructor) {
        return treeConstructor.apply(deepCopy(oldTree.root(), nodeConstructor));
    }

    public static <T, N extends MutableBinaryTreeNode<T, N>> N deepCopy(BinaryTreeNode<T> oldRoot, Function<T, N> constructor) {
        if (oldRoot == null) {
            return null;
        }

        N newRoot = constructor.apply(oldRoot.value());

        newRoot.setLeft(deepCopy(oldRoot.left(), constructor));
        newRoot.setRight(deepCopy(oldRoot.right(), constructor));

        return newRoot;
    }
}
