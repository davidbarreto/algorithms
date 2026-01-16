package br.com.dbarreto.datastructure.tree.binary.builder;

import br.com.dbarreto.datastructure.node.tree.binary.impl.SimpleBinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.datastructure.tree.binary.impl.SimpleBinaryTree;

public final class SimpleBinaryTreeBuilder<T> {

    private final NodeBuilder<SimpleBinaryTreeBuilder<T>, T> rootBuilder;

    public SimpleBinaryTreeBuilder(T rootValue) {
        this.rootBuilder = new NodeBuilder<>(this, rootValue);
    }

    public NodeBuilder<SimpleBinaryTreeBuilder<T>, T> root() {
        return rootBuilder;
    }

    public BinaryTree<T> build() {
        SimpleBinaryTreeNode<T> rootNode = rootBuilder.buildNode();
        return new SimpleBinaryTree<>(rootNode);
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
        SimpleBinaryTreeNode<T> buildNode() {
            SimpleBinaryTreeNode<T> leftNode = leftBuilder == null ? null : leftBuilder.buildNode();
            SimpleBinaryTreeNode<T> rightNode = rightBuilder == null ? null : rightBuilder.buildNode();
            return new SimpleBinaryTreeNode<>(value, leftNode, rightNode);
        }
    }
}
