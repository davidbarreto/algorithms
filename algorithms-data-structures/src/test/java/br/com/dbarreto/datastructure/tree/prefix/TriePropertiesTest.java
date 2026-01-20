package br.com.dbarreto.datastructure.tree.prefix;

import br.com.dbarreto.datastructure.node.tree.prefix.impl.ArrayTrieNode;
import br.com.dbarreto.datastructure.node.tree.prefix.impl.MapTrieNode;
import br.com.dbarreto.datastructure.tree.prefix.impl.StandardTrie;
import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class TriePropertiesTest {

    @Property
    void shouldContainAllAddedWords(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("words") Set<String> words
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);

        for (String word : words) {
            assertThat(trie.contains(word)).isTrue();
        }
    }

    @Property
    void shouldHaveCorrectSize(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("wordList") List<String> words
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);

        long uniqueCount = words.stream().distinct().count();
        assertThat(trie.size()).isEqualTo((int) uniqueCount);
    }

    @Property
    void shouldRemoveWords(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("words") Set<String> words
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);

        for (String word : words) {
            assertThat(trie.remove(word)).isTrue();
            assertThat(trie.contains(word)).isFalse();
        }
        assertThat(trie.isEmpty()).isTrue();
        assertThat(trie.size()).isZero();
    }

    @Property
    void shouldIdentifyPrefixes(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("words") Set<String> words
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);

        for (String word : words) {
            for (int i = 1; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                assertThat(trie.startsWith(prefix)).isTrue();
            }
        }
    }

    @Property
    void shouldCalculateHeightCorrectly(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("words") Set<String> words
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);

        int expectedHeight = words.stream()
                .mapToInt(String::length)
                .max()
                .orElse(-1);

        if (words.isEmpty()) {
             assertThat(trie.height()).isEqualTo(-1);
        } else {
             assertThat(trie.height()).isEqualTo(expectedHeight);
        }
    }

    @Property
    void shouldRetrieveAllWordsWithPrefix(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("words") Set<String> words,
            @ForAll @AlphaChars @StringLength(min = 1, max = 3) String queryPrefix
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);

        List<String> expected = words.stream()
                .filter(w -> w.startsWith(queryPrefix))
                .toList();

        Iterable<String> actual = trie.getAllWithPrefix(queryPrefix);

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
    
    @Property
    void shouldNotContainWordsNotAdded(
            @ForAll("trieImplementations") TrieFactory factory,
            @ForAll("words") Set<String> words,
            @ForAll("words") Set<String> otherWords
    ) {
        Trie trie = factory.get();
        words.forEach(trie::add);
        
        Set<String> notAdded = new HashSet<>(otherWords);
        notAdded.removeAll(words);
        
        for (String word : notAdded) {
            assertThat(trie.contains(word)).isFalse();
        }
    }

    @Provide
    Arbitrary<TrieFactory> trieImplementations() {
        return Arbitraries.of(
                new TrieFactory("StandardTrie + ArrayTrieNode", () -> new StandardTrie(ArrayTrieNode::new)),
                new TrieFactory("StandardTrie + MapTrieNode", () -> new StandardTrie(MapTrieNode::new))
        );
    }

    @Provide
    Arbitrary<Set<String>> words() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(1)
                .ofMaxLength(20)
                .set()
                .ofMaxSize(50);
    }

    @Provide
    Arbitrary<List<String>> wordList() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(1)
                .ofMaxLength(20)
                .list()
                .ofMaxSize(50);
    }

    record TrieFactory(String name, Supplier<Trie> supplier) {
        Trie get() {
            return supplier.get();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
