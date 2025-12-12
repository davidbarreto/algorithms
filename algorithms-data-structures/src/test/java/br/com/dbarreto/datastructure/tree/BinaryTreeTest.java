package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.testutils.BinaryTreeFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    @ParameterizedTest
    @MethodSource("sizeArguments")
    void testSize(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.size());
    }

    @ParameterizedTest
    @MethodSource("heightArguments")
    void testHeight(BinaryTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.height());
    }

    @ParameterizedTest
    @MethodSource("containsArguments")
    void testContains(Integer searchedValue, boolean expected) {
        var tree = BinaryTreeFactory.createPerfectBinaryTree();
        assertEquals(expected, tree.contains(searchedValue));
    }

    private static Stream<Arguments> sizeArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeFactory.createEmptyBinaryTree(), 0),
                Arguments.of(BinaryTreeFactory.createPerfectBinaryTree(), 7),
                Arguments.of(BinaryTreeFactory.createMissingChildrenBinaryTree(), 7)
        );
    }

    private static Stream<Arguments> heightArguments() {
        return Stream.of(
                Arguments.of(BinaryTreeFactory.createEmptyBinaryTree(), 0),
                Arguments.of(BinaryTreeFactory.createPerfectBinaryTree(), 3),
                Arguments.of(BinaryTreeFactory.createMissingChildrenBinaryTree(), 4)
        );
    }

    private static Stream<Arguments> containsArguments() {
        return Stream.of(
                Arguments.of(100, false),
                Arguments.of(0, false),
                Arguments.of(-100, false),
                Arguments.of(1, true),
                Arguments.of(7, true)
        );
    }
}