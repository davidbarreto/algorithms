package br.com.dbarreto.datastructure.node;

/**
 * Represents a node in a Red-Black Tree, which is a type of self-balancing binary search tree.
 * <p>
 * Each node has a color (Red or Black) and a reference to its parent, in addition to standard binary tree properties.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 */
public interface ColoredBinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    /**
     * Enumeration representing the color of a node in a Red-Black Tree.
     */
    enum Color {
        /**
         * Represents a Red node.
         */
        RED,
        /**
         * Represents a Black node.
         */
        BLACK;

        /**
         * Checks if the color is Red.
         *
         * @return {@code true} if Red, {@code false} otherwise
         */
        public boolean isRed() {
            return this == Color.RED;
        }

        /**
         * Checks if the color is Black.
         *
         * @return {@code true} if Black, {@code false} otherwise
         */
        public boolean isBlack() {
            return this == Color.BLACK;
        }
    }

    /**
     * Returns the color of this node.
     *
     * @return the {@link Color} of the node
     */
    Color color();

    /**
     * Returns the parent of this node.
     *
     * @return the parent node, or {@code null} if this is the root
     */
    ColoredBinarySearchTreeNode<T> parent();
}
