package br.com.dbarreto.utils;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

import br.com.dbarreto.datastructure.node.impl.SimpleBinaryTreeNode;
import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.datastructure.tree.builder.SimpleBinaryTreeBuilder;
import br.com.dbarreto.datastructure.tree.impl.SimpleBinaryTree;

public class BinaryTreeScenarios {

    public static BinaryTree<Integer> createEmptyBinaryTree() {
        return new SimpleBinaryTree<>();
    }

    public static BinaryTree<Integer> createPerfectBinaryTree() {
        return new SimpleBinaryTreeBuilder<>(1)
                .root()
                    .left(2)
                        .left(4).end()
                        .right(5).end()
                    .end()
                    .right(3)
                        .left(6).end()
                        .right(7).end()
                    .end()
                .end()
                .build();
    }

    public static BinaryTree<Integer> createMissingChildrenBinaryTree() {
        return new SimpleBinaryTreeBuilder<>(1)
                .root()
                    .left(2)
                        .left(4).end()
                        .right(5)
                            .left(6).end()
                        .end()
                    .end()
                    .right(3)
                        .right(7).end()
                    .end()
                .end()
                .build();
    }

    public static BinaryTree<Integer> createLeftUnbalancedBinaryTree() {
        return new SimpleBinaryTreeBuilder<>(1)
                .root()
                    .left(2)
                        .left(4)
                            .left(8)
                                .left(10).end()
                                .right(11).end()
                            .end()
                        .end()
                        .right(5).end()
                    .end()
                    .right(3)
                        .left(6).end()
                        .right(7).end()
                    .end()
                .end()
                .build();
    }

    public static BinaryTree<Integer> createRightUnbalancedBinaryTree() {
        return new SimpleBinaryTreeBuilder<>(1)
                .root()
                    .left(2)
                        .left(4).end()
                        .right(5).end()
                    .end()
                    .right(3)
                        .left(6).end()
                        .right(7)
                            .left(8).end()
                            .right(9)
                                .right(10).end()
                                .left(12)
                                    .right(13).end()
                                .end()
                            .end()
                        .end()
                    .end()
                .end()
                .build();
    }

    public static BinaryTree<Integer> createBigBinaryTree() {

        record Pair(SimpleBinaryTreeNode<Integer> node, Integer level) {}

        var random = new Random();
        Queue<Pair> queue = new ArrayDeque<>();
        var root = new SimpleBinaryTreeNode<>(1);
        queue.add(new Pair(root, 1));

        int maxLevels = 10; // total elements = 2^(maxLevels) - 1
        while (!queue.isEmpty()) {
            var pair = queue.poll();
            var node = pair.node();
            var currentLevel = pair.level() + 1;

            if (currentLevel > maxLevels) {
                break;
            }

            var left = new SimpleBinaryTreeNode<>(random.nextInt());
            var right = new SimpleBinaryTreeNode<>(random.nextInt());

            node.setLeft(left);
            node.setRight(right);

            queue.add(new Pair(left, currentLevel));
            queue.add(new Pair(right, currentLevel));
        }

        return new SimpleBinaryTree<>(root);
    }

    public static BinaryTree<Integer> createSimpleBinaryTree() {
        return new SimpleBinaryTreeBuilder<>(200)
                .root()
                    .left(5).end()
                    .right(3).end()
                .end()
                .build();
    }

    public static BinaryTree<Integer> createBstBinaryTree() {
        return new SimpleBinaryTreeBuilder<>(20)
                .root()
                    .left(17)
                        .left(15)
                            .left(12).end()
                        .end()
                        .right(28)
                            .right(35).end()
                        .end()
                    .end()
                    .right(38)
                    .end()
                .end()
                .build();
    }

    public static BinaryTree<Integer> createPerfectBstTree() {
        return new SimpleBinaryTreeBuilder<>(4)
                .root()
                    .left(2)
                        .left(1).end()
                        .right(3).end()
                    .end()
                    .right(6)
                        .left(5).end()
                        .right(7).end()
                    .end()
                .end()
                .build();
    }
}
