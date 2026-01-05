package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.tuple.Tuple;
import br.com.dbarreto.datastructure.tuple.impl.RelativeTuple;

public class TupleScenarios {

    private TupleScenarios() {}

    public static Tuple<Integer> createEmptyTuple() {
        return new RelativeTuple<>();
    }

    public static Tuple<Integer> createNormalTuple() {
        return new RelativeTuple<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
