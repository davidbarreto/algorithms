package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.BinaryTree;

public class SimpleBinaryTree<T> implements BinaryTree<T> {

    private final BinaryTreeNode<T> root;

    public SimpleBinaryTree() {
        this(null);
    }

    public SimpleBinaryTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }
}
