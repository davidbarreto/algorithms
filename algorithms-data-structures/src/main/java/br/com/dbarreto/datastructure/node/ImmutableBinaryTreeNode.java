package br.com.dbarreto.datastructure.node;

public class ImmutableBinaryTreeNode<T> implements BinaryTreeNode<T> {

    private final T value;
    private final BinaryTreeNode<T> left;
    private final BinaryTreeNode<T> right;

    public ImmutableBinaryTreeNode(T value) {
        this(value, null, null);
    }

    public ImmutableBinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public BinaryTreeNode<T> left() {
        return this.left;
    }

    @Override
    public BinaryTreeNode<T> right() {
        return this.right;
    }

    @Override
    public T value() {
        return value;
    }
}
