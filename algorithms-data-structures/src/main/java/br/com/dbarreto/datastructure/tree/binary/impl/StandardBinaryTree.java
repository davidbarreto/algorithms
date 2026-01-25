package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;

/**
 * Simple Binary Tree implementation.
 * <p>
 * A binary tree is a tree data structure in which each node has at most two children,
 * referred to as the left child and the right child. Unlike binary search trees,
 * there are no ordering requirements on the nodes' values.
 * <p>
 * This is a basic implementation that simply stores a root node and provides access to it.
 * It does not provide any tree manipulation operations like insertion, deletion, or traversal.
 * Those operations would need to be implemented by subclasses or external utilities.
 * <p>
 * For constructing binary trees programmatically, use {@link Builder}
 * which provides a fluent API for building tree structures.
 *
 * @param <T> the type of elements maintained by this tree
 */
public class StandardBinaryTree<T> extends AbstractBinaryTree<T> implements BinaryTree<T> {

    private final Node<T> root;

    public StandardBinaryTree() {
        this(null);
    }

    private StandardBinaryTree(Node<T> root) {
        this.root = root;
    }

    /**
     * Returns the root node of this binary tree.
     *
     * @return the root node, or null if the tree is empty
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    public static final class Builder<T> {

        private final NodeBuilder<Builder<T>, T> rootBuilder;

        public Builder(T rootValue) {
            this.rootBuilder = new NodeBuilder<>(this, rootValue);
        }

        public NodeBuilder<Builder<T>, T> root() {
            return rootBuilder;
        }

        public BinaryTree<T> build() {
            Node<T> rootNode = rootBuilder.buildNode();
            return new StandardBinaryTree<>(rootNode);
        }

        public static class NodeBuilder<P, T> {

            private final P parentBuilder;
            private final T value;
            private NodeBuilder<NodeBuilder<P, T>, T> leftBuilder;
            private NodeBuilder<NodeBuilder<P, T>, T> rightBuilder;

            NodeBuilder(P parentBuilder, T value) {
                this.parentBuilder = parentBuilder;
                this.value = value;
            }

            public NodeBuilder<NodeBuilder<P, T>, T> left(T value) {
                leftBuilder = new NodeBuilder<>(this, value);
                return leftBuilder;
            }

            public NodeBuilder<NodeBuilder<P, T>, T> right(T value) {
                rightBuilder = new NodeBuilder<>(this, value);
                return rightBuilder;
            }

            /** return to parent builder */
            public P end() {
                return parentBuilder;
            }

            /** recursively build immutable node */
            Node<T> buildNode() {
                Node<T> leftNode = leftBuilder == null ? null : leftBuilder.buildNode();
                Node<T> rightNode = rightBuilder == null ? null : rightBuilder.buildNode();
                return new Node<>(value, leftNode, rightNode);
            }
        }
    }
}
