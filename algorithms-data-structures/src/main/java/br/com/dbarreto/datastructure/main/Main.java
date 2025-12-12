package br.com.dbarreto.datastructure.main;

import br.com.dbarreto.datastructure.tree.BinaryTree;
import br.com.dbarreto.datastructure.tree.builder.SimpleBinaryTreeBuilder;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new SimpleBinaryTreeBuilder<>(1)
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

        tree.traverseInOrder(System.out::println);
    }
}
