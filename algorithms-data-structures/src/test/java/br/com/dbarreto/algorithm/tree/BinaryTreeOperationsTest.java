package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.utils.BinarySearchTreeScenarios;
import br.com.dbarreto.utils.BinaryTreeScenarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link BinaryTreeOperations}.
 */
class BinaryTreeOperationsTest {

    /**
     * Tests the {@link BinaryTreeOperations#size(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("sizeArguments")
    @DisplayName("Should return correct size")
    void shouldReturnCorrectSize(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, BinaryTreeOperations.size(tree));
    }

    /**
     * Tests the {@link BinaryTreeOperations#height(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("heightArguments")
    @DisplayName("Should return correct height")
    void shouldReturnCorrectHeight(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, BinaryTreeOperations.height(tree));
    }

    /**
     * Tests the {@link BinaryTreeOperations#contains(BinaryTree, Object)} method.
     */
    @ParameterizedTest
    @MethodSource("containsArguments")
    @DisplayName("Should check if tree contains value")
    void shouldCheckIfTreeContainsValue(Integer searchedValue, boolean expected) {
        var tree = BinaryTreeScenarios.createPerfectBinaryTree();
        assertEquals(expected, BinaryTreeOperations.contains(tree, searchedValue));
    }

    /**
     * Tests the {@link BinaryTreeOperations#isBalanced(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("isBalancedArguments")
    @DisplayName("Should check if tree is balanced")
    void shouldCheckIfTreeIsBalanced(BinaryTree<Integer> tree, boolean expected) {
        assertEquals(expected, BinaryTreeOperations.isBalanced(tree));
    }

    /**
     * Tests the {@link BinaryTreeOperations#equals(BinaryTree, BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("equalsArguments")
    @DisplayName("Should check if trees are equal")
    void shouldCheckIfTreesAreEqual(BinaryTree<Integer> tree1, BinaryTree<Integer> tree2, boolean expected) {
        assertEquals(expected, BinaryTreeOperations.equals(tree1, tree2));
    }

    private static Stream<Arguments> sizeArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), 0),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), 7),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree(), 7),
                Arguments.of(BinaryTreeScenarios.createBigBinaryTree(), 1023)
        );
    }

    private static Stream<Arguments> heightArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), 0),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), 3),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree(), 4),
                Arguments.of(BinaryTreeScenarios.createBigBinaryTree(), 10)
        );
    }

    private static Stream<Arguments> containsArguments() {
        return Stream.of(
                Arguments.of(100, false),
                Arguments.of(0, false),
                Arguments.of(-100, false),
                Arguments.of(1, true),
                Arguments.of(7, true),
                Arguments.of(null, false)
        );
    }

        private static Stream<Arguments> isBalancedArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), true),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), true),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree(), true),
                Arguments.of(BinaryTreeScenarios.createLeftUnbalancedBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createRightUnbalancedBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createBigBinaryTree(), true)
        );
    }

    private static Stream<Arguments> equalsArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), BinaryTreeScenarios.createEmptyBinaryTree(), true),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), BinaryTreeScenarios.createPerfectBinaryTree(), true),
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), BinaryTreeScenarios.createPerfectBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), BinaryTreeScenarios.createMissingChildrenBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree(), BinaryTreeScenarios.createPerfectBinaryTree(), false),
                Arguments.of(BinaryTreeScenarios.createPerfectBstTree(), BinarySearchTreeScenarios.createSimpleBstWithPerfectStructure(), true),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), BinarySearchTreeScenarios.createAvlTreeWithPerfectInserts(), false),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), BinarySearchTreeScenarios.createRedBlackTreeWithPerfectInserts(), false),
                Arguments.of(BinarySearchTreeScenarios.createAvlTreeWithPerfectInserts(), BinarySearchTreeScenarios.createRedBlackTreeWithPerfectInserts(), true)
        );
    }
}