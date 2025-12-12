package br.com.dbarreto.datastructure.node;

public interface BinaryTreeNode<T> extends TreeNode<T> {
    BinaryTreeNode<T> left();
    BinaryTreeNode<T> right();
}
