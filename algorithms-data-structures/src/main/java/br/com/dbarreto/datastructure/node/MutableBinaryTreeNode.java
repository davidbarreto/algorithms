package br.com.dbarreto.datastructure.node;

public interface MutableBinaryTreeNode<T, N extends MutableBinaryTreeNode<T, N>> extends BinaryTreeNode<T>, MutableTreeNode<T> {
    
    void setLeft(N left);
    void setRight(N right);

    @Override
    default BinaryTreeNode<T> left() {
        return leftMutable();
    }

    @Override
    default BinaryTreeNode<T> right() {
        return rightMutable();
    }

    N leftMutable();
    N rightMutable();
}
