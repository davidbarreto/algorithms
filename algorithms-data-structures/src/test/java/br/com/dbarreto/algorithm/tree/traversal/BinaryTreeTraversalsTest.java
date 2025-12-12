package br.com.dbarreto.algorithm.tree.traversal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.dbarreto.datastructure.testutils.BinaryTreeFactory;
import br.com.dbarreto.datastructure.tree.BinaryTree;

public class BinaryTreeTraversalsTest {

    @ParameterizedTest
    @MethodSource("inOrderArguments")
    void traverseInOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        binaryTree.traverseInOrder(actual::add);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("preOrderArguments")
    void traversePreOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        binaryTree.traversePreOrder(actual::add);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("postOrderArguments")
    void traversePostOrder(BinaryTree<Integer> binaryTree, List<Integer> expected) {
        List<Integer> actual = new ArrayList<>();
        binaryTree.traversePostOrder(actual::add);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> inOrderArguments() {
        return Stream.of(
            Arguments.of(
                    BinaryTreeFactory.createEmptyBinaryTree(), Collections.emptyList()
            ),
            Arguments.of(
                    BinaryTreeFactory.createPerfectBinaryTree(),
                    List.of(4, 2, 5, 1, 6, 3, 7)
            ),
            Arguments.of(
                    BinaryTreeFactory.createMissingChildrenBinaryTree(),
                    List.of(4, 2, 6, 5, 1, 3, 7)
            )
        );
    }

    private static Stream<Arguments> preOrderArguments() {
        return Stream.of(
                Arguments.of(
                        BinaryTreeFactory.createEmptyBinaryTree(), Collections.emptyList()
                ),
                Arguments.of(
                        BinaryTreeFactory.createPerfectBinaryTree(),
                        List.of(1, 2, 4, 5, 3, 6, 7)
                ),
                Arguments.of(
                        BinaryTreeFactory.createMissingChildrenBinaryTree(),
                        List.of(1, 2, 4, 5, 6, 3, 7)
                )
        );
    }

    private static Stream<Arguments> postOrderArguments() {
        return Stream.of(
                Arguments.of(
                        BinaryTreeFactory.createEmptyBinaryTree(), Collections.emptyList()
                ),
                Arguments.of(
                        BinaryTreeFactory.createPerfectBinaryTree(),
                        List.of(4, 5, 2, 6, 7, 3, 1)
                ),
                Arguments.of(
                        BinaryTreeFactory.createMissingChildrenBinaryTree(),
                        List.of(4, 6, 5, 2, 7, 3, 1)
                )
        );
    }
}
