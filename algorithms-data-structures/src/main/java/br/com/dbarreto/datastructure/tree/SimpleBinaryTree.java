package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.node.BinaryTreeNode;

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
