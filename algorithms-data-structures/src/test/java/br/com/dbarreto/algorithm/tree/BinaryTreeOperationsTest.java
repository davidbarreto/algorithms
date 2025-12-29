package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.utils.BinarySearchTreeScenarios;
import br.com.dbarreto.utils.BinaryTreeScenarios;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeOperationsTest {

    @ParameterizedTest
    @MethodSource("sizeArguments")
    void testSize(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, BinaryTreeOperations.size(tree));
    }

    @ParameterizedTest
    @MethodSource("heightArguments")
    void testHeight(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, BinaryTreeOperations.height(tree));
    }

    @ParameterizedTest
    @MethodSource("containsArguments")
    void testContains(Integer searchedValue, boolean expected) {
        var tree = BinaryTreeScenarios.createPerfectBinaryTree();
        assertEquals(expected, BinaryTreeOperations.contains(tree, searchedValue));
    }

    @ParameterizedTest
    @MethodSource("isBalancedArguments")
    void testIsBalanced(BinaryTree<Integer> tree, boolean expected) {
        assertEquals(expected, BinaryTreeOperations.isBalanced(tree));
    }

    @ParameterizedTest
    @MethodSource("equalsArguments")
    void testEquals(BinaryTree<Integer> tree1, BinaryTree<Integer> tree2, boolean expected) {
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