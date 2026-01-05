package br.com.dbarreto.datastructure.tuple;

import br.com.dbarreto.utils.TupleScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TupleTest {

    @ParameterizedTest
    @MethodSource("tupleGetSpecificElementArguments")
    void testGetSpecificElement(Tuple<Integer> tuple, int index, int expected) {
        assertThat(tuple.get(index)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("testGetSpecificElementOutOfRange")
    void testGetSpecificElementOutOfRange(Tuple<Integer> tuple, int index) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.get(index));
    }

    @ParameterizedTest
    @MethodSource("tupleSliceExpressionArguments")
    void testSliceExpression(Tuple<Integer> tuple, String expression, Integer[] expected) {
        assertThat(tuple.slice(expression)).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("tupleSliceExpressionOutOfRangeArguments")
    void testSliceExpressionOutOfRange(Tuple<Integer> tuple, String expression) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.slice(expression));
    }

    @ParameterizedTest
    @MethodSource("tupleSliceExpressionInvalidFormatArguments")
    void testSliceExpressionInvalidFormat(Tuple<Integer> tuple, String expression) {
        assertThrows(NumberFormatException.class, () -> tuple.slice(expression));
    }

    @ParameterizedTest
    @MethodSource("tupleSliceFromArguments")
    void testSliceFrom(Tuple<Integer> tuple, int from, Integer[] expected) {
        assertThat(tuple.sliceFrom(from)).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("tupleSliceFromOutOfRangeArguments")
    void testSliceFromOutOfRange(Tuple<Integer> tuple, int from) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.sliceFrom(from));
    }

    @ParameterizedTest
    @MethodSource("tupleSliceToArguments")
    void testSliceTo(Tuple<Integer> tuple, int to, Integer[] expected) {
        assertThat(tuple.sliceTo(to)).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("tupleSliceToOutOfRangeArguments")
    void testSliceToOutOfRange(Tuple<Integer> tuple, int to) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.sliceTo(to));
    }

    @ParameterizedTest
    @MethodSource("tupleSliceArguments")
    void testSlice(Tuple<Integer> tuple, int from, int to, Integer[] expected) {
        assertThat(tuple.slice(from, to)).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("tupleSliceOutOfRangeArguments")
    void testSliceOutOfRange(Tuple<Integer> tuple, int from, int to) {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> tuple.slice(from, to));
    }

    @Test
    void testReverse() {
        Tuple<Integer> tuple = TupleScenarios.createNormalTuple();
        Tuple<Integer> reversed = tuple.reverse();
        assertThat(reversed).containsExactly(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        
        Tuple<Integer> empty = TupleScenarios.createEmptyTuple();
        assertThat(empty.reverse()).isEmpty();
    }

    @Test
    void testSize() {
        assertThat(TupleScenarios.createNormalTuple().size()).isEqualTo(10);
        assertThat(TupleScenarios.createEmptyTuple().size()).isZero();
    }

    @ParameterizedTest
    @MethodSource("tupleIsEmptyArguments")
    void testIsEmpty(Tuple<Integer> tuple, boolean expected) {
        assertThat(tuple.isEmpty()).isEqualTo(expected);
    }

    @Test
    void testIterator() {
        Tuple<Integer> tuple = TupleScenarios.createNormalTuple();
        Iterator<Integer> iterator = tuple.iterator();
        
        int expected = 1;
        while(iterator.hasNext()) {
            assertThat(iterator.next()).isEqualTo(expected++);
        }
        assertThat(expected).isEqualTo(11);
    }

    @ParameterizedTest
    @MethodSource("tupleToStringArguments")
    void testToString(Tuple<Integer> tuple, String expected) {
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
