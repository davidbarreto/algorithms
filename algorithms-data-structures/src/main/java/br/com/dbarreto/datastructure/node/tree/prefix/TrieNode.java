package br.com.dbarreto.datastructure.node.tree.prefix;

import br.com.dbarreto.datastructure.node.tree.nary.NAryTreeNode;

import java.util.Collection;

/**
 * Represents a node in a Trie (Prefix Tree).
 * <p>
 * Each node represents a character in a sequence. A node can mark the end of a valid word
 * and may have children nodes mapped by characters.
 */
public interface TrieNode extends NAryTreeNode<TrieNode> {

    /**
     * Checks if this node marks the end of a word.
     *
     * @return {@code true} if this node represents the end of a word, {@code false} otherwise.
     */
    boolean isWord();

    /**
     * Retrieves the child node associated with the given character.
     *
     * @param ch the character to look up.
     * @return the child {@link TrieNode} associated with the character, or {@code null} if not found.
     */
    TrieNode get(Character ch);

    /**
     * Returns a collection of characters that have outgoing edges from this node.
     *
     * @return a {@link Collection} of characters representing the keys of the children nodes.
     */
    Collection<Character> keys();
}
