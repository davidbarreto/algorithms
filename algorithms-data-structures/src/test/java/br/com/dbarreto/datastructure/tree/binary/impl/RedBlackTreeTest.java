package br.com.dbarreto.datastructure.tree.binary.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.dbarreto.datastructure.tree.binary.impl.RedBlackTree.isRed;
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
        assertFalse(isRed(tree.getRoot()));
    }

    // Helper methods to validate Red-Black properties

    private boolean isValidRedBlackTree(RedBlackTree<Integer> tree) {

        var root = tree.getRoot();

        if (root == null) {
            return true;
        }

        // Property 2: Root is black
        if (isRed(root)) {
            return false;
        }

        // Check other properties
        return checkProperties(root);
    }

    private boolean checkProperties(RedBlackTree.RedBlackNode<Integer> node) {
        if (node == null) {
            return true;
        }

        // Property 4: If node is red, both children must be black
        if (isRed(node) && (isRed(node.left) || isRed(node.right))) {
            return false;
        }

        // Recursively check children
        if (!checkProperties(node.left)) {
            return false;
        }
        if (!checkProperties(node.right)) {
            return false;
        }

        // Property 5: All paths have same black height
        return RedBlackTree.blackHeight(node.left) == RedBlackTree.blackHeight(node.right);
    }
}
