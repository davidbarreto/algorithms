package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.testutils.BinarySearchTreeScenarios;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {

    @Test
    void testTraversals() {
        var tree = BinarySearchTreeScenarios.createAvlTree();

        var inOrderList = new ArrayList<Integer>();
        tree.traverseInOrder(inOrderList::add);
        assertEquals(List.of(10, 20, 25, 30, 40, 50), inOrderList);

        var postOrderList = new ArrayList<Integer>();
        tree.traversePostOrder(postOrderList::add);
        assertEquals(List.of(10, 25, 20, 50, 40, 30), postOrderList);

        var preOrderList = new ArrayList<Integer>();
        tree.traversePreOrder(preOrderList::add);
        assertEquals(List.of(30, 20, 10, 25, 40, 50), preOrderList);
    }

    @Test
    void testLeftLeftRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);  // Triggers left-left rotation

        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10, 20, 30), inOrder);
        assertEquals(2, tree.height());  // Balanced
    }

    @Test
    void testRightRightRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);  // Triggers right-right rotation

        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10, 20, 30), inOrder);
        assertEquals(2, tree.height());
    }

    @Test
    void testLeftRightRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);  // Triggers left-right rotation

        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10, 20, 30), inOrder);
        assertEquals(2, tree.height());
    }

    @Test
    void testRightLeftRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);  // Triggers right-left rotation

        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10, 20, 30), inOrder);
        assertEquals(2, tree.height());
    }

    @Test
    void testBalanceAfterMultipleInserts() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 10; i++) {
            tree.insert(i);
        }
        assertTrue(tree.height() <= 4);  // AVL height bound
        assertTrue(tree.isBalanced());
    }

    @Test
    void testDuplicatesAllowed() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(10);  // Duplicate

        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10, 10), inOrder);  // Duplicates in right
    }

    @Test
    void testEmptyTree() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertNull(tree.root());
        assertEquals(0, tree.height());
        assertFalse(tree.contains(1));
    }
}