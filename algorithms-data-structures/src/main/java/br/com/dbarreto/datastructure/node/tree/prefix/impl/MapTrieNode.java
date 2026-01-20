package br.com.dbarreto.datastructure.node.tree.prefix.impl;

import br.com.dbarreto.datastructure.node.tree.prefix.MutableTrieNode;
import br.com.dbarreto.datastructure.node.tree.prefix.TrieNode;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapTrieNode implements MutableTrieNode {

    private final Map<Character, MutableTrieNode> children;
    private boolean isWord;

    public MapTrieNode() {
        this.isWord = false;
        this.children = new HashMap<>();
    }

    @Override
    public boolean isWord() {
        return this.isWord;
    }

    @Override
    public MutableTrieNode getMutable(Character ch) {
        return this.children.get(ch);
    }

    @Override
    public Collection<Character> prefixes() {
        return Collections.unmodifiableCollection(this.children.keySet());
    }

    @Override
    public Collection<TrieNode> children() {
        return Collections.unmodifiableCollection(this.children.values());
    }

    @Override
    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    @Override
    public void put(Character ch, MutableTrieNode node) {
        this.children.put(ch, node);
    }

    @Override
    public void remove(Character ch) {
        this.children.remove(ch);
    }
}
