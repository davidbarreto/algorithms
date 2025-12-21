package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.datastructure.tree.builder.SimpleBinaryTreeBuilder;
import br.com.dbarreto.datastructure.tree.impl.SimpleBinaryTree;

public class BinaryTreeScenarios {

    public static BinaryTree<Integer> createEmptyBinaryTree() {
        return new SimpleBinaryTree<>();
    }

    public static BinaryTree<Integer> createPerfectBinaryTree() {
        return new SimpleBinaryTreeBuilder<Integer>(1)
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
        return new SimpleBinaryTreeBuilder<Integer>(1)
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
        return new SimpleBinaryTreeBuilder<Integer>(1)
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
        return new SimpleBinaryTreeBuilder<Integer>(1)
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
}
