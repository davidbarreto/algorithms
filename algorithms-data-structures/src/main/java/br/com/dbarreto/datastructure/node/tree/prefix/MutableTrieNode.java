package br.com.dbarreto.datastructure.node.tree.prefix;

/**
 * Represents a mutable {@link TrieNode}.
 * <p>
 * This interface extends {@link TrieNode} to provide methods for modifying the node's state,
 * such as marking it as a word end, adding children, or removing children.
 */
public interface MutableTrieNode extends TrieNode {

    /**
     * Retrieves the mutable child node associated with the given character.
     *
     * @param ch the character to look up.
     * @return the child {@link MutableTrieNode} associated with the character, or {@code null} if not found.
     */
    MutableTrieNode getMutable(Character ch);

    /**
     * Retrieves the child node associated with the given character.
     * <p>
     * This default implementation delegates to {@link #getMutable(Character)}.
     *
     * @param ch the character to look up.
     * @return the child {@link TrieNode} associated with the character, or {@code null} if not found.
     */
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
     * @param node the child node to add.
     */
    void put(Character ch, MutableTrieNode node);

    /**
     * Removes the child node associated with the given character.
     *
     * @param ch the character of the child to remove.
     */
    void remove(Character ch);
}
