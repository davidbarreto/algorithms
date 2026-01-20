package br.com.dbarreto.datastructure.node.tree.prefix.impl;

import br.com.dbarreto.datastructure.node.tree.prefix.MutableTrieNode;
import br.com.dbarreto.datastructure.node.tree.prefix.TrieNode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of {@link MutableTrieNode} using an array to store children.
 * <p>
 * This implementation assumes a fixed alphabet size (ASCII 256). It provides O(1) access time
 * for children but may consume more memory for sparse nodes compared to map-based implementations.
 */
public class ArrayTrieNode implements MutableTrieNode {

    private static final int SIZE = 256;

    private final MutableTrieNode[] children;
    private boolean isWord;

    /**
     * Constructs a new {@code ArrayTrieNode} with no children and not marked as a word.
     */
    public ArrayTrieNode() {
        this.children = new ArrayTrieNode[SIZE];
        this.isWord = false;
    }

    @Override
    public MutableTrieNode getMutable(Character ch) {
        return this.children[ch];
    }

    @Override
    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    @Override
    public void put(Character ch, MutableTrieNode node) {
        this.children[ch] = node;
    }

    @Override
    public void remove(Character ch) {
        this.children[ch] = null;
    }

    @Override
    public boolean isWord() {
        return this.isWord;
    }

    @Override
    public Collection<Character> keys() {
        return IntStream.range(0, SIZE)
                .filter(i -> this.children[i] != null)
                .mapToObj(i -> (char) i)
                .toList();
    }

    @Override
    public Collection<TrieNode> children() {
        return Arrays.stream(this.children)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
