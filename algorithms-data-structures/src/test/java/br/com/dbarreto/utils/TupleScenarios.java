package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.tuple.Tuple;
import br.com.dbarreto.datastructure.tuple.impl.RelativeTuple;

/**
 * Utility class for creating various tuple scenarios for testing purposes.
 */
public class TupleScenarios {

    private TupleScenarios() {}

    /**
     * Creates an empty tuple.
     *
     * @return an empty {@link RelativeTuple}
     */
    public static Tuple<Integer> createEmptyTuple() {
        return new RelativeTuple<>();
    }

    /**
     * Creates a tuple with a predefined set of integer values.
     *
     * @return a {@link RelativeTuple} populated with integers from 1 to 10
     */
    public static Tuple<Integer> createNormalTuple() {
        return new RelativeTuple<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
