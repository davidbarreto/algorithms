package br.com.dbarreto.datastructure.node;

/**
 * Enumeration representing the direction of a child in a binary tree (Left or Right).
 */
public enum BinaryTreeChildDirection {
    /**
     * Represents the left child.
     */
    LEFT,
    /**
     * Represents the right child.
     */
    RIGHT;

    /**
     * Returns the opposite direction.
     *
     * @return {@link #RIGHT} if this is {@link #LEFT}, and vice-versa
     */
    public BinaryTreeChildDirection invert() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
