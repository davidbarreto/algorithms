package br.com.dbarreto.datastructure.node;

public enum BinaryTreeChildDirection {
    LEFT,
    RIGHT;

    public BinaryTreeChildDirection invert() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
