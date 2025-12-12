package br.com.dbarreto.datastructure.testutils;

import br.com.dbarreto.datastructure.tree.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.SimpleBinarySearchTree;

public class BinarySearchTreeFactory {

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

    public static void main(String[] args) {
        var tree = createBstWithUnorderedInserts();
        System.out.println(tree.toString());
    }
}
