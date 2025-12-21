package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.node.MutableBinaryTreeNode;

public interface SelfBalancingBinarySearchTree<T extends Comparable<T>, N extends MutableBinaryTreeNode<T,N>> extends BinarySearchTree<T> {
    N rotateLeft(N node);
    N rotateRight(N node);
}
