package br.com.dbarreto.datastructure.node.tree.prefix;

/**
 * Represents a mutable {@link TrieNode}.
 * <p>
 * This interface provides methods to modify the node's state, such as changing
 * its word status or altering its children.
 */
public interface MutableTrieNode extends TrieNode {

    MutableTrieNode getMutable(Character ch);

    @Override
    default TrieNode get(Character ch) {
        return getMutable(ch);
    }

    /**
     * Sets whether the path to this node represents a complete word.
     *
     * @param isWord {@code true} to mark it as a word, {@code false} otherwise.
     */
    void setWord(boolean isWord);

    /**
     * Adds or replaces a child node for a given character.
     *
     * @param ch   the character representing the child link.
     * @param node the child node.
     */
    void put(Character ch, MutableTrieNode node);

    /**
     * Removes the child node associated with the given character.
     *
     * @param ch the character of the child to remove.
     */
    void remove(Character ch);
}
