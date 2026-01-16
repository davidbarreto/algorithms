package br.com.dbarreto.datastructure.node.tree.binary;

/**
 * Represents a mutable node in a Red-Black Tree.
 * <p>
 * Allows modifying the node's color and parent reference, which are necessary for rebalancing operations.
 * </p>
 *
 * @param <T> the type of the value held by the node, which must be Comparable
 * @param <N> the type of the mutable colored binary search tree node itself (recursive generic type)
 */
public interface MutableColoredBinarySearchTreeNode<T extends Comparable<T>, N extends MutableColoredBinarySearchTreeNode<T, N>> extends MutableBinarySearchTreeNode<T, N>, ColoredBinarySearchTreeNode<T> {

    /**
     * Sets the left child of this node.
     *
     * @param left the new left child
     */
    void setLeft(N left);

    /**
     * Sets the color of this node.
     *
     * @param color the new color (RED or BLACK)
     */
    void setColor(Color color);

    /**
     * Returns the parent of this node.
     *
     * @return the parent node
     */
    @Override
    default ColoredBinarySearchTreeNode<T> parent() {
        return parentMutable();
    }

    /**
     * Returns the parent of this node as a mutable node type {@code N}.
     *
     * @return the parent node
     */
    N parentMutable();

    /**
     * Sets the parent of this node.
     *
     * @param parent the new parent node
     */
    void setParent(N parent);
}
