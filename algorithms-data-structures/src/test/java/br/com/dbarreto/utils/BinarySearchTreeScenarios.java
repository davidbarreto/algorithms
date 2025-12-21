package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.tree.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.impl.AvlTree;
import br.com.dbarreto.datastructure.tree.impl.SimpleBinarySearchTree;

public class BinarySearchTreeScenarios {

    public static BinarySearchTree<Integer> createEmptyBst() {
        return new SimpleBinarySearchTree<>();
    }

    public static BinarySearchTree<Integer> createBstWithOrderedInserts() {
        var tree = new SimpleBinarySearchTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        return tree;
    }

    public static BinarySearchTree<Integer> createBstWithUnorderedInserts() {
        var tree = new SimpleBinarySearchTree<Integer>();
        tree.insert(2);
        tree.insert(0);
        tree.insert(3);
        tree.insert(-1);
        tree.insert(5);
        tree.insert(1);
        tree.insert(4);
        tree.insert(-3);
        tree.insert(10);
        tree.insert(-100);
        return tree;
    }

    public static BinarySearchTree<Integer> createAvlTree() {
        var tree = new AvlTree<Integer>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        return tree;
    }
}
