package br.com.dbarreto.utils;

import br.com.dbarreto.algorithm.sort.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

public class SortingScenarios {

    private SortingScenarios() {}

    public static List<SortingScenario<Integer>> allSortingScenarios() {
        return List.of(
                smallListNatualOrderScenario(),
                mediumListNaturalOrderScenario(),
                largeListNaturalOrderScenario(),
                smallListReverseOrderScenario(),
                customComparatorScenario(),
                emptyListScenario(),
                singleElementListScenario()
        );
    }

    public static SortingScenario<Integer> smallListNatualOrderScenario() {
        return new SortingScenario<>(
                Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3),
                Comparator.naturalOrder(),
                List.of(1, 1, 2, 3, 3, 4, 5, 5, 6, 9)
        );
    }

    public static SortingScenario<Integer> mediumListNaturalOrderScenario() {
        return new SortingScenario<>(
                Arrays.asList(20, 11, 18, 2, 15, 13, 1, 19, 6, 8, 9, 4, 12, 5, 17, 3, 14, 7, 10, 16),
                Comparator.naturalOrder(),
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        );
    }

    public static SortingScenario<Integer> largeListNaturalOrderScenario() {
        return new SortingScenario<>(
                Arrays.asList(42, 12, 35, 1, 19, 48, 23, 8, 45, 3, 29, 15, 38, 6, 21, 49, 31, 10, 26, 4, 41, 14, 33, 2, 17, 46, 25, 9, 43, 5, 28, 13, 36, 0, 20, 47, 24, 7, 44, 30, 16, 39, 11, 27, 34, 18, 40, 22, 37, 32),
                Comparator.naturalOrder(),
                List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49)
        );
    }

    public static SortingScenario<Integer> smallListReverseOrderScenario() {
        return new SortingScenario<>(
                Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3),
                Comparator.reverseOrder(),
                List.of(9, 6, 5, 5, 4, 3, 3, 2, 1, 1)
        );
    }

    public static SortingScenario<Integer> customComparatorScenario() {
        return new SortingScenario<>(
                Arrays.asList(15, 2, 41, 33, 14),
                Comparator.comparingInt(i -> i % 10),
                List.of(41, 2, 33, 14, 15)
        );
    }

    public static SortingScenario<Integer> emptyListScenario() {
        return new SortingScenario<>(
                new ArrayList<Integer>(),
                Comparator.naturalOrder(),
                new ArrayList<Integer>()
        );
    }

    public static SortingScenario<Integer> singleElementListScenario() {
        return new SortingScenario<>(
                Arrays.asList(42),
                Comparator.naturalOrder(),
                List.of(42)
        );
    }

    public static SortingScenario<Integer> nullListScenario() {
        return new SortingScenario<>(
                (List<Integer>) null,
                Comparator.naturalOrder(),
                null
        );
    }

    public static <E> List<BiConsumer<List<E>, Comparator<E>>> sortingImplementations() {
        return List.of(
                Sorting::bubbleSort,
                Sorting::selectionSort,
                Sorting::insertionSort,
                Sorting::heapSort
        );
    }

    public record SortingScenario<E>(
            List<E> inputList, Comparator<E> comparator, List<E> expectedList) {
    }
}
