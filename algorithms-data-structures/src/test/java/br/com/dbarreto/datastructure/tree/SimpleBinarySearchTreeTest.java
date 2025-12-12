package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.testutils.BinarySearchTreeFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBinarySearchTreeTest {

    @ParameterizedTest
    @MethodSource("insertArguments")
    void insert(SimpleBinarySearchTree<Integer> tree, List<Integer> expected) {
        assertTree(tree, expected);
    }

    @ParameterizedTest
    @MethodSource("deleteArguments")
    void delete(Integer valueToDelete, List<Integer> expected) {
        var tree = BinarySearchTreeFactory.createBstWithUnorderedInserts();
        tree.delete(valueToDelete);
        assertTree(tree, expected);
    }

    @ParameterizedTest
    @MethodSource("minArguments")
    void min(BinarySearchTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.min());
    }

    @ParameterizedTest
    @MethodSource("maxArguments")
    void max(BinarySearchTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.max());
    }

    @ParameterizedTest
    @MethodSource("rootArguments")
    void root(SimpleBinarySearchTree<Integer> tree, Integer expected) {
        assertEquals(expected, tree.root() != null ? tree.root().value() : null);
    }

    @ParameterizedTest
    @MethodSource("containsArguments")
    void contains(Integer searchedVal, boolean expected) {
        var tree = BinarySearchTreeFactory.createBstWithUnorderedInserts();
        assertEquals(expected, tree.contains(searchedVal));
    }

    private void assertTree(BinaryTree<Integer> tree, List<Integer> expected) {
        var actual = new ArrayList<>();
        tree.traverseInOrder(actual::add);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> insertArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeFactory.createEmptyBst(), Collections.emptyList()),
                Arguments.of(BinarySearchTreeFactory.createBstWithOrderedInserts(), List.of(1, 2, 3, 4, 5)),
                Arguments.of(BinarySearchTreeFactory.createBstWithUnorderedInserts(), List.of(-100, -3, -1, 0, 1, 2, 3, 4, 5, 10))
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
                Arguments.of(BinarySearchTreeFactory.createEmptyBst(), null),
                Arguments.of(BinarySearchTreeFactory.createBstWithOrderedInserts(), 1),
                Arguments.of(BinarySearchTreeFactory.createBstWithUnorderedInserts(), -100)
        );
    }

    private static Stream<Arguments> maxArguments() {
        return Stream.of(
                Arguments.of(BinarySearchTreeFactory.createEmptyBst(), null),
                Arguments.of(BinarySearchTreeFactory.createBstWithOrderedInserts(), 5),
                Arguments.of(BinarySearchTreeFactory.createBstWithUnorderedInserts(), 10)
        );
    }

    private static Stream<Arguments> rootArguments() {
        return Stream.of(
            Arguments.of(BinarySearchTreeFactory.createEmptyBst(), null),
            Arguments.of(BinarySearchTreeFactory.createBstWithOrderedInserts(), 1),
            Arguments.of(BinarySearchTreeFactory.createBstWithUnorderedInserts(), 2)
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
}