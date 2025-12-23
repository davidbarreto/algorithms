package br.com.dbarreto.datastructure.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;

/**
 * Iterator implementations for tree traversals.
 *
 * Provides lazy iterators for different tree traversal orders:
 * - In-order: Left, Root, Right
 * - Pre-order: Root, Left, Right
 * - Post-order: Left, Right, Root
 * - Level-order: Breadth-first traversal
 */
public class TreeIterators {

    /**
     * In-order iterator: visits nodes in ascending order for BSTs.
     * Uses a stack to simulate recursion.
     */
    private static class InOrderIterator<T> implements Iterator<T> {
        private final Stack<BinaryTreeNode<T>> stack = new Stack<>();
        private BinaryTreeNode<T> current;

        public InOrderIterator(BinaryTreeNode<T> root) {
            current = root;
        }

        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left();
            }

            // Visit the top node
            BinaryTreeNode<T> node = stack.pop();
            T result = node.value();

            // Move to right subtree
            current = node.right();

            return result;
        }
    }

    /**
     * Pre-order iterator: visits root before children.
     * Uses a stack for iterative traversal.
     */
    private static class PreOrderIterator<T> implements Iterator<T> {
        private final Stack<BinaryTreeNode<T>> stack = new Stack<>();

        public PreOrderIterator(BinaryTreeNode<T> root) {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BinaryTreeNode<T> node = stack.pop();
            T result = node.value();

            // Push right first, then left (so left is processed first)
            if (node.right() != null) {
                stack.push(node.right());
            }
            if (node.left() != null) {
                stack.push(node.left());
            }

            return result;
        }
    }

    /**
     * Post-order iterator: visits children before root.
     * Uses two stacks for iterative traversal.
     */
    private static class PostOrderIterator<T> implements Iterator<T> {
        private final Stack<BinaryTreeNode<T>> stack1 = new Stack<>();
        private final Stack<BinaryTreeNode<T>> stack2 = new Stack<>();

        public PostOrderIterator(BinaryTreeNode<T> root) {
            if (root != null) {
                stack1.push(root);
                fillStack2();
            }
        }

        private void fillStack2() {
            while (!stack1.isEmpty()) {
                BinaryTreeNode<T> node = stack1.pop();
                stack2.push(node);

                if (node.left() != null) {
                    stack1.push(node.left());
                }
                if (node.right() != null) {
                    stack1.push(node.right());
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack2.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return stack2.pop().value();
        }
    }

    /**
     * Level-order iterator: breadth-first traversal using a queue.
     */
    private static class LevelOrderIterator<T> implements Iterator<T> {
        private final Queue<BinaryTreeNode<T>> queue = new LinkedList<>();

        public LevelOrderIterator(BinaryTreeNode<T> root) {
            if (root != null) {
                queue.offer(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BinaryTreeNode<T> node = queue.poll();
            T result = node.value();

            if (node.left() != null) {
                queue.offer(node.left());
            }
            if (node.right() != null) {
                queue.offer(node.right());
            }

            return result;
        }
    }

    /**
     * Factory methods for creating iterators from trees.
     */
    public static <T> Iterator<T> inOrder(BinaryTree<T> tree) {
        return new InOrderIterator<T>(tree.root());
    }

    public static <T> Iterator<T> preOrder(BinaryTree<T> tree) {
        return new PreOrderIterator<T>(tree.root());
    }

    public static <T> Iterator<T> postOrder(BinaryTree<T> tree) {
        return new PostOrderIterator<T>(tree.root());
    }

    public static <T> Iterator<T> levelOrder(BinaryTree<T> tree) {
        return new LevelOrderIterator<T>(tree.root());
    }
}