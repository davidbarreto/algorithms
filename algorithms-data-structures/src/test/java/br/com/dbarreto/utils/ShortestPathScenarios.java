package br.com.dbarreto.utils;

import br.com.dbarreto.algorithm.graph.GraphShortestPath;
import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.graph.GraphType;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class ShortestPathScenarios {

    private ShortestPathScenarios() {
    }

    public static List<ShortestPathScenario<String>> scenarios() {
        return List.of(
                createSimpleUndirectedScenario(),
                createSmallDirectedScenario(),
                createMediumDirectedScenario(),
                createMediumUndirectedScenario(),
                createLargeDirectedScenario(),
                createLargeUndirectedScenario()
        );
    }

    public static <V> List<BiFunction<Graph<V>, V, Map<V, Double>>> shortestPathImplementations() {
        return List.of(
                GraphShortestPath::dijkstra,
                GraphShortestPath::bellmanFord
        );
    }

    // Small Undirected
    public static ShortestPathScenario<String> createSimpleUndirectedScenario() {
        return new ShortestPathScenario<>(
                new GraphScenarios.GraphScenario<>(
                        List.of(
                                new Graph.Edge<>("A", "B", 4.0),
                                new Graph.Edge<>("A", "C", 8.0),
                                new Graph.Edge<>("B", "C", 3.0),
                                new Graph.Edge<>("B", "E", 6.0),
                                new Graph.Edge<>("E", "D", 10.0)
                        ),
                        GraphType.UNDIRECTED
                ),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 4.0,
                        "C", 7.0,
                        "D", 20.0,
                        "E", 10.0
                )
        );
    }

    // Small Directed
    public static ShortestPathScenario<String> createSmallDirectedScenario() {
        return new ShortestPathScenario<>(
                new GraphScenarios.GraphScenario<>(
                        List.of(
                                new Graph.Edge<>("A", "B", 10.0),
                                new Graph.Edge<>("A", "C", 3.0),
                                new Graph.Edge<>("B", "D", 2.0),
                                new Graph.Edge<>("C", "B", 4.0),
                                new Graph.Edge<>("C", "D", 8.0),
                                new Graph.Edge<>("C", "E", 2.0),
                                new Graph.Edge<>("E", "D", 5.0)
                        ),
                        GraphType.DIRECTED
                ),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 7.0,
                        "C", 3.0,
                        "D", 9.0,
                        "E", 5.0
                )
        );
    }

    // Medium Directed
    public static ShortestPathScenario<String> createMediumDirectedScenario() {
        return new ShortestPathScenario<>(
                new GraphScenarios.GraphScenario<>(
                        List.of(
                                new Graph.Edge<>("D", "A", 4.0),
                                new Graph.Edge<>("A", "E", 4.0),
                                new Graph.Edge<>("A", "C", 3.0),
                                new Graph.Edge<>("D", "E", 2.0),
                                new Graph.Edge<>("E", "C", 4.0),
                                new Graph.Edge<>("E", "G", 5.0),
                                new Graph.Edge<>("C", "F", 4.0),
                                new Graph.Edge<>("C", "B", 3.0),
                                new Graph.Edge<>("G", "C", 5.0),
                                new Graph.Edge<>("G", "F", 5.0),
                                new Graph.Edge<>("F", "B", 2.0)
                        ),
                        GraphType.DIRECTED
                ),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 6.0,
                        "C", 3.0,
                        "D", Double.POSITIVE_INFINITY,
                        "E", 4.0,
                        "F", 7.0,
                        "G", 9.0
                )
        );
    }

    // Medium Undirected
    public static ShortestPathScenario<String> createMediumUndirectedScenario() {
        return new ShortestPathScenario<>(
                new GraphScenarios.GraphScenario<>(
                        List.of(
                                new Graph.Edge<>("A", "B", 2.0),
                                new Graph.Edge<>("A", "C", 5.0),
                                new Graph.Edge<>("B", "C", 2.0),
                                new Graph.Edge<>("B", "D", 4.0),
                                new Graph.Edge<>("C", "E", 3.0),
                                new Graph.Edge<>("D", "E", 1.0),
                                new Graph.Edge<>("D", "F", 2.0),
                                new Graph.Edge<>("E", "F", 5.0),
                                new Graph.Edge<>("F", "G", 1.0)
                        ),
                        GraphType.UNDIRECTED
                ),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 2.0,
                        "C", 4.0,
                        "D", 6.0,
                        "E", 7.0,
                        "F", 8.0,
                        "G", 9.0
                )
        );
    }

    // Large Directed
    public static ShortestPathScenario<String> createLargeDirectedScenario() {
        return new ShortestPathScenario<>(
                new GraphScenarios.GraphScenario<>(
                        List.of(
                                new Graph.Edge<>("S", "A", 2.0),
                                new Graph.Edge<>("S", "B", 5.0),
                                new Graph.Edge<>("A", "C", 2.0),
                                new Graph.Edge<>("A", "D", 4.0),
                                new Graph.Edge<>("B", "D", 1.0),
                                new Graph.Edge<>("B", "E", 2.0),
                                new Graph.Edge<>("C", "F", 3.0),
                                new Graph.Edge<>("D", "F", 2.0),
                                new Graph.Edge<>("D", "G", 5.0),
                                new Graph.Edge<>("E", "G", 2.0),
                                new Graph.Edge<>("F", "H", 4.0),
                                new Graph.Edge<>("G", "H", 1.0),
                                new Graph.Edge<>("G", "I", 3.0),
                                new Graph.Edge<>("H", "T", 3.0),
                                new Graph.Edge<>("I", "T", 4.0)
                        ),
                        GraphType.DIRECTED
                ),
                "S",
                Map.ofEntries(
                        Map.entry("S", 0.0),
                        Map.entry("A", 2.0),
                        Map.entry("B", 5.0),
                        Map.entry("C", 4.0),
                        Map.entry("D", 6.0),
                        Map.entry("E", 7.0),
                        Map.entry("F", 7.0),
                        Map.entry("G", 9.0),
                        Map.entry("H", 10.0),
                        Map.entry("I", 12.0),
                        Map.entry("T", 13.0)
                )
        );
    }

    // Large Undirected
    public static ShortestPathScenario<String> createLargeUndirectedScenario() {
        return new ShortestPathScenario<>(
                new GraphScenarios.GraphScenario<>(
                        List.of(
                                new Graph.Edge<>("1", "2", 1.0),
                                new Graph.Edge<>("1", "3", 4.0),
                                new Graph.Edge<>("2", "4", 2.0),
                                new Graph.Edge<>("2", "5", 3.0),
                                new Graph.Edge<>("3", "5", 1.0),
                                new Graph.Edge<>("3", "6", 5.0),
                                new Graph.Edge<>("4", "7", 3.0),
                                new Graph.Edge<>("5", "7", 2.0),
                                new Graph.Edge<>("5", "8", 4.0),
                                new Graph.Edge<>("6", "8", 1.0),
                                new Graph.Edge<>("7", "9", 2.0),
                                new Graph.Edge<>("8", "9", 5.0),
                                new Graph.Edge<>("8", "10", 3.0),
                                new Graph.Edge<>("9", "11", 1.0),
                                new Graph.Edge<>("10", "11", 2.0),
                                new Graph.Edge<>("11", "12", 4.0)
                        ),
                        GraphType.UNDIRECTED
                ),
                "1",
                Map.ofEntries(
                        Map.entry("1", 0.0),
                        Map.entry("2", 1.0),
                        Map.entry("3", 4.0),
                        Map.entry("4", 3.0),
                        Map.entry("5", 4.0),
                        Map.entry("6", 9.0),
                        Map.entry("7", 6.0),
                        Map.entry("8", 8.0),
                        Map.entry("9", 8.0),
                        Map.entry("10", 11.0),
                        Map.entry("11", 9.0),
                        Map.entry("12", 13.0)
                )
        );
    }

    public record ShortestPathScenario<V>(
            GraphScenarios.GraphScenario<V> graphScenario, String origin, Map<V, Double> distances) {
    }
}
