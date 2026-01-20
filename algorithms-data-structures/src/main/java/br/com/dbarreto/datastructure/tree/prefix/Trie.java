package br.com.dbarreto.datastructure.tree.prefix;

import br.com.dbarreto.datastructure.tree.Tree;

/**
 * Represents a Trie (also known as a prefix tree), a specialized tree data structure
 * used for storing and retrieving a dynamic set of strings. It is particularly
 * efficient for operations involving prefixes, such as autocomplete and spell checking.
 * <p>
 * Each node in the trie represents a single character. A path from the root to a
 * specific node represents a prefix, and if a node is marked as the end of a word,
 * that path represents a complete word stored in the trie.
 */
public interface Trie extends Tree<CharSequence>, Iterable<String> {

    /**
     * Adds a word to the trie.
     *
     * @param word the word to be added.
     * @return {@code true} if the word was newly added, or {@code false} if the word
     *         already existed in the trie.
     * @throws IllegalArgumentException if the word is null or empty.
     */
    boolean add(CharSequence word);

    /**
     * Removes a word from the trie.
     *
     * @param word the word to be removed.
     * @return {@code true} if the word existed and was successfully removed, or
     *         {@code false} if the word was not found.
     * @throws IllegalArgumentException if the word is null or empty.
     */
    boolean remove(CharSequence word);

    /**
     * Checks if the trie contains the exact specified word.
     * <p>
     * This will return {@code false} for a prefix that is not marked as a complete word.
     * For example, if the trie contains "apple", {@code contains("app")} will return {@code false}.
     *
     * @param word the word to search for.
     * @return {@code true} if the word is in the trie, {@code false} otherwise.
     */
    @Override
    boolean contains(CharSequence word);

    /**
     * Checks if there is any word in the trie that starts with the given prefix.
     * <p>
     * For example, if the trie contains "apple", {@code startsWith("app")} will return {@code true}.
     *
     * @param prefix the prefix to check.
     * @return {@code true} if any word starts with the prefix, {@code false} otherwise.
     */
    boolean startsWith(CharSequence prefix);

    /**
     * Returns the total number of unique words stored in the trie.
     *
     * @return the number of words in the trie.
     */
    @Override
    int size();

    /**
     * Returns the height of the trie, which corresponds to the length of the
     * longest word stored in it.
     * <p>
     * An empty trie has a height of -1.
     *
     * @return the height of the trie.
     */
    @Override
    int height();

    /**
     * Checks if the trie is empty (contains no words).
     *
     * @return {@code true} if the trie is empty, {@code false} otherwise.
     */
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns an {@link Iterable} of all words in the trie that start with the
     * given prefix.
     * <p>
     * The words are not guaranteed to be returned in any specific order.
     * If the prefix is an empty string, all words in the trie will be returned.
     *
     * @param prefix the prefix to search for.
     * @return an {@link Iterable} containing all matching words.
     */
    Iterable<String> wordsWithPrefix(CharSequence prefix);

    /**
     * Returns an {@link String} of the longest prefix of the given word that is in the trie.
     *
     * @param word the word to search for.
     * @return a {@link String} representing the longest prefix of the given word that is in the trie.
     * @throws IllegalArgumentException if the word is null or empty.
     */
    String longestPrefixOf(CharSequence word);
}
