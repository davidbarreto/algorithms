package br.com.dbarreto.datastructure.tree.prefix;

import br.com.dbarreto.datastructure.tree.prefix.impl.StandardTrie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link Trie} implementations.
 * <p>
 * These tests verify the behavior of {@link StandardTrie} with different Node implementations.
 */
class TrieTest {

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should add words correctly")
    void shouldAddWords(Trie trie) {
        assertThat(trie.add("shells")).isTrue();
        assertThat(trie.add("shell")).isTrue();
        assertThat(trie.add("she")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should not add duplicate words")
    void shouldNotAddDuplicateWords(Trie trie) {
        trie.add("shell");
        assertThat(trie.add("shell")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should check if contains word")
    void shouldCheckContains(Trie trie) {
        trie.add("she");
        trie.add("shell");

        assertThat(trie.contains("she")).isTrue();
        assertThat(trie.contains("shell")).isTrue();
        assertThat(trie.contains("shells")).isFalse();
        assertThat(trie.contains("sh")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should check if starts with prefix")
    void shouldCheckStartsWith(Trie trie) {
        trie.add("shell");

        assertThat(trie.startsWith("she")).isTrue();
        assertThat(trie.startsWith("shell")).isTrue();
        assertThat(trie.startsWith("s")).isTrue();
        assertThat(trie.startsWith("shells")).isFalse();
        assertThat(trie.startsWith("sh")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should return correct size")
    void shouldReturnCorrectSize(Trie trie) {
        assertThat(trie.size()).isZero();

        trie.add("she");
        assertThat(trie.size()).isEqualTo(1);

        trie.add("shell");
        assertThat(trie.size()).isEqualTo(2);

        trie.remove("she");
        assertThat(trie.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should return correct height")
    void shouldReturnCorrectHeight(Trie trie) {
        assertThat(trie.height()).isEqualTo(-1);

        trie.add("she");
        assertThat(trie.height()).isEqualTo(3);

        trie.add("shells");
        assertThat(trie.height()).isEqualTo(6);

        trie.remove("shells");
        assertThat(trie.height()).isEqualTo(3);

        trie.remove("she");
        assertThat(trie.height()).isEqualTo(-1);
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should get all words with prefix")
    void shouldGetAllWordsWithPrefix(Trie trie) {
        trie.add("she");
        trie.add("shell");
        trie.add("shells");
        trie.add("sea");

        assertThat(trie.wordsWithPrefix("she"))
                .containsExactlyInAnyOrder("she", "shell", "shells");

        assertThat(trie.wordsWithPrefix("se"))
                .containsExactlyInAnyOrder("sea");

        assertThat(trie.wordsWithPrefix("z"))
                .isEmpty();

        assertThat(trie.wordsWithPrefix(null))
                .isEmpty();

        assertThat(trie.wordsWithPrefix(""))
                .containsExactlyInAnyOrder("sea", "she", "shell", "shells");
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should get all words with prefix")
    void shouldGetCorrectLongestPrefix(Trie trie) {
        trie.add("she");
        trie.add("shell");
        trie.add("shells");
        trie.add("sea");

        assertThat(trie.longestPrefixOf("she")).isEqualTo("she");
        assertThat(trie.longestPrefixOf("shel")).isEqualTo("she");
        assertThat(trie.longestPrefixOf("shell")).isEqualTo("shell");
        assertThat(trie.longestPrefixOf("shells")).isEqualTo("shells");
        assertThat(trie.longestPrefixOf("shellscript")).isEqualTo("shells");
        assertThat(trie.longestPrefixOf("seaside")).isEqualTo("sea");
        assertThat(trie.longestPrefixOf("banana")).isEmpty();
        assertThat(trie.longestPrefixOf("s")).isEmpty();

    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should remove words correctly")
    void shouldRemoveWords(Trie trie) {
        trie.add("shell");
        trie.add("she");

        assertThat(trie.remove("shell")).isTrue();
        assertThat(trie.contains("shell")).isFalse();
        assertThat(trie.contains("she")).isTrue();

        assertThat(trie.remove("she")).isTrue();
        assertThat(trie.contains("she")).isFalse();
        assertThat(trie.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("trieNodeImplementations")
    @DisplayName("Should not remove non-existent words")
    void shouldNotRemoveNonExistentWords(Trie trie) {
        trie.add("shell");

        assertThat(trie.remove("she")).isFalse();
        assertThat(trie.remove("shells")).isFalse();
        assertThat(trie.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw exception when adding null or empty word")
    void shouldThrowExceptionWhenAddingInvalidWord(String word) {
        // We can use any implementation here since the validation is in the Trie class
        Trie trie = StandardTrie.newTrieWithMapNode();
        assertThatThrownBy(() -> trie.add(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word cannot be null nor empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw exception when removing null or empty word")
    void shouldThrowExceptionWhenRemovingInvalidWord(String word) {
        // We can use any implementation here since the validation is in the Trie class
        Trie trie = StandardTrie.newTrieWithMapNode();
        assertThatThrownBy(() -> trie.remove(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word cannot be null nor empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw exception when removing null or empty word")
    void shouldThrowExceptionWhenGettingLongestPrefixOfInvalidWord(String word) {
        // We can use any implementation here since the validation is in the Trie class
        Trie trie = StandardTrie.newTrieWithMapNode();
        assertThatThrownBy(() -> trie.longestPrefixOf(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word cannot be null nor empty");
    }

    static Stream<Trie> trieNodeImplementations() {
        return Stream.of(
                StandardTrie.newTrieWithArrayNode(),
                StandardTrie.newTrieWithMapNode()
        );
    }
}
