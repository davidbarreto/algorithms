package br.com.dbarreto.algorithm.tree;

import br.com.dbarreto.datastructure.node.tree.binary.MutableBinaryTreeNode;
import br.com.dbarreto.datastructure.node.tree.binary.impl.SimpleMutableBinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.tree.binary.impl.SimpleMutableBinaryTreeNode;
import br.com.dbarreto.datastructure.node.tree.binary.impl.SimpleMutableHeightBinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.datastructure.tree.binary.impl.SimpleBinaryTree;
import br.com.dbarreto.utils.BinarySearchTreeScenarios;
import br.com.dbarreto.utils.BinaryTreeScenarios;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
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
    void testSize(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, BinaryTreeOperations.size(tree));
    }

    /**
     * Tests the {@link BinaryTreeOperations#height(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("heightArguments")
    void testHeight(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, BinaryTreeOperations.height(tree));
    }

    /**
     * Tests the {@link BinaryTreeOperations#contains(BinaryTree, Object)} method.
     */
    @ParameterizedTest
    @MethodSource("containsArguments")
    void testContains(Integer searchedValue, boolean expected) {
        var tree = BinaryTreeScenarios.createPerfectBinaryTree();
        assertEquals(expected, BinaryTreeOperations.contains(tree, searchedValue));
    }

    /**
     * Tests the {@link BinaryTreeOperations#isBalanced(BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("isBalancedArguments")
    void testIsBalanced(BinaryTree<Integer> tree, boolean expected) {
        assertEquals(expected, BinaryTreeOperations.isBalanced(tree));
    }

    /**
     * Tests the {@link BinaryTreeOperations#equals(BinaryTree, BinaryTree)} method.
     */
    @ParameterizedTest
    @MethodSource("equalsArguments")
    void testEquals(BinaryTree<Integer> tree1, BinaryTree<Integer> tree2, boolean expected) {
        assertEquals(expected, BinaryTreeOperations.equals(tree1, tree2));
    }

    /**
     * Tests the {@link BinaryTreeOperations#deepCopy(BinaryTreeNode, Function)} method.
     */
    @ParameterizedTest
    @MethodSource("deepCopyArguments")
    <T, N extends MutableBinaryTreeNode<T, N>> void testDeepCopy(BinaryTree<T> source, Function<T, N> constructor) {
        var copiedRoot = BinaryTreeOperations.deepCopy(source.root(), constructor);
        assertTrue(BinaryTreeOperations.equals(source.root(), copiedRoot));
    }

    /**
     * Tests the {@link BinaryTreeOperations#deepCopy(BinaryTree, Function, Function)} method.
     */
    @ParameterizedTest
    @MethodSource("deepCopyTreeArguments")
    <T, M extends BinaryTree<T>, N extends MutableBinaryTreeNode<T, N>> void testDeepCopyTree(BinaryTree<T> source, Function<N, M> treeConstructor, Function<T, N> nodeConstructor) {
        var copiedTree = BinaryTreeOperations.deepCopy(source, treeConstructor, nodeConstructor);
        assertTrue(BinaryTreeOperations.equals(source, copiedTree));
        // Verify it's a different instance
        assertNotSame(source, copiedTree);
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
    private static Stream<Arguments> deepCopyArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), simpleBstConstructor()),
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), heightBstConstructor()),
                Arguments.of(BinaryTreeScenarios.createPerfectBstTree(), simpleBstConstructor()),
                Arguments.of(BinaryTreeScenarios.createPerfectBstTree(), heightBstConstructor()),
                Arguments.of(BinarySearchTreeScenarios.createBstWithOrderedInserts(), heightBstConstructor())
        );
    }

    private static Stream<Arguments> deepCopyTreeArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeScenarios.createPerfectBinaryTree(), simpleBinaryTreeConstructor(), simpleMutableBinaryTreeNodeConstructor()),
                Arguments.of(BinaryTreeScenarios.createEmptyBinaryTree(), simpleBinaryTreeConstructor(), simpleMutableBinaryTreeNodeConstructor()),
                Arguments.of(BinaryTreeScenarios.createMissingChildrenBinaryTree(), simpleBinaryTreeConstructor(), simpleMutableBinaryTreeNodeConstructor()),
                Arguments.of(BinarySearchTreeScenarios.createSimpleBstWithPerfectStructure(), simpleBinaryTreeConstructor(), simpleMutableBinaryTreeNodeConstructor())
        );
    }

    private static Function<Integer, SimpleMutableBinarySearchTreeNode<Integer>> simpleBstConstructor() {
        return SimpleMutableBinarySearchTreeNode::new;
    }

    private static Function<Integer, SimpleMutableHeightBinarySearchTreeNode<Integer>> heightBstConstructor() {
        return SimpleMutableHeightBinarySearchTreeNode::new;
    }

    private static Function<SimpleMutableBinaryTreeNode<Integer>, SimpleBinaryTree<Integer>> simpleBinaryTreeConstructor() {
        return SimpleBinaryTree::new;
    }

    private static Function<Integer, SimpleMutableBinaryTreeNode<Integer>> simpleMutableBinaryTreeNodeConstructor() {
        return SimpleMutableBinaryTreeNode::new;
    }}