package br.com.dbarreto.datastructure.node;

public class SimpleBinaryTreeNode<T> implements BinaryTreeNode<T> {

    private T value;
    private SimpleBinaryTreeNode<T> left;
    private SimpleBinaryTreeNode<T> right;

    public SimpleBinaryTreeNode(T value) {
        this.value = value;
    }

    @Override
    public SimpleBinaryTreeNode<T> left() {
        return this.left;
    }

    @Override
    public SimpleBinaryTreeNode<T> right() {
        return this.right;
    }

    public void setLeft(SimpleBinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(SimpleBinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void print(StringBuilder buffer, String prefix, String childrenPrefix, char type) {
        buffer.append(prefix)
            .append(this.value())
            .append("(")
                .append(type)
            .append(")")
            .append('\n');

        if (this.left() != null && this.right() != null) {
            this.left().print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", 'L');
            this.right().print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", 'R');
        } else {

            char childType = this.left() != null ? 'L' : 'R';
            SimpleBinaryTreeNode<T> node = this.left() != null ? this.left() : this.right();
            if (node != null) {
                node.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", childType);
            }
        }
    }
}
