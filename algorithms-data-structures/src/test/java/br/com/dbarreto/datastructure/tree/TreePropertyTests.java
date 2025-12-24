package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.tree.impl.AvlTree;
import br.com.dbarreto.datastructure.tree.impl.RedBlackTree;
import br.com.dbarreto.datastructure.node.impl.SimpleMutableHeightBinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.impl.RedBlackTreeNode;
import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.impl.SimpleBinarySearchTree;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static br.com.dbarreto.datastructure.node.ColoredBinarySearchTreeNode.Color.RED;
import static br.com.dbarreto.datastructure.node.ColoredBinarySearchTreeNode.Color.BLACK;

/**
 * Property-based tests for tree data structures.
 *
 * These tests use jqwik to generate random inputs and verify that
 * tree properties hold under various scenarios.
 */
class TreePropertyTests {

    @Property
    void binarySearchTreePropertyHolds(@ForAll("integerLists") List<Integer> values) {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        values.forEach(tree::insert);

        isValidBST(tree.root());
    }

    @Property
    void avlTreeRemainsBalanced(@ForAll("smallIntegerLists") @Size(max = 20) List<Integer> values) {
        AvlTree<Integer> tree = new AvlTree<>();
        values.forEach(tree::insert);

        assertFalse(tree.isEmpty());
        long uniqueCount = values.stream().distinct().count();
        assertEquals(uniqueCount, tree.size());
        assertTrue(tree.isBalanced());
        isValidBST(tree.root());
        isValidAvlTree(tree);
    }

    @Property
    void redBlackTreeMaintainsProperties(@ForAll("verySmallIntegerLists") @Size(max = 15) List<Integer> values) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        values.forEach(tree::insert);

        assertFalse(tree.isEmpty());
        assertEquals(values.size(), tree.size());
        isValidBST(tree.root());
        isValidRedBlackTree(tree);
    }

    @Property
    void treeSizeEqualsNumberOfElements(@ForAll("integerLists") List<Integer> values) {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        values.forEach(tree::insert);

        // BST allows duplicates, so size should equal total insertions
        assertThat(tree.size()).isEqualTo(values.size());
    }

    @Property
    void insertedElementsAreFindable(@ForAll("integerLists") List<Integer> values) {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        values.forEach(tree::insert);

        for (Integer value : values) {
            assertThat(tree.contains(value)).isTrue();
        }
    }

    @Property
    void treeHeightIsReasonable(@ForAll("mediumIntegerLists") @Size(max = 50) List<Integer> values) {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        values.forEach(tree::insert);

        int height = tree.height();
        int optimalHeight = (int) (Math.log(values.size()) / Math.log(2));

        // Height should not be excessively large (allowing some tolerance for unbalanced trees)
        assertThat(height).isLessThanOrEqualTo(values.size()); // worst case
        assertThat(height).isGreaterThanOrEqualTo(optimalHeight - 2); // not too unbalanced
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
    Arbitrary<List<Integer>> verySmallIntegerLists() {
        return Arbitraries.integers()
                .between(-1000, 1000)
                .list()
                .ofSize(15);
    }

    @Provide
    Arbitrary<List<Integer>> mediumIntegerLists() {
        return Arbitraries.integers()
                .between(-1000, 1000)
                .list()
                .ofSize(50);
    }

    // Helper methods for property validation

    private void isValidBST(BinarySearchTreeNode<Integer> node) {
        isValidBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private void isValidBST(BinarySearchTreeNode<Integer> node, int min, int max) {
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
        isValidAvlTree(tree, tree.root());
    }

    private void isValidAvlTree(AvlTree<Integer> tree, BinaryTreeNode<Integer> node) {
        if (node == null) return;

        // Must be a height-aware node for AVL validation
        if (!(node instanceof SimpleMutableHeightBinarySearchTreeNode<Integer> heightNode)) {
            throw new AssertionError("Node is not a height-aware AVL node");
        }

        // Check balance factor using tree's method
        int balance = tree.balanceFactor(heightNode);
        if (Math.abs(balance) > 1) {
            throw new AssertionError("Balance factor violation: " + balance + " (must be between -1 and 1)");
        }

        // Recursively check subtrees
        isValidAvlTree(tree, node.left());
        isValidAvlTree(tree, node.right());
    }

    private void isValidRedBlackTree(RedBlackTree<Integer> tree) {
        isValidRedBlackTree(tree, tree.root());
    }

    private void isValidRedBlackTree(RedBlackTree<Integer> tree, BinaryTreeNode<Integer> node) {
        if (node == null) return;

        // Must be a colored node for RB validation
        if (!(node instanceof RedBlackTreeNode<Integer> rbNode)) {
            throw new AssertionError("Node is not a Red-Black tree node");
        }

        // Property 1: Every node is either red or black
        if (rbNode.color() != RED && rbNode.color() != BLACK) {
            throw new AssertionError("Property 1 violation: Node has invalid color");
        }

        // Property 4: If a node is red, both children must be black
        if (rbNode.color() == RED && ((rbNode.leftMutable() != null && rbNode.leftMutable().color() == RED) ||
                (rbNode.rightMutable() != null && rbNode.rightMutable().color() == RED))) {
                throw new AssertionError("Property 4 violation: Red node has red child");
            }


        // Property 5: Every path from node to leaf has same number of black nodes
        int blackHeight = tree.blackHeight(rbNode);
        if (blackHeight == -1) {
            throw new AssertionError("Property 5 violation: Inconsistent black heights in subtrees");
        }

        // Recursively check subtrees
        isValidRedBlackTree(tree, node.left());
        isValidRedBlackTree(tree, node.right());
    }
}