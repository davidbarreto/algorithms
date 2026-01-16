package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.tree.binary.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.datastructure.tree.binary.impl.AvlTree;
import br.com.dbarreto.datastructure.tree.binary.impl.RedBlackTree;
import br.com.dbarreto.datastructure.tree.binary.impl.SimpleBinarySearchTree;

/**
 * Utility class for creating various Binary Search Tree (BST) scenarios for testing purposes.
 * <p>
 * Provides factory methods for creating empty BSTs, BSTs with ordered and unordered insertions,
 * as well as instances of self-balancing trees like AVL and Red-Black Trees.
 * </p>
 */
public class BinarySearchTreeScenarios {

    /**
     * Creates an empty {@link SimpleBinarySearchTree}.
     *
     * @return an empty BST
     */
    public static BinarySearchTree<Integer> createEmptyBst() {
        return new SimpleBinarySearchTree<>();
    }

    /**
     * Creates a {@link SimpleBinarySearchTree} by inserting elements in ascending order,
     * resulting in a completely unbalanced tree (like a linked list).
     *
     * @return an unbalanced BST
     */
    public static BinarySearchTree<Integer> createBstWithOrderedInserts() {
        var tree = new SimpleBinarySearchTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        return tree;
    }

    /**
     * Creates a {@link SimpleBinarySearchTree} by inserting elements in a non-sequential order.
     *
     * @return a BST with a more complex structure
     */
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

    /**
     * Creates and populates an {@link AvlTree}.
     *
     * @return a balanced AVL tree
     */
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

    /**
     * Creates and populates a {@link RedBlackTree}.
     *
     * @return a balanced Red-Black tree
     */
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

    /**
     * Creates a {@link SimpleBinarySearchTree} with a perfect structure.
     *
     * @return a perfectly balanced BST
     */
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

    /**
     * Creates an {@link AvlTree} by inserting elements that would form a perfect BST if not for balancing.
     *
     * @return a balanced AVL tree
     */
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

    /**
     * Creates a {@link RedBlackTree} by inserting elements that would form a perfect BST if not for balancing.
     *
     * @return a balanced Red-Black tree
     */
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

    /**
     * Creates an {@link AvlTree} that requires a Left-Left rotation to balance.
     *
     * @return an AVL tree after a Left-Left rotation
     */
    public static BinaryTree<Integer> createAvlTreeThatTriggersLeftLeftRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);  // Triggers left-left rotation

        return tree;
    }

    /**
     * Creates an {@link AvlTree} that requires a Right-Right rotation to balance.
     *
     * @return an AVL tree after a Right-Right rotation
     */
    public static BinaryTree<Integer> createAvlTreeThatTriggersRightRightRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);  // Triggers right-right rotation
        return tree;
    }

    /**
     * Creates an {@link AvlTree} that requires a Right-Left rotation to balance.
     *
     * @return an AVL tree after a Right-Left rotation
     */
    public static BinaryTree<Integer> createAvlTreeThatTriggersRightLeftRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);  // Triggers left-right rotation
        return tree;
    }

    /**
     * Creates an {@link AvlTree} that requires a Left-Right rotation to balance.
     *
     * @return an AVL tree after a Left-Right rotation
     */
    public static BinaryTree<Integer> createAvlTreeThatTriggersLeftRightRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);  // Triggers right-left rotation
        return tree;
    }
}
