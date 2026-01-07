package br.com.dbarreto.algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import br.com.dbarreto.datastructure.graph.Graph;

public class GraphTraversals {

    private GraphTraversals() {}

    public static <V> List<V> breadthFirstSearch(Graph<V> graph, V start) {
        List<V> result = new ArrayList<>();
        breadthFirstSearch(graph, start, (Consumer<V>) result::add);
        return result;
    }

    public static <V> boolean breadthFirstSearch(Graph<V> graph, V start, V target) {
        return search(graph, start, Deque::add, Deque::poll, null, v -> start.equals(target));
    }

    public static <V> boolean breadthFirstSearch(Graph<V> graph, V start, Predicate<V> condition) {
        return search(graph, start, Deque::add, Deque::poll, null, condition);
    }

    public static <V> void breadthFirstSearch(Graph<V> graph, V start, Consumer<V> visitor) {
        search(graph, start, Deque::add, Deque::poll, visitor, null);
    }

    public static <V> List<V> deepFirstSearch(Graph<V> graph, V start) {
        List<V> result = new ArrayList<>();
        breadthFirstSearch(graph, start, (Consumer<V>) result::add);
        return result;
    }

    public static <V> boolean deepFirstSearch(Graph<V> graph, V start, V target) {
        return search(graph, start, Deque::push, Deque::pop, null, v -> v.equals(target));
    }

    public static <V> boolean deepFirstSearch(Graph<V> graph, V start, Predicate<V> condition) {
        return search(graph, start, Deque::push, Deque::pop, null, condition);
    }

    public static <V> void deepFirstSearch(Graph<V> graph, V start, Consumer<V> visitor) {
        search(graph, start, Deque::push, Deque::pop, visitor, null);
    }

    private static <V> boolean search(Graph<V> graph, V start,
        BiConsumer<Deque<V>, V> addConsumer, Function<Deque<V>, V> removeFunction,
        Consumer<V> visitor, Predicate<V> condition)
    {

        Set<V> visited = new HashSet<>();
        Deque<V> container = new ArrayDeque<>();
        addConsumer.accept(container, start);
        visited.add(start);

        while (!container.isEmpty()) {
            V current = removeFunction.apply(container);

            if (condition != null && condition.test(current)) {
                return true;
            } else {
                visitor.accept(current);
            }

            for (V neighbor : graph.neighborsOf(current)) {
                if (visited.add(neighbor)) {
                    addConsumer.accept(container, neighbor);
                }
            }
        }

        return false;
    }

    public static <V> void deepFirstSearchRecursive(Graph<V> graph, V vertex, Consumer<V> visitor) {
        deepFirstSearchRecursive(graph, vertex, visitor, new HashSet<>());
    }

    private static <V> void deepFirstSearchRecursive(Graph<V> graph, V vertex, Consumer<V> visitor, Set<V> visited) {

        if (!visited.add(vertex)) {
            return;
        }

        visitor.accept(vertex);

        for (V neighbor : graph.neighborsOf(vertex)) {
            deepFirstSearchRecursive(graph, neighbor, visitor, visited);
        }
    }
}
