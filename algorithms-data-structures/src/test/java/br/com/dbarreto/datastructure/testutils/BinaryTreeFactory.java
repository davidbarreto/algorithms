package br.com.dbarreto.datastructure.testutils;

import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.datastructure.tree.SimpleBinaryTree;
import br.com.dbarreto.datastructure.tree.builder.SimpleBinaryTreeBuilder;

public class BinaryTreeFactory {

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
}
