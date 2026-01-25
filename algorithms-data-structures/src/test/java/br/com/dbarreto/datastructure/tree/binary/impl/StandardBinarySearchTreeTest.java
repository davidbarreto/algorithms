package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.utils.BinarySearchTreeScenarios;
import br.com.dbarreto.datastructure.tree.binary.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;

import br.com.dbarreto.utils.BinaryTreeScenarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StandardBinarySearchTree}.
 */
class StandardBinarySearchTreeTest {

    /**
     * Tests the {@code insert} method.
     */
    @ParameterizedTest
    @MethodSource("insertArguments")
    @DisplayName("Should insert elements correctly")
    void shouldInsertElements(StandardBinarySearchTree<Integer> tree, List<Integer> expected) {
        assertTree(tree, expected);
    }

    /**
     * Tests the {@code delete} method.
     */
    @ParameterizedTest
    @MethodSource("deleteArguments")
    @DisplayName("Should delete elements correctly")
    void shouldDeleteElements(Integer valueToDelete, List<Integer> expected) {
        var tree = BinarySearchTreeScenarios.createBstWithUnorderedInserts();
        tree.delete(valueToDelete);
        assertTree(tree, expected);
    }

    /**
     * Tests the {@code min} method.
     */
    @ParameterizedTest
    @MethodSource("minArguments")
    @DisplayName("Should return minimum value")
    void shouldReturnMinValue(BinarySearchTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.min());
    }

    /**
     * Tests the {@code max} method.
     */
    @ParameterizedTest
    @MethodSource("maxArguments")
    @DisplayName("Should return maximum value")
    void shouldReturnMaxValue(BinarySearchTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.max());
    }

    /**
     * Tests the {@code root} method.
     */
    @ParameterizedTest
    @MethodSource("rootArguments")
    @DisplayName("Should return root value")
    void shouldReturnRootValue(StandardBinarySearchTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.root() != null ? tree.root().value() : null);
    }

    /**
     * Tests the {@code contains} method.
     */
    @ParameterizedTest
    @MethodSource("containsArguments")
    @DisplayName("Should check if tree contains value")
    void shouldCheckIfTreeContainsValue(Integer searchedVal, boolean expected) {
        var tree = BinarySearchTreeScenarios.createBstWithUnorderedInserts();
        assertEquals(expected, tree.contains(searchedVal));
    }

    /**
     * Tests both insertion and search functionality.
     */
    @Test
    @DisplayName("Should insert and search correctly")
    void shouldInsertAndSearch() {
        StandardBinarySearchTree<Integer> tree = new StandardBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(10));
    }

    /**
     * Tests the {@code min} and {@code max} methods.
     */
    @Test
    @DisplayName("Should return min and max values")
    void shouldReturnMinAndMaxValues() {
        StandardBinarySearchTree<Integer> tree = new StandardBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertEquals(3, tree.min());
        assertEquals(7, tree.max());
    }

    /**
     * Tests inserting a large number of elements.
     */
    @Test
    @DisplayName("Should handle large number of inserts")
    void shouldHandleLargeInserts() {
        StandardBinarySearchTree<Integer> tree = new StandardBinarySearchTree<>();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        assertEquals(99, tree.max());
        assertTrue(tree.contains(50));
    }

    /**
     * Tests the {@link StandardBinarySearchTree#StandardBinarySearchTree(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("copyTreeArguments")
    @DisplayName("Should deep copy tree that happens to be a BST")
    <T> void shouldCopyBstTree(BinaryTree<Integer> source) {
        var copiedTree = new StandardBinarySearchTree<>(source);
        assertTrue(BinaryTreeOperations.equals(source, copiedTree));
        // Verify it's a different instance
        assertNotSame(source, copiedTree);
    }

    /**
     * Tests the {@link StandardBinarySearchTree#StandardBinarySearchTree(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("copyTreeFailedArguments")
    @DisplayName("Should NOT copy tree which is not a BST")
    <T> void shouldNotCopyNonBstTree(BinaryTree<Integer> source) {
        assertThrows(IllegalArgumentException.class, () -> new StandardBinarySearchTree<>(source));
    }

    private void assertTree(BinaryTree<Integer> tree, List<Integer> expected) {
        var actual = new ArrayList<>();
        tree.traverseInOrder(actual::add);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> insertArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createEmptyBst(), Collections.emptyList()),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), List.of(1, 2, 3, 4, 5)),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), List.of(-100, -3, -1, 0, 1, 2, 3, 4, 5, 10))
        );
    }

    private static Stream<Arguments> deleteArguments() {
        return Stream.of(
                // delete root
                Arguments.of(2, List.of(-100, -3, -1, 0, 1, 3, 4, 5, 10)),
                // delete node with 2 children
                Arguments.of(0, List.of(-100, -3, -1, 1, 2, 3, 4, 5, 10)),
                // delete node with left only
                Arguments.of(-1, List.of(-100, -3, 0, 1, 2, 3, 4, 5, 10)),
                // delete node with right only
                Arguments.of(3, List.of(-100, -3, -1, 0, 1, 2, 4, 5, 10)),
                // delete leaf
                Arguments.of(10, List.of(-100, -3, -1, 0, 1, 2, 3, 4, 5))
        );
    }

    private static Stream<Arguments> minArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createEmptyBst(), null),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), 1),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), -100)
        );
    }

    private static Stream<Arguments> maxArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createEmptyBst(), null),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), 5),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), 10)
        );
    }

    private static Stream<Arguments> rootArguments() {
        return Stream.of(
            Arguments.of(BinarySearchTreeScenarios.createEmptyBst(), null),
            Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), 1),
            Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), 2)
        );
    }

    private static Stream<Arguments> containsArguments() {
        return Stream.of(
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(-100, true),
                Arguments.of(Integer.MAX_VALUE, false),
                Arguments.of(null, false),
                Arguments.of(100_000, false)
        );
    }

    private static Stream<Arguments> copyTreeArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree()),
                Arguments.of(BinarySearchTreeScenarios.createSimpleBstWithPerfectStructure())
        );
    }

    private static Stream<Arguments> copyTreeFailedArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree()),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree())
        );
    }
}
