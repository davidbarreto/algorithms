package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.tree.impl.SimpleBinarySearchTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for tree iterators.
 */
class TreeIteratorsTest {

    @Test
    void inOrderIteratorWorks() {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        Iterator<Integer> iterator = tree.inOrderIterator();
        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result).containsExactly(1, 3, 4, 5, 6, 7, 8);
    }

    @Test
    void preOrderIteratorWorks() {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        Iterator<Integer> iterator = tree.preOrderIterator();
        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result).containsExactly(5, 3, 1, 4, 7, 6, 8);
    }

    @Test
    void postOrderIteratorWorks() {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        Iterator<Integer> iterator = tree.postOrderIterator();
        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result).containsExactly(1, 4, 3, 6, 8, 7, 5);
    }

    @Test
    void levelOrderIteratorWorks() {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        Iterator<Integer> iterator = tree.levelOrderIterator();
        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result).containsExactly(5, 3, 7, 1, 4, 6, 8);
    }

    @Test
    void emptyTreeIterators() {
        SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>();

        assertThat(tree.inOrderIterator().hasNext()).isFalse();
        assertThat(tree.preOrderIterator().hasNext()).isFalse();
        assertThat(tree.postOrderIterator().hasNext()).isFalse();
        assertThat(tree.levelOrderIterator().hasNext()).isFalse();
    }
}