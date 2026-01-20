package br.com.dbarreto.datastructure.tree.prefix;

import br.com.dbarreto.datastructure.node.tree.prefix.MutableTrieNode;
import br.com.dbarreto.datastructure.node.tree.prefix.impl.ArrayTrieNode;
import br.com.dbarreto.datastructure.node.tree.prefix.impl.MapTrieNode;
import br.com.dbarreto.datastructure.tree.prefix.impl.StandardTrie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TrieTest {

    static Stream<Supplier<MutableTrieNode>> nodeSuppliers() {
        return Stream.of(
                MapTrieNode::new,
                ArrayTrieNode::new
        );
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should add words correctly")
    void shouldAddWords(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
        assertThat(trie.add("shells")).isTrue();
        assertThat(trie.add("shell")).isTrue();
        assertThat(trie.add("she")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should not add duplicate words")
    void shouldNotAddDuplicateWords(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
        trie.add("shell");
        assertThat(trie.add("shell")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should check if contains word")
    void shouldCheckContains(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
        trie.add("she");
        trie.add("shell");

        assertThat(trie.contains("she")).isTrue();
        assertThat(trie.contains("shell")).isTrue();
        assertThat(trie.contains("shells")).isFalse();
        assertThat(trie.contains("sh")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should check if starts with prefix")
    void shouldCheckStartsWith(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
        trie.add("shell");

        assertThat(trie.startsWith("she")).isTrue();
        assertThat(trie.startsWith("shell")).isTrue();
        assertThat(trie.startsWith("s")).isTrue();
        assertThat(trie.startsWith("shells")).isFalse();
        assertThat(trie.startsWith("sh")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should return correct size")
    void shouldReturnCorrectSize(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
        assertThat(trie.size()).isZero();

        trie.add("she");
        assertThat(trie.size()).isEqualTo(1);

        trie.add("shell");
        assertThat(trie.size()).isEqualTo(2);

        trie.remove("she");
        assertThat(trie.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should return correct height")
    void shouldReturnCorrectHeight(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
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
    @MethodSource("nodeSuppliers")
    @DisplayName("Should get all words with prefix")
    void shouldGetAllWithPrefix(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
        trie.add("she");
        trie.add("shell");
        trie.add("shells");
        trie.add("sea");

        assertThat(trie.getAllWithPrefix("she"))
                .containsExactlyInAnyOrder("she", "shell", "shells");

        assertThat(trie.getAllWithPrefix("se"))
                .containsExactlyInAnyOrder("sea");

        assertThat(trie.getAllWithPrefix("z"))
                .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("nodeSuppliers")
    @DisplayName("Should remove words correctly")
    void shouldRemoveWords(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
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
    @MethodSource("nodeSuppliers")
    @DisplayName("Should not remove non-existent words")
    void shouldNotRemoveNonExistentWords(Supplier<MutableTrieNode> nodeSupplier) {
        Trie trie = new StandardTrie(nodeSupplier);
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
        Trie trie = new StandardTrie(MapTrieNode::new);
        assertThatThrownBy(() -> trie.add(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word cannot be null nor empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw exception when removing null or empty word")
    void shouldThrowExceptionWhenRemovingInvalidWord(String word) {
        // We can use any implementation here since the validation is in the Trie class
        Trie trie = new StandardTrie(MapTrieNode::new);
        assertThatThrownBy(() -> trie.remove(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word cannot be null nor empty");
    }
}
