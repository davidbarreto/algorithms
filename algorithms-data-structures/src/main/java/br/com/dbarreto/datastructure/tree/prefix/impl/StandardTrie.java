package br.com.dbarreto.datastructure.tree.prefix.impl;

import br.com.dbarreto.datastructure.node.tree.prefix.TrieNode;
import br.com.dbarreto.datastructure.tree.prefix.Trie;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A standard implementation of the {@link Trie} interface.
 * <p>
 * This implementation supports flexible node storage strategies via a {@link Supplier} for {@link MutableTrieNode}.
 * It maintains the size and height of the trie dynamically as words are added or removed.
 */
public class StandardTrie implements Trie {

    private final MutableTrieNode root;
    private final Supplier<MutableTrieNode> nodeSupplier;
    private int size;
    private int height;

    /**
     * Constructs a new {@code StandardTrie} using the provided node supplier.
     *
     * @param nodeSupplier a supplier that creates new instances of {@link MutableTrieNode}.
     */
    private StandardTrie(Supplier<MutableTrieNode> nodeSupplier) {
        this.nodeSupplier = nodeSupplier;
        this.root = this.nodeSupplier.get();
        this.size = 0;
        this.height = -1;
    }

    public static Trie newTrieWithArrayNode() {
        return new StandardTrie(ArrayTrieNode::new);
    }

    public static Trie newTrieWithMapNode() {
        return new StandardTrie(MapTrieNode::new);
    }

    @Override
    public boolean add(CharSequence word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null nor empty");
        }

        MutableTrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            MutableTrieNode child = node.getMutable(ch);
            if (child == null) {
                child = this.nodeSupplier.get();
                node.put(ch, child);
            }
            node = child;
        }

        if (node.isWord()) {
            return false;
        }

        node.setWord(true);
        this.size++;
        this.height = Math.max(this.height, word.length());
        return true;
    }

    @Override
    public boolean remove(CharSequence word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null nor empty");
        }

        boolean[] removed = new boolean[1];
        remove(word, 0, root, removed);

        if (removed[0]) {
            size--;
            if (size == 0) {
                height = -1;
            } else if (this.height == word.length()) {
                height = height(root);
            }
        }

        return removed[0];
    }

    @Override
    public boolean contains(CharSequence word) {
        var node = traverse(word);
        return node != null && node.isWord();
    }

    @Override
    public boolean startsWith(CharSequence prefix) {
        var node = traverse(prefix);
        return node != null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public Iterable<String> wordsWithPrefix(CharSequence prefix) {
        var node = traverse(prefix);
        if (node == null) {
            return Collections.emptyList();
        }

        StringBuilder sb = new StringBuilder(prefix);
        List<String> result = new ArrayList<>();
        wordsWithPrefix(node, sb, result);
        return result;
    }

    @Override
    public String longestPrefixOf(CharSequence word) {

        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null nor empty");
        }

        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        TrieNode node = this.root;
        for (int i = 0; i < word.length() && node != null; i++) {
            char ch = word.charAt(i);
            node = node.get(ch);
            temp.append(ch);
            if (node != null && node.isWord()) {
                result.append(temp);
                temp.delete(0, temp.length());
            }
        }

        return result.toString();
    }

    private boolean remove(CharSequence word, int index, MutableTrieNode root, boolean[] removed) {
        if (index == word.length()) {
            if (!root.isWord()) {
                return false;
            }
            root.setWord(false);
            removed[0] = true;

            return root.children().isEmpty();
        }

        char ch = word.charAt(index);
        MutableTrieNode child = root.getMutable(ch);
        if (child == null) {
            return false;
        }

        boolean shouldDeleteChild = remove(word, index + 1, child, removed);

        if (shouldDeleteChild) {
            root.remove(ch);
        }

        return root.children().isEmpty() && !root.isWord();
    }

    private void wordsWithPrefix(TrieNode node, StringBuilder builder, List<String> result) {
        if (node.isWord()) {
            result.add(builder.toString());
        }

        for (Character ch : node.keys()) {
            builder.append(ch);
            wordsWithPrefix(node.get(ch), builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    private TrieNode traverse(CharSequence word) {

        if (word == null) {
            return null;
        }

        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node == null) {
                return null;
            }
            node = node.get(word.charAt(i));
        }

        return node;
    }

    private int height(TrieNode root) {
        if (root == null || root.children().isEmpty()) {
            return 0;
        }

        int h = 0;
        for (TrieNode child : root.children()) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    @Override
    public Iterator<String> iterator() {
        return wordsWithPrefix("").iterator();
    }

    /**
     * Represents a mutable {@link TrieNode}.
     * <p>
     * This interface extends {@link TrieNode} to provide methods for modifying the node's state,
     * such as marking it as a word end, adding children, or removing children.
     */
    private interface MutableTrieNode extends TrieNode {

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

    private static class ArrayTrieNode implements MutableTrieNode {

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

    private static class MapTrieNode implements MutableTrieNode {

        private final Map<Character, MutableTrieNode> children;
        private boolean isWord;

        /**
         * Constructs a new {@code MapTrieNode} with no children and not marked as a word.
         */
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
        public Collection<Character> keys() {
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
}
