package br.com.dbarreto.datastructure.tuple.impl;

import br.com.dbarreto.datastructure.tuple.Pair;

public record SimplePair<T, U> (T first, U second) implements Pair<T, U> {
    @Override
    public String toString() {
        return "[" + first + "," + second + "]";
    }
}
