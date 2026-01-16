package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.datastructure.tree.binary.builder.SimpleBinaryTreeBuilder;

/**
 * Simple Binary Tree implementation.
 *
 * A binary tree is a tree data structure in which each node has at most two children,
 * referred to as the left child and the right child. Unlike binary search trees,
 * there are no ordering requirements on the nodes' values.
 *
 * This is a basic implementation that simply stores a root node and provides access to it.
 * It does not provide any tree manipulation operations like insertion, deletion, or traversal.
 * Those operations would need to be implemented by subclasses or external utilities.
 *
 * For constructing binary trees programmatically, use {@link SimpleBinaryTreeBuilder}
 * which provides a fluent API for building tree structures.
 *
 * @param <T> the type of elements maintained by this tree
 */
public class SimpleBinaryTree<T> implements BinaryTree<T> {

    private final BinaryTreeNode<T> root;

    /**
     * Constructs an empty binary tree with no root node.
     */
    public SimpleBinaryTree() {
        this(null);
    }

    /**
     * Constructs a binary tree with the specified root node.
     *
     * @param root the root node of the tree, or null for an empty tree
     */
    public SimpleBinaryTree(BinaryTreeNode<T> root) {
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
}
