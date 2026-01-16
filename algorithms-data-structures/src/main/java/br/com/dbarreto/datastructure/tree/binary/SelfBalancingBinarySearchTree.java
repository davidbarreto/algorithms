package br.com.dbarreto.datastructure.tree.binary;

import br.com.dbarreto.datastructure.node.tree.binary.MutableBinaryTreeNode;

public interface SelfBalancingBinarySearchTree<T extends Comparable<T>, N extends MutableBinaryTreeNode<T,N>> extends BinarySearchTree<T> {
    N rotateLeft(N node);
    N rotateRight(N node);
}
