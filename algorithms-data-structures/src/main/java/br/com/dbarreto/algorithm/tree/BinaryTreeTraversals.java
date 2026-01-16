package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class BinaryTreeTraversals {

    private BinaryTreeTraversals() {}

    public static <T> void traverseInOrder(BinaryTree<T> binaryTree, Consumer<T> visitor) {
        traverseInOrder(binaryTree.root(), visitor);
    }

    public static <T> void traverseInOrder(BinaryTreeNode<T> node, Consumer<T> visitor) {
        if (node == null) {
            return;
        }

        if (node.left() != null) {
            traverseInOrder(node.left(), visitor);
        }
        
        visitor.accept(node.value());
        
        if (node.right() != null) {
            traverseInOrder(node.right(), visitor);
        }
    }

    public static <T> void traversePreOrder(BinaryTree<T> binaryTree, Consumer<T> visitor) {
        traversePreOrder(binaryTree.root(), visitor);
    }

    public static <T> void traversePreOrder(BinaryTreeNode<T> node, Consumer<T> visitor) {
        if (node == null) {
            return;
        }

        visitor.accept(node.value());
        if (node.left() != null) {
            traversePreOrder(node.left(), visitor);
        }

        if (node.right() != null) {
            traversePreOrder(node.right(), visitor);
        }
    }

    public static <T> void traversePostOrder(BinaryTree<T> binaryTree, Consumer<T> visitor) {
        traversePostOrder(binaryTree.root(), visitor);
    }

    public static <T> void traversePostOrder(BinaryTreeNode<T> node, Consumer<T> visitor) {
        if (node == null) {
            return;
        }

        if (node.left() != null) {
            traversePostOrder(node.left(), visitor);
        }

        if (node.right() != null) {
            traversePostOrder(node.right(), visitor);
        }

        visitor.accept(node.value());
    }

    public static <T> void traverseLevelOrder(BinaryTree<T> binaryTree, Consumer<T> visitor) {

        if (binaryTree.root() == null) {
            return;
        }
        
        Deque<BinaryTreeNode<T>> queue = new ArrayDeque<>();
        queue.add(binaryTree.root());

        while (!queue.isEmpty()) {

            var node = queue.poll();
            visitor.accept(node.value());
            
            if (node.left() != null) {
                queue.add(node.left());
            }

            if (node.right() != null) {
                queue.add(node.right());
            }
        }
    }
}
