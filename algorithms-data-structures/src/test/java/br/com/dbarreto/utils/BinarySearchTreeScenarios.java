package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.tree.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.datastructure.tree.impl.AvlTree;
import br.com.dbarreto.datastructure.tree.impl.RedBlackTree;
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

    public static BinarySearchTree<Integer> createRedBlackTree() {
        var tree = new RedBlackTree<Integer>();
        tree.insert(300);
        tree.insert(-23);
        tree.insert(Integer.MIN_VALUE);
        tree.insert(40);
        tree.insert(230);
        tree.insert(-234);

        return tree;
    }

    public static BinaryTree<Integer> createSimpleBstWithPerfectStructure() {
        var tree = new SimpleBinarySearchTree<Integer>();
        // Insert in order to match the BST structure: 4,2,6,1,3,5,7
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        return tree;
    }

    public static BinaryTree<Integer> createAvlTreeWithPerfectInserts() {
        var tree = new AvlTree<Integer>();
        // Insert in same order, but AVL will balance, so structure differs
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        return tree;
    }

    public static BinaryTree<Integer> createRedBlackTreeWithPerfectInserts() {
        var tree = new RedBlackTree<Integer>();
        // Insert in same order, but Red-Black will balance, so structure differs
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        return tree;
    }
}
