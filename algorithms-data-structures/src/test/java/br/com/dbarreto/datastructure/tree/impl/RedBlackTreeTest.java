package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.tree.binary.impl.RedBlackTreeNode;
import br.com.dbarreto.datastructure.tree.binary.impl.RedBlackTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link RedBlackTree}.
 */
class RedBlackTreeTest {

    /**
     * Tests that inserting elements maintains the Red-Black Tree properties.
     */
    @Test
    @DisplayName("Should maintain Red-Black properties after insert")
    void shouldMaintainRedBlackPropertiesAfterInsert() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(5);

        assertTrue(isValidRedBlackTree(tree));
    }

    /**
     * Tests the insertion and search functionality.
     */
    @Test
    @DisplayName("Should insert and search correctly")
    void shouldInsertAndSearch() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(9));
        assertFalse(tree.contains(10));
    }

    /**
     * Tests the {@code min} and {@code max} methods.
     */
    @Test
    @DisplayName("Should return min and max values")
    void shouldReturnMinAndMaxValues() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        assertEquals(1, tree.min());
        assertEquals(9, tree.max());
    }

    /**
     * Tests inserting a large number of elements.
     */
    @Test
    @DisplayName("Should handle large number of inserts")
    void shouldHandleLargeInserts() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        assertEquals(9, tree.max());
        assertTrue(tree.contains(5));
        assertTrue(isValidRedBlackTree(tree));
    }

    /**
     * Tests the handling of duplicate elements.
     */
    @Test
    @DisplayName("Should handle duplicate elements")
    void shouldHandleDuplicateElements() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(10); // Duplicate goes right

        assertTrue(tree.contains(10));
        assertTrue(isValidRedBlackTree(tree));
    }

    /**
     * Tests the properties of an empty Red-Black Tree.
     */
    @Test
    @DisplayName("Should handle empty tree correctly")
    void shouldHandleEmptyTree() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        assertNull(tree.root());
        assertFalse(tree.contains(1));
        assertNull(tree.min());
        assertNull(tree.max());
    }

    /**
     * Tests that the root of the tree is always black.
     */
    @Test
    @DisplayName("Should have black root")
    void shouldHaveBlackRoot() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        assertTrue(tree.root().color().isBlack());
    }

    // Helper methods to validate Red-Black properties

    private boolean isValidRedBlackTree(RedBlackTree<Integer> tree) {
        if (tree.root() == null) {
            return true;
        }

        // Property 2: Root is black
        if (tree.root().color().isRed()) {
            return false;
        }

        // Check other properties
        return checkProperties(tree.root());
    }

    private boolean checkProperties(RedBlackTreeNode<Integer> node) {
        if (node == null) {
            return true;
        }

        // Property 4: If node is red, both children must be black
        if (isRed(node) && (isRed(node.leftMutable()) || isRed(node.rightMutable()))) {
            return false;
        }

        // Recursively check children
        if (!checkProperties(node.left())) {
            return false;
        }
        if (!checkProperties(node.right())) {
            return false;
        }

        // Property 5: All paths have same black height
        int leftBlackHeight = blackHeight(node.left());
        int rightBlackHeight = blackHeight(node.right());
        return leftBlackHeight == rightBlackHeight;
    }

    private boolean isRed(RedBlackTreeNode<Integer> node) {
        return node != null && node.color().isRed();
    }

    private int blackHeight(RedBlackTreeNode<Integer> node) {
        if (node == null) {
            return 1; // null nodes are black
        }

        int leftHeight = blackHeight(node.left());
        int rightHeight = blackHeight(node.right());

        if (leftHeight != rightHeight) {
            return -1; // Invalid
        }

        return leftHeight + (node.color().isBlack() ? 1 : 0);
    }
}
