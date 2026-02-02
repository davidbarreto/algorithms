package br.com.dbarreto.algorithm.sort;

import br.com.dbarreto.utils.SortingScenarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortingTest {

    @ParameterizedTest
    @MethodSource("sortingArguments")
    @DisplayName("Should sort list correctly")
    void shouldSortList(SortingScenarios.SortingScenario<Integer> scenario,
                        BiConsumer<List<Integer>, Comparator<Integer>> sortingAlgorithm)
    {
        sortingAlgorithm.accept(scenario.inputList(), scenario.comparator());
        assertThat(scenario.inputList()).isEqualTo(scenario.expectedList());
    }

    static Stream<Arguments> sortingArguments() {
        return SortingScenarios.allSortingScenarios().stream()
                .flatMap(sortingScenario -> SortingScenarios.sortingImplementations().stream()
                        .map(sortingAlgorithm ->
                                Arguments.of(sortingScenario, sortingAlgorithm)
                        )
                );
    }
}