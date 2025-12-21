package br.com.dbarreto.algorithm.tree.traversal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.dbarreto.algorithm.tree.BinaryTreeTraversals;
import br.com.dbarreto.utils.BinaryTreeScenarios;
import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.datastructure.tree.impl.SimpleBinaryTree;

public class BinaryTreeTraversalsTest {

    @ParameterizedTest
    @MethodSource("inOrderArguments")
    void traverseInOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        BinaryTreeTraversals.traverseInOrder(binaryTree, actual::add);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("preOrderArguments")
    void traversePreOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        BinaryTreeTraversals.traversePreOrder(binaryTree, actual::add);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("postOrderArguments")
    void traversePostOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        BinaryTreeTraversals.traversePostOrder(binaryTree, actual::add);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("levelOrderArguments")
    void traverseLevelOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        BinaryTreeTraversals.traverseLevelOrder(binaryTree, actual::add);
        assertEquals(expected, actual);
    }

    @Test
    void testTraversalOnEmptyTree() {
        SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>();
        List<Integer> result = new ArrayList<>();
        BinaryTreeTraversals.traverseInOrder(tree, result::add);
        assertTrue(result.isEmpty());
    }

    private static Stream<Arguments> inOrderArguments() {
        return Stream.of(
                Arguments.of(
                        BinaryTreeScenarios.createEmptyBinaryTree(), Collections.emptyList()
                ),
                Arguments.of(
                        BinaryTreeScenarios.createPerfectBinaryTree(),
                        List.of(4, 2, 5, 1, 6, 3, 7)
                ),
                Arguments.of(
                        BinaryTreeScenarios.createMissingChildrenBinaryTree(),
                        List.of(4, 2, 6, 5, 1, 3, 7)
                )
        );
    }

    private static Stream<Arguments> preOrderArguments() {
        return Stream.of(
                Arguments.of(
                        BinaryTreeScenarios.createEmptyBinaryTree(), Collections.emptyList()
                ),
                Arguments.of(
                        BinaryTreeScenarios.createPerfectBinaryTree(),
                        List.of(1, 2, 4, 5, 3, 6, 7)
                ),
                Arguments.of(
                        BinaryTreeScenarios.createMissingChildrenBinaryTree(),
                        List.of(1, 2, 4, 5, 6, 3, 7)
                )
        );
    }

    private static Stream<Arguments> postOrderArguments() {
        return Stream.of(
                Arguments.of(
                        BinaryTreeScenarios.createEmptyBinaryTree(), Collections.emptyList()
                ),
                Arguments.of(
                        BinaryTreeScenarios.createPerfectBinaryTree(),
                        List.of(4, 5, 2, 6, 7, 3, 1)
                ),
                Arguments.of(
                        BinaryTreeScenarios.createMissingChildrenBinaryTree(),
                        List.of(4, 6, 5, 2, 7, 3, 1)
                )
        );
    }

    private static Stream<Arguments> levelOrderArguments() {
        return Stream.of(
                Arguments.of(
                        BinaryTreeScenarios.createEmptyBinaryTree(), Collections.emptyList()
                ),
                Arguments.of(
                        BinaryTreeScenarios.createPerfectBinaryTree(),
                        List.of(1, 2, 3, 4, 5, 6, 7)
                ),
                Arguments.of(
                        BinaryTreeScenarios.createMissingChildrenBinaryTree(),
                        List.of(1, 2, 3, 4, 5, 7, 6)
                )
        );
    }
}
