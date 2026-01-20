package br.com.dbarreto.datastructure.node.tree.prefix;

import br.com.dbarreto.datastructure.node.tree.nary.NAryTreeNode;

import java.util.Collection;

public interface TrieNode extends NAryTreeNode<TrieNode> {
    boolean isWord();
    TrieNode get(Character ch);
    Collection<Character> prefixes();
}
