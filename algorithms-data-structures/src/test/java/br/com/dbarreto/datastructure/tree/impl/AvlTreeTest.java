package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.tree.binary.impl.AvlTree;
import br.com.dbarreto.utils.BinarySearchTreeScenarios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link AvlTree}.
 */
class AvlTreeTest {

    /**
     * Tests the different traversal methods on a sample AVL tree.
     */
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

    /**
     * Tests that the tree remains balanced and correctly ordered after various rotations.
     */
    @ParameterizedTest
    @MethodSource("rotationArguments")
    void testRotation(AvlTree<Integer> tree) {
        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10, 20, 30), inOrder);
        assertEquals(2, tree.height());
    }

    /**
     * Tests that the tree remains balanced after a sequence of insertions.
     */
    @Test
    void testBalanceAfterMultipleInserts() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 10; i++) {
            tree.insert(i);
        }
        assertTrue(tree.height() <= 4);  // AVL height bound
        assertTrue(tree.isBalanced());
    }

    /**
     * Tests that duplicate elements are not inserted.
     */
    @Test
    void testDuplicatesAllowed() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(10);  // Duplicate

        List<Integer> inOrder = new ArrayList<>();
        tree.traverseInOrder(inOrder::add);
        assertEquals(List.of(10), inOrder);  // Duplicates discarded
    }

    /**
     * Tests the properties of an empty AVL tree.
     */
    @Test
    void testEmptyTree() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertNull(tree.root());
        assertEquals(0, tree.height());
        assertTrue(tree.isEmpty());
        assertFalse(tree.contains(1));
    }

    /**
     * Tests the {@code contains} method.
     */
    @Test
    void testContains() {
        AvlTree<Integer> tree = new AvlTree<>();
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
        assertFalse(tree.contains(0));
        assertFalse(tree.contains(10));
    }

    /**
     * Tests the {@code min} and {@code max} methods.
     */
    @Test
    void testMinMax() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        assertEquals(1, tree.min());
        assertEquals(9, tree.max());
    }

    /**
     * Tests the {@code contains} method after rotations have occurred.
     */
    @Test
    void testContainsAfterRotations() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);  // Right-right rotation

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(30));
        assertEquals(2, tree.height());
    }

    /**
     * Tests various operations on a large AVL tree.
     */
    @Test
    void testLargeTreeOperations() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }

        assertFalse(tree.isEmpty());
        assertEquals(100, tree.size());
        assertEquals(1, tree.min());
        assertEquals(100, tree.max());
        assertTrue(tree.contains(50));
        assertFalse(tree.contains(101));
        assertTrue(tree.isBalanced());
        assertTrue(tree.height() <= 8); // AVL height bound: logarithmic, not fixed for 100 nodes
        assertThat(tree.balanceFactor()).isBetween(-1, 1);
    }

    static Stream<Arguments> rotationArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createAvlTreeThatTriggersLeftLeftRotation()),
                Arguments.of(BinarySearchTreeScenarios.createAvlTreeThatTriggersRightRightRotation()),
                Arguments.of(BinarySearchTreeScenarios.createAvlTreeThatTriggersRightLeftRotation()),
                Arguments.of(BinarySearchTreeScenarios.createAvlTreeThatTriggersLeftRightRotation())
        );
    }
}
