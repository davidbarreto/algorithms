package br.com.dbarreto.datastructure.tuple;

import br.com.dbarreto.utils.TupleScenarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link Tuple} implementations.
 */
class TupleTest {

    /**
     * Tests getting a specific element by index.
     */
    @ParameterizedTest
    @MethodSource("tupleGetSpecificElementArguments")
    @DisplayName("Should get specific element by index")
    void shouldGetSpecificElement(Tuple<Integer> tuple, int index, int expected) {
        assertThat(tuple.get(index)).isEqualTo(expected);
    }

    /**
     * Tests that getting an element with an out-of-range index throws an exception.
     */
    @ParameterizedTest
    @MethodSource("testGetSpecificElementOutOfRange")
    @DisplayName("Should throw exception when getting element out of range")
    void shouldThrowExceptionWhenGettingElementOutOfRange(Tuple<Integer> tuple, int index) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.get(index));
    }

    /**
     * Tests slicing a tuple using a string expression (e.g., "1:4").
     */
    @ParameterizedTest
    @MethodSource("tupleSliceExpressionArguments")
    @DisplayName("Should slice tuple using string expression")
    void shouldSliceUsingExpression(Tuple<Integer> tuple, String expression, Integer[] expected) {
        assertThat(tuple.slice(expression)).containsExactly(expected);
    }

    /**
     * Tests that slicing with an out-of-range expression throws an exception.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceExpressionOutOfRangeArguments")
    @DisplayName("Should throw exception when slicing with out of range expression")
    void shouldThrowExceptionWhenSlicingWithOutOfRangeExpression(Tuple<Integer> tuple, String expression) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.slice(expression));
    }

    /**
     * Tests that slicing with an invalid format expression throws an exception.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceExpressionInvalidFormatArguments")
    @DisplayName("Should throw exception when slicing with invalid format expression")
    void shouldThrowExceptionWhenSlicingWithInvalidFormatExpression(Tuple<Integer> tuple, String expression) {
        assertThrows(NumberFormatException.class, () -> tuple.slice(expression));
    }

    /**
     * Tests slicing from a specific index to the end.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceFromArguments")
    @DisplayName("Should slice tuple from index")
    void shouldSliceFromIndex(Tuple<Integer> tuple, int from, Integer[] expected) {
        assertThat(tuple.sliceFrom(from)).containsExactly(expected);
    }

    /**
     * Tests that slicing from an out-of-range index throws an exception.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceFromOutOfRangeArguments")
    @DisplayName("Should throw exception when slicing from out of range index")
    void shouldThrowExceptionWhenSlicingFromOutOfRangeIndex(Tuple<Integer> tuple, int from) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.sliceFrom(from));
    }

    /**
     * Tests slicing from the beginning to a specific index.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceToArguments")
    @DisplayName("Should slice tuple to index")
    void shouldSliceToIndex(Tuple<Integer> tuple, int to, Integer[] expected) {
        assertThat(tuple.sliceTo(to)).containsExactly(expected);
    }

    /**
     * Tests that slicing to an out-of-range index throws an exception.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceToOutOfRangeArguments")
    @DisplayName("Should throw exception when slicing to out of range index")
    void shouldThrowExceptionWhenSlicingToOutOfRangeIndex(Tuple<Integer> tuple, int to) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.sliceTo(to));
    }

    /**
     * Tests slicing between two indices.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceArguments")
    @DisplayName("Should slice tuple between indices")
    void shouldSliceBetweenIndices(Tuple<Integer> tuple, int from, int to, Integer[] expected) {
        assertThat(tuple.slice(from, to)).containsExactly(expected);
    }

    /**
     * Tests that slicing with out-of-range indices throws an exception.
     */
    @ParameterizedTest
    @MethodSource("tupleSliceOutOfRangeArguments")
    @DisplayName("Should throw exception when slicing with out of range indices")
    void shouldThrowExceptionWhenSlicingWithOutOfRangeIndices(Tuple<Integer> tuple, int from, int to) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.slice(from, to));
    }

    /**
     * Tests reversing a tuple.
     */
    @Test
    @DisplayName("Should reverse tuple")
    void shouldReverseTuple() {
        Tuple<Integer> tuple = TupleScenarios.createNormalTuple();
        Tuple<Integer> reversed = tuple.reverse();
        assertThat(reversed).containsExactly(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        
        Tuple<Integer> empty = TupleScenarios.createEmptyTuple();
        assertThat(empty.reverse()).isEmpty();
    }

    /**
     * Tests the {@code size} method.
     */
    @Test
    @DisplayName("Should return correct size")
    void shouldReturnCorrectSize() {
        assertThat(TupleScenarios.createNormalTuple().size()).isEqualTo(10);
        assertThat(TupleScenarios.createEmptyTuple().size()).isZero();
    }

    /**
     * Tests the {@code isEmpty} method.
     */
    @ParameterizedTest
    @MethodSource("tupleIsEmptyArguments")
    @DisplayName("Should check if tuple is empty")
    void shouldCheckIfEmpty(Tuple<Integer> tuple, boolean expected) {
        assertThat(tuple.isEmpty()).isEqualTo(expected);
    }

    /**
     * Tests the iterator functionality.
     */
    @Test
    @DisplayName("Should iterate over elements")
    void shouldIterateOverElements() {
        Tuple<Integer> tuple = TupleScenarios.createNormalTuple();
        Iterator<Integer> iterator = tuple.iterator();
        
        int expected = 1;
        while(iterator.hasNext()) {
            assertThat(iterator.next()).isEqualTo(expected++);
        }
        assertThat(expected).isEqualTo(11);
    }

    /**
     * Tests the {@code toString} method.
     */
    @ParameterizedTest
    @MethodSource("tupleToStringArguments")
    @DisplayName("Should return correct string representation")
    void shouldReturnCorrectStringRepresentation(Tuple<Integer> tuple, String expected) {
        assertThat(tuple).hasToString(expected);
    }

    static Stream<Arguments> tupleGetSpecificElementArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), 0, 1),
                Arguments.of(TupleScenarios.createNormalTuple(), -10, 1),
                Arguments.of(TupleScenarios.createNormalTuple(), 9, 10),
                Arguments.of(TupleScenarios.createNormalTuple(), -1, 10),
                Arguments.of(TupleScenarios.createNormalTuple(), 4, 5),
                Arguments.of(TupleScenarios.createNormalTuple(), -6, 5)
        );
    }

    static Stream<Arguments> testGetSpecificElementOutOfRange() {
        return Stream.of(
                Arguments.of(TupleScenarios.createEmptyTuple(), 0),
                Arguments.of(TupleScenarios.createNormalTuple(), -11),
                Arguments.of(TupleScenarios.createNormalTuple(), -12),
                Arguments.of(TupleScenarios.createNormalTuple(), -20),
                Arguments.of(TupleScenarios.createNormalTuple(), 10),
                Arguments.of(TupleScenarios.createNormalTuple(), 11),
                Arguments.of(TupleScenarios.createNormalTuple(), 15)
        );
    }

    static Stream<Arguments> tupleIsEmptyArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createEmptyTuple(), true),
                Arguments.of(TupleScenarios.createNormalTuple(), false)
        );
    }

    static Stream<Arguments> tupleToStringArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createEmptyTuple(), "[]"),
                Arguments.of(TupleScenarios.createNormalTuple(), "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]")
        );
    }

    static Stream<Arguments> tupleSliceExpressionArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), "1:4", new Integer[]{2, 3, 4}),
                Arguments.of(TupleScenarios.createNormalTuple(), "0:10", new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), ":5", new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(TupleScenarios.createNormalTuple(), "5:", new Integer[]{6, 7, 8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), "-3:", new Integer[]{8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), ":-3", new Integer[]{1, 2, 3, 4, 5, 6, 7})
        );
    }

    static Stream<Arguments> tupleSliceExpressionOutOfRangeArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), "11:"),
                Arguments.of(TupleScenarios.createNormalTuple(), ":11"),
                Arguments.of(TupleScenarios.createNormalTuple(), "-11:"),
                Arguments.of(TupleScenarios.createNormalTuple(), ":-11")
        );
    }

    static Stream<Arguments> tupleSliceExpressionInvalidFormatArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), "a:b"),
                Arguments.of(TupleScenarios.createNormalTuple(), "1:b"),
                Arguments.of(TupleScenarios.createNormalTuple(), "a:5"),
                Arguments.of(TupleScenarios.createNormalTuple(), " : "),
                Arguments.of(TupleScenarios.createNormalTuple(), "1.5:2.5")
        );
    }

    static Stream<Arguments> tupleSliceFromArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), 0, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), 5, new Integer[]{6, 7, 8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), -3, new Integer[]{8, 9, 10})
        );
    }

    static Stream<Arguments> tupleSliceFromOutOfRangeArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), 10),
                Arguments.of(TupleScenarios.createNormalTuple(), 11),
                Arguments.of(TupleScenarios.createNormalTuple(), -11)
        );
    }

    static Stream<Arguments> tupleSliceToArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), 10, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), 5, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(TupleScenarios.createNormalTuple(), -3, new Integer[]{1, 2, 3, 4, 5, 6, 7}),
                Arguments.of(TupleScenarios.createNormalTuple(), 0, new Integer[]{})
        );
    }

    static Stream<Arguments> tupleSliceToOutOfRangeArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), 11),
                Arguments.of(TupleScenarios.createNormalTuple(), -11)
        );
    }

    static Stream<Arguments> tupleSliceArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), 1, 4, new Integer[]{2, 3, 4}),
                Arguments.of(TupleScenarios.createNormalTuple(), 0, 10, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                Arguments.of(TupleScenarios.createNormalTuple(), 5, 5, new Integer[]{}),
                Arguments.of(TupleScenarios.createNormalTuple(), 5, 4, new Integer[]{}), // from > to returns empty
                Arguments.of(TupleScenarios.createNormalTuple(), -5, -2, new Integer[]{6, 7, 8})
        );
    }

    static Stream<Arguments> tupleSliceOutOfRangeArguments() {
        return Stream.of(
                Arguments.of(TupleScenarios.createNormalTuple(), -11, 5),
                Arguments.of(TupleScenarios.createNormalTuple(), 0, 11),
                Arguments.of(TupleScenarios.createNormalTuple(), 5, 12)
        );
    }
}
