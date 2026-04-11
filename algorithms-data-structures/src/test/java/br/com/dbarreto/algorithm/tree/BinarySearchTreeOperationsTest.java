package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.tree.binary.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.utils.BinarySearchTreeScenarios;
import br.com.dbarreto.utils.BinaryTreeScenarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BinarySearchTreeOperations}.
 */
class BinarySearchTreeOperationsTest {

    /**
     * Tests the {@link BinarySearchTree#min()} method.
     */
    @ParameterizedTest
    @MethodSource("minArguments")
    @DisplayName("Should return minimum value")
    void shouldReturnMinValue(BinarySearchTree<Integer> tree, int expected) {
        assertEquals(expected, tree.min());
    }

    /**
     * Tests the {@link BinarySearchTree#max()} method.
     */
    @ParameterizedTest
    @MethodSource("maxArguments")
    @DisplayName("Should return maximum value")
    void shouldReturnMaxValue(BinarySearchTree<Integer> tree, int expected) {
        assertEquals(expected, tree.max());
    }

    /**
     * Tests the {@link BinarySearchTree#contains(Object)} method.
     */
    @ParameterizedTest
    @MethodSource("containsArguments")
    @DisplayName("Should check if tree contains value")
    void shouldCheckIfTreeContainsValue(BinarySearchTree<Integer> tree, Integer searchedElement, boolean expected) {
        assertEquals(expected, tree.contains(searchedElement));
    }

    /**
     * Tests the {@link BinarySearchTreeOperations#isBinarySearchTree(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("isBinarySearchTreeArguments")
    @DisplayName("Should check if tree is binary search tree")
    void shouldCheckIfTreeIsBinarySearchTree(BinaryTree<Integer> tree, boolean expected) {
        assertEquals(expected, BinarySearchTreeOperations.isBinarySearchTree(tree));
    }

    private static Stream<Arguments> minArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), -100),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), 1),
                Arguments.of(BinarySearchTreeScenarios.createAvlTree(), 10),
                Arguments.of(BinarySearchTreeScenarios.createRedBlackTree(), Integer.MIN_VALUE)
        );
    }

    private static Stream<Arguments> maxArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), 10),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), 5),
                Arguments.of(BinarySearchTreeScenarios.createAvlTree(), 50),
                Arguments.of(BinarySearchTreeScenarios.createRedBlackTree(), 300)
        );
    }

    private static Stream<Arguments> containsArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), -1, true),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), -100, true),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), 0, true),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), 4, true),
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), -200, false),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), 3, true),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), -1, false),
                Arguments.of(BinarySearchTreeScenarios.createAvlTree(), 30, true),
                Arguments.of(BinarySearchTreeScenarios.createAvlTree(), 100, false),
                Arguments.of(BinarySearchTreeScenarios.createRedBlackTree(), -234, true),
                Arguments.of(BinarySearchTreeScenarios.createRedBlackTree(), 234, false)
        );
    }

    private static Stream<Arguments> isBinarySearchTreeArguments() {
        return Stream.of(
                // Empty tree is considered a valid BST
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), true),
                // Regular binary trees that are not BSTs
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createSimpleBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createNonBstBinaryTreeCase1(), false),
                Arguments.of(BinaryTreeScenarios.createNonBstBinaryTreeCase2(), false),
                Arguments.of(BinaryTreeScenarios.createNonBstBinaryTreeCase3(), false),
                // Binary tree that happens to be a BST
                Arguments.of(BinaryTreeScenarios.createBstBinaryTreeCase1(), true),
                Arguments.of(BinaryTreeScenarios.createBstBinaryTreeCase2(), true),
                // BinarySearchTree instances - these return true immediately via instanceof check
                Arguments.of(BinarySearchTreeScenarios.createBstWithUnorderedInserts(), true),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), true),
                Arguments.of(BinarySearchTreeScenarios.createAvlTree(), true),
                Arguments.of(BinarySearchTreeScenarios.createRedBlackTree(), true)
        );
    }
}
