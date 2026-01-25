package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.datastructure.tree.binary.BinarySearchTree;
import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Property-based tests for tree data structures.
 * <p>
 * These tests use jqwik to generate random inputs and verify that
 * tree properties hold under various scenarios.
 * </p>
 */
class TreePropertiesTest {

    /**
     * Verifies that the Binary Search Tree property holds for a {@link StandardBinarySearchTree}.
     */
    @Property
    void shouldMaintainBinarySearchTreeProperty(@ForAll("integerLists") List<Integer> values) {
        StandardBinarySearchTree<Integer> tree = new StandardBinarySearchTree<>();
        values.forEach(tree::insert);

        isValidBST(tree.root());
    }

    /**
     * Verifies that an {@link AvlTree} maintains its properties (balanced, BST, logarithmic height).
     */
    @Property
    void shouldMaintainAvlTreeProperties(@ForAll("smallIntegerLists") @Size(max = 20) List<Integer> values) {
        AvlTree<Integer> tree = new AvlTree<>();
        values.forEach(tree::insert);

        assertFalse(tree.isEmpty());
        long uniqueCount = values.stream().distinct().count();
        assertEquals(uniqueCount, tree.size());
        assertTrue(tree.isBalanced());
        isValidBST(tree.root());
        isValidAvlTree(tree);
        isHeightLogarithmic(tree);
    }

    /**
     * Verifies that a {@link RedBlackTree} maintains its properties (BST, Red-Black rules, logarithmic height).
     */
    @Property
    void shouldMaintainRedBlackTreeProperties(@ForAll("smallIntegerLists") @Size(max = 20) List<Integer> values) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        values.forEach(tree::insert);

        assertFalse(tree.isEmpty());
        assertEquals(values.size(), tree.size());
        isValidBST(tree.root());
        isValidRedBlackTree(tree);
        isHeightLogarithmic(tree);
    }

    /**
     * Verifies that the size of the tree equals the number of inserted elements.
     */
    @Property
    void shouldHaveSizeEqualToNumberOfElements(@ForAll("integerLists") List<Integer> values) {
        StandardBinarySearchTree<Integer> tree = new StandardBinarySearchTree<>();
        values.forEach(tree::insert);

        // BST allows duplicates, so size should equal total insertions
        assertThat(tree.size()).isEqualTo(values.size());
    }

    /**
     * Verifies that all inserted elements can be found in the tree.
     */
    @Property
    void shouldFindAllInsertedElements(@ForAll("integerLists") List<Integer> values) {
        StandardBinarySearchTree<Integer> tree = new StandardBinarySearchTree<>();
        values.forEach(tree::insert);

        for (Integer value : values) {
            assertThat(tree.contains(value)).isTrue();
        }
    }

    /**
     * Verifies that the height of a self-balancing tree remains logarithmic.
     */
    @Property
    void shouldMaintainLogarithmicHeightInBalancedTree(@ForAll("largeIntegerLists") List<Integer> values,
                                         @ForAll("selfBalancedTrees") BinarySearchTree<Integer> tree)
    {
        values.forEach(tree::insert);

        int n = values.size();
        int height = tree.height();

        int maxAllowed = maxAllowedHeight(n);
        assertTrue(height <= maxAllowed);
    }

    @Provide
    Arbitrary<List<Integer>> largeIntegerLists() {
        return Arbitraries.integers()
                .between(-1000, 1000)
                .list()
                .ofSize(1000);
    }

    @Provide
    Arbitrary<List<Integer>> integerLists() {
        return Arbitraries.integers()
                .between(-1000, 1000)
                .list()
                .ofSize(100);
    }

    @Provide
    Arbitrary<List<Integer>> smallIntegerLists() {
        return Arbitraries.integers()
                .between(-1000, 1000)
                .list()
                .ofSize(20);
    }

    @Provide
    Arbitrary<BinarySearchTree<Integer>> selfBalancedTrees() {
        return Arbitraries.ofSuppliers(
                AvlTree::new,
                RedBlackTree::new
        );
    }

    // Helper methods for property validation

    private void isValidBST(BinaryTreeNode<Integer> node) {
        isValidBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private void isValidBST(BinaryTreeNode<Integer> node, int min, int max) {
        if (node == null) {
            return;
        }

        int value = node.value();
        if (value < min || value > max) {
            throw new AssertionError("Node value " + value + " is out of valid range [" + min + ", " + max + "]");
        }
        
        // For BST: left <= root <= right (allows duplicates)
        isValidBST(node.left(), min, value);
        isValidBST(node.right(), value, max);
    }

    private void isValidAvlTree(AvlTree<Integer> tree) {
        isValidAvlTree(tree, tree.getRoot());
    }

    private void isValidAvlTree(AvlTree<Integer> tree, AvlTree.AvlNode<Integer> node) {
        if (node == null) return;

        // Check balance factor using tree's method
        int balance;
        if (Math.abs(balance = AvlTree.balanceFactor(node)) < -1) {
            throw new AssertionError("Balance factor violation: " + balance + " (must be between -1 and 1)");
        }

        // Recursively check subtrees
        isValidAvlTree(tree, node.left);
        isValidAvlTree(tree, node.right);
    }

    private void isValidRedBlackTree(RedBlackTree<Integer> tree) {
        isValidRedBlackTree(tree, tree.getRoot());
    }

    private void isValidRedBlackTree(RedBlackTree<Integer> tree, RedBlackTree.RedBlackNode<Integer> node) {
        if (node == null) {
            return;
        }

        // Property 4: If a node is red, both children must be black
        if (RedBlackTree.isRed(node) && (RedBlackTree.isRed(node.left) || RedBlackTree.isRed(node.right))) {
            throw new AssertionError("Property 4 violation: Red node has red child");
        }

        // Property 5: Every path from node to leaf has same number of black nodes
        if (RedBlackTree.blackHeight(node) == -1) {
            throw new AssertionError("Property 5 violation: Inconsistent black heights in subtrees");
        }

        // Recursively check subtrees
        isValidRedBlackTree(tree, node.left);
        isValidRedBlackTree(tree, node.right);
    }

    private void isHeightLogarithmic(BinarySearchTree<Integer> tree) {
        int n = tree.size();
        int height = tree.height();
        int maxHeightAllowed = maxAllowedHeight(n);

        if (height > maxHeightAllowed) {
            throw new AssertionError("Height [" + height + "] exceeds max height allowed [2*log2("
                    + n + "] = [" + maxHeightAllowed + ")]");
        }
    }

    /**
     * Computes the maximum allowed height for a self-balancing binary search tree
     * (AVL, Red-Black, etc.) given the number of nodes.
     * <p>
     * For simplicity and robustness, we use the same upper bound for all auto-balanced
     * tree implementations, even though AVL trees have a tighter theoretical bound
     * (~1.44 * log2(n + 2)).
     * </p>
     * <p>
     * Formula: {@code 2.0 * log2(n + 2)}
     * - The factor 2.0 safely covers Red-Black trees and also includes a margin for AVL trees.
     * - Adding 2 to n ensures correct computation for very small trees (n = 0 or 1) and avoids rounding issues.
     * - This bound is theoretical; actual tree heights are usually smaller.
     * - Purpose: used in property-based tests to verify trees maintain logarithmic height
     *   and do not degenerate.
     * </p>
     *
     * @param n the number of nodes in the tree
     * @return the maximum allowed height
     */
    private int maxAllowedHeight(int n) {
        return (int) (2.0 * Math.log(n + 2) / Math.log(2));
    }
}
