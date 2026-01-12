package br.com.dbarreto.utils;

import br.com.dbarreto.algorithm.graph.GraphShortestPath;
import br.com.dbarreto.datastructure.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Utility class for creating various shortest path scenarios for testing purposes.
 * <p>
 * Provides a collection of graph scenarios, including the graph itself, the origin vertex,
 * and the expected shortest path distances. It also provides a list of shortest path
 * algorithm implementations to be tested.
 * </p>
 */
public class ShortestPathScenarios {

    private ShortestPathScenarios() {
    }

    public static List<ShortestPathScenario<String>> allSingleOriginShortestPathScenarios() {
        List<ShortestPathScenario<String>> scenarios = new ArrayList<>();
        scenarios.addAll(weightedSingleOriginShortestPathScenarios());
        scenarios.addAll(unweightedSingleOriginShortestPathScenarios());
        return scenarios;
    }

    /**
     * Returns a list of all predefined shortest path scenarios.
     *
     * @return a list of {@link ShortestPathScenario}
     */
    public static List<ShortestPathScenario<String>> weightedSingleOriginShortestPathScenarios() {
        return List.of(
                createSmallUndirectedWeightedSingleOriginShortestPathScenario(),
                createSmallDirectedWeightedSingleOriginShortestPathScenario(),
                createMediumUndirectedWeightedSingleOriginShortestPathScenario(),
                createMediumDirectedWeightedSingleOrginShortestPathScenario(),
                createLargeUndirectedWeightedSingleOriginShortestPathScenario(),
                createLargeDirectedWeightedSingleOriginShortestPathScenario()
        );
    }

    public static List<ShortestPathScenario<String>> unweightedSingleOriginShortestPathScenarios() {
        return List.of(
                createSmallUndirectedWeightNormalizedSingleOriginShortestPathScenario(),
                createSmallDirectedWeightNormalizedSingleOriginShortestPathScenario(),
                createMediumUndirectedWeightNormalizedSingleOriginShortestPathScenario(),
                createMediumDirectedWeightNormalizedSingleOriginShortestPathScenario(),
                createLargeUndirectedWeightNormalizedSingleOriginShortestPathScenario(),
                createLargeDirectedWeightNormalizedSingleOriginShortestPathScenario()
        );
    }

    public static List<AllVerticesShortestPathScenario<String>> allMultiOriginShortestPathScenarios() {
        return List.of(
            createUndirectedWeightedMultiOriginShortestPathScenario(),
            createSmallDirectedWeightedMultiOriginShortestPathScenario(),
            createMediumUndirectedWeightedMultiOriginShortestPathScenario(),
            createMediumDirectedWeightedMultiOriginShortestPathScenario(),
            createLargeUndirectedWeightedMultiOriginShortestPathScenario(),
            createLargeDirectedWeightedMultiOriginShortestPathScenario()
        );
    }

    /**
     * Returns a list of shortest path algorithm implementations to be tested.
     *
     * @param <V> the vertex type
     * @return a list of {@link BiFunction}s, each representing a shortest path algorithm
     */
    public static <V> List<BiFunction<Graph<V>, V, Map<V, Double>>> shortestPathImplementations() {
        return List.of(
                GraphShortestPath::dijkstra,
                GraphShortestPath::bellmanFord
        );
    }

    /**
     * Creates a simple undirected graph scenario.
     *
     * @return a {@link ShortestPathScenario}
     */
    public static ShortestPathScenario<String> createSmallUndirectedWeightedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createSmallUndirectedWeightedGraphScenario(),
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

    /**
     * Creates a small directed graph scenario.
     *
     * @return a {@link ShortestPathScenario}
     */
    public static ShortestPathScenario<String> createSmallDirectedWeightedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createSmallDirectedWeghtedGraphScenario(),
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

    /**
     * Creates a medium-sized directed graph scenario.
     *
     * @return a {@link ShortestPathScenario}
     */
    public static ShortestPathScenario<String> createMediumDirectedWeightedSingleOrginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createMediumDirectedWeightedGraphScenario(),
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

    /**
     * Creates a medium-sized undirected graph scenario.
     *
     * @return a {@link ShortestPathScenario}
     */
    public static ShortestPathScenario<String> createMediumUndirectedWeightedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createMediumUndirectedWeightedGraphScenario(),
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

    /**
     * Creates a large directed graph scenario.
     *
     * @return a {@link ShortestPathScenario}
     */
    public static ShortestPathScenario<String> createLargeDirectedWeightedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createLargeDirectedWeightedGraphScenario(),
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

    /**
     * Creates a large undirected graph scenario.
     *
     * @return a {@link ShortestPathScenario}
     */
    public static ShortestPathScenario<String> createLargeUndirectedWeightedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createLargeUndirectedWeightedGraphScenario(),
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

    public static ShortestPathScenario<String> createSmallUndirectedWeightNormalizedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createSmallUndirectedWeightNormalizedGraphScenario(),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 1.0,
                        "C", 1.0,
                        "D", 1.0,
                        "E", 2.0
                )
        );
    }

    public static ShortestPathScenario<String> createSmallDirectedWeightNormalizedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createSmallDirectedWeightNormalizedGraphScenario(),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 2.0,
                        "C", 1.0,
                        "D", 1.0,
                        "E", 2.0
                )
        );
    }

    public static ShortestPathScenario<String> createMediumUndirectedWeightNormalizedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createMediumUndirectedWeightNormalizedGraphScenario(),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 2.0,
                        "C", 1.0,
                        "D", 1.0,
                        "E", 1.0,
                        "F", 2.0,
                        "G", 2.0
                )
        );
    }

    public static ShortestPathScenario<String> createMediumDirectedWeightNormalizedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createMediumDirectedWeightNormalizedGraphScenario(),
                "A",
                Map.of(
                        "A", 0.0,
                        "B", 2.0,
                        "C", 1.0,
                        "D", 1.0,
                        "E", 1.0,
                        "F", 2.0,
                        "G", Double.POSITIVE_INFINITY
                )
        );
    }

    public static ShortestPathScenario<String> createLargeUndirectedWeightNormalizedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createLargeUndirectedWeightNormalizedGraphScenario(),
                "1",
                Map.ofEntries(
                        Map.entry("1", 0.0),
                        Map.entry("2", 1.0),
                        Map.entry("3", 1.0),
                        Map.entry("4", 2.0),
                        Map.entry("5", 2.0),
                        Map.entry("6", 2.0),
                        Map.entry("7", 3.0),
                        Map.entry("8", 3.0),
                        Map.entry("9", 4.0),
                        Map.entry("10", 4.0),
                        Map.entry("11", 5.0),
                        Map.entry("12", 6.0)
                )
        );
    }

    public static ShortestPathScenario<String> createLargeDirectedWeightNormalizedSingleOriginShortestPathScenario() {
        return new ShortestPathScenario<>(
                GraphScenarios.createLargeDirectedWeightNormalizedGraphScenario(),
                "S",
                Map.ofEntries(
                        Map.entry("S", 0.0),
                        Map.entry("A", 1.0),
                        Map.entry("B", 1.0),
                        Map.entry("C", 2.0),
                        Map.entry("D", 2.0),
                        Map.entry("E", 2.0),
                        Map.entry("F", 3.0),
                        Map.entry("G", 3.0),
                        Map.entry("H", 4.0),
                        Map.entry("I", 4.0),
                        Map.entry("T", 5.0)
                )
        );
    }

    public static AllVerticesShortestPathScenario<String> createUndirectedWeightedMultiOriginShortestPathScenario() {
        return new AllVerticesShortestPathScenario<>(
                GraphScenarios.createSmallUndirectedWeightedGraphScenario(),
                Map.ofEntries(
                        Map.entry("A", Map.ofEntries(
                                Map.entry("A", 0.0),
                                Map.entry("B", 4.0),
                                Map.entry("C", 7.0),
                                Map.entry("D", 20.0),
                                Map.entry("E", 10.0)
                            )
                        ),
                        Map.entry("B", Map.ofEntries(
                                        Map.entry("A", 4.0),
                                        Map.entry("B", 0.0),
                                        Map.entry("C", 3.0),
                                        Map.entry("D", 16.0),
                                        Map.entry("E", 6.0)
                                )
                        ),
                        Map.entry("C", Map.ofEntries(
                                        Map.entry("A", 7.0),
                                        Map.entry("B", 3.0),
                                        Map.entry("C", 0.0),
                                        Map.entry("D", 19.0),
                                        Map.entry("E", 9.0)
                                )
                        ),
                        Map.entry("D", Map.ofEntries(
                                        Map.entry("A", 20.0),
                                        Map.entry("B", 16.0),
                                        Map.entry("C", 19.0),
                                        Map.entry("D", 0.0),
                                        Map.entry("E", 10.0)
                                )
                        ),
                        Map.entry("E", Map.ofEntries(
                                        Map.entry("A", 10.0),
                                        Map.entry("B", 6.0),
                                        Map.entry("C", 9.0),
                                        Map.entry("D", 10.0),
                                        Map.entry("E", 0.0)
                                )
                        )
                )
        );
    }

    public static AllVerticesShortestPathScenario<String> createSmallDirectedWeightedMultiOriginShortestPathScenario() {
        return new AllVerticesShortestPathScenario<>(
                GraphScenarios.createSmallDirectedWeghtedGraphScenario(),
                Map.ofEntries(
                        Map.entry("A", Map.ofEntries(
                                Map.entry("A", 0.0),
                                Map.entry("B", 7.0),
                                Map.entry("C", 3.0),
                                Map.entry("D", 9.0),
                                Map.entry("E", 5.0)
                        )),
                        Map.entry("B", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", 0.0),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", 2.0),
                                Map.entry("E", Double.POSITIVE_INFINITY)
                        )),
                        Map.entry("C", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", 4.0),
                                Map.entry("C", 0.0),
                                Map.entry("D", 6.0),
                                Map.entry("E", 2.0)
                        )),
                        Map.entry("D", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", 0.0),
                                Map.entry("E", Double.POSITIVE_INFINITY)
                        )),
                        Map.entry("E", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", 5.0),
                                Map.entry("E", 0.0)
                        ))
                )
        );
    }

    public static AllVerticesShortestPathScenario<String> createMediumUndirectedWeightedMultiOriginShortestPathScenario() {
        return new AllVerticesShortestPathScenario<>(
                GraphScenarios.createMediumUndirectedWeightedGraphScenario(),
                Map.ofEntries(
                        Map.entry("A", Map.ofEntries(
                                Map.entry("A", 0.0), Map.entry("B", 2.0), Map.entry("C", 4.0), Map.entry("D", 6.0), Map.entry("E", 7.0), Map.entry("F", 8.0), Map.entry("G", 9.0)
                        )),
                        Map.entry("B", Map.ofEntries(
                                Map.entry("A", 2.0), Map.entry("B", 0.0), Map.entry("C", 2.0), Map.entry("D", 4.0), Map.entry("E", 5.0), Map.entry("F", 6.0), Map.entry("G", 7.0)
                        )),
                        Map.entry("C", Map.ofEntries(
                                Map.entry("A", 4.0), Map.entry("B", 2.0), Map.entry("C", 0.0), Map.entry("D", 4.0), Map.entry("E", 3.0), Map.entry("F", 6.0), Map.entry("G", 7.0)
                        )),
                        Map.entry("D", Map.ofEntries(
                                Map.entry("A", 6.0), Map.entry("B", 4.0), Map.entry("C", 4.0), Map.entry("D", 0.0), Map.entry("E", 1.0), Map.entry("F", 2.0), Map.entry("G", 3.0)
                        )),
                        Map.entry("E", Map.ofEntries(
                                Map.entry("A", 7.0), Map.entry("B", 5.0), Map.entry("C", 3.0), Map.entry("D", 1.0), Map.entry("E", 0.0), Map.entry("F", 3.0), Map.entry("G", 4.0)
                        )),
                        Map.entry("F", Map.ofEntries(
                                Map.entry("A", 8.0), Map.entry("B", 6.0), Map.entry("C", 6.0), Map.entry("D", 2.0), Map.entry("E", 3.0), Map.entry("F", 0.0), Map.entry("G", 1.0)
                        )),
                        Map.entry("G", Map.ofEntries(
                                Map.entry("A", 9.0), Map.entry("B", 7.0), Map.entry("C", 7.0), Map.entry("D", 3.0), Map.entry("E", 4.0), Map.entry("F", 1.0), Map.entry("G", 0.0)
                        ))
                )
        );
    }

    public static AllVerticesShortestPathScenario<String> createMediumDirectedWeightedMultiOriginShortestPathScenario() {
        return new AllVerticesShortestPathScenario<>(
                GraphScenarios.createMediumDirectedWeightedGraphScenario(),
                Map.ofEntries(
                        Map.entry("A", Map.ofEntries(
                                Map.entry("A", 0.0), Map.entry("B", 6.0), Map.entry("C", 3.0), Map.entry("D", Double.POSITIVE_INFINITY), Map.entry("E", 4.0), Map.entry("F", 7.0), Map.entry("G", 9.0)
                        )),
                        Map.entry("B", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY), Map.entry("B", 0.0), Map.entry("C", Double.POSITIVE_INFINITY), Map.entry("D", Double.POSITIVE_INFINITY), Map.entry("E", Double.POSITIVE_INFINITY), Map.entry("F", Double.POSITIVE_INFINITY), Map.entry("G", Double.POSITIVE_INFINITY)
                        )),
                        Map.entry("C", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY), Map.entry("B", 3.0), Map.entry("C", 0.0), Map.entry("D", Double.POSITIVE_INFINITY), Map.entry("E", Double.POSITIVE_INFINITY), Map.entry("F", 4.0), Map.entry("G", Double.POSITIVE_INFINITY)
                        )),
                        Map.entry("D", Map.ofEntries(
                                Map.entry("A", 4.0), Map.entry("B", 9.0), Map.entry("C", 6.0), Map.entry("D", 0.0), Map.entry("E", 2.0), Map.entry("F", 10.0), Map.entry("G", 7.0)
                        )),
                        Map.entry("E", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY), Map.entry("B", 7.0), Map.entry("C", 4.0), Map.entry("D", Double.POSITIVE_INFINITY), Map.entry("E", 0.0), Map.entry("F", 8.0), Map.entry("G", 5.0)
                        )),
                        Map.entry("F", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY), Map.entry("B", 2.0), Map.entry("C", Double.POSITIVE_INFINITY), Map.entry("D", Double.POSITIVE_INFINITY), Map.entry("E", Double.POSITIVE_INFINITY), Map.entry("F", 0.0), Map.entry("G", Double.POSITIVE_INFINITY)
                        )),
                        Map.entry("G", Map.ofEntries(
                                Map.entry("A", Double.POSITIVE_INFINITY), Map.entry("B", 7.0), Map.entry("C", 5.0), Map.entry("D", Double.POSITIVE_INFINITY), Map.entry("E", Double.POSITIVE_INFINITY), Map.entry("F", 5.0), Map.entry("G", 0.0)
                        ))
                )
        );
    }

    public static AllVerticesShortestPathScenario<String> createLargeUndirectedWeightedMultiOriginShortestPathScenario() {
        return new AllVerticesShortestPathScenario<>(
                GraphScenarios.createLargeUndirectedWeightedGraphScenario(),
                Map.ofEntries(
                        Map.entry("1", Map.ofEntries(
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
                        )),
                        Map.entry("2", Map.ofEntries(
                                Map.entry("1", 1.0),
                                Map.entry("2", 0.0),
                                Map.entry("3", 4.0),
                                Map.entry("4", 2.0),
                                Map.entry("5", 3.0),
                                Map.entry("6", 8.0),
                                Map.entry("7", 5.0),
                                Map.entry("8", 7.0),
                                Map.entry("9", 7.0),
                                Map.entry("10", 10.0),
                                Map.entry("11", 8.0),
                                Map.entry("12", 12.0)
                        )),
                        Map.entry("3", Map.ofEntries(
                                Map.entry("1", 4.0),
                                Map.entry("2", 4.0),
                                Map.entry("3", 0.0),
                                Map.entry("4", 6.0),
                                Map.entry("5", 1.0),
                                Map.entry("6", 5.0),
                                Map.entry("7", 3.0),
                                Map.entry("8", 5.0),
                                Map.entry("9", 5.0),
                                Map.entry("10", 8.0),
                                Map.entry("11", 6.0),
                                Map.entry("12", 10.0)
                        )),
                        Map.entry("4", Map.ofEntries(
                                Map.entry("1", 3.0),
                                Map.entry("2", 2.0),
                                Map.entry("3", 6.0),
                                Map.entry("4", 0.0),
                                Map.entry("5", 5.0),
                                Map.entry("6", 10.0),
                                Map.entry("7", 3.0),
                                Map.entry("8", 9.0),
                                Map.entry("9", 5.0),
                                Map.entry("10", 8.0),
                                Map.entry("11", 6.0),
                                Map.entry("12", 10.0)
                        )),
                        Map.entry("5", Map.ofEntries(
                                Map.entry("1", 4.0),
                                Map.entry("2", 3.0),
                                Map.entry("3", 1.0),
                                Map.entry("4", 5.0),
                                Map.entry("5", 0.0),
                                Map.entry("6", 5.0),
                                Map.entry("7", 2.0),
                                Map.entry("8", 4.0),
                                Map.entry("9", 4.0),
                                Map.entry("10", 7.0),
                                Map.entry("11", 5.0),
                                Map.entry("12", 9.0)
                        )),
                        Map.entry("6", Map.ofEntries(
                                Map.entry("1", 9.0),
                                Map.entry("2", 8.0),
                                Map.entry("3", 5.0),
                                Map.entry("4", 10.0),
                                Map.entry("5", 5.0),
                                Map.entry("6", 0.0),
                                Map.entry("7", 7.0),
                                Map.entry("8", 1.0),
                                Map.entry("9", 6.0),
                                Map.entry("10", 4.0),
                                Map.entry("11", 6.0),
                                Map.entry("12", 10.0)
                        )),
                        Map.entry("7", Map.ofEntries(
                                Map.entry("1", 6.0),
                                Map.entry("2", 5.0),
                                Map.entry("3", 3.0),
                                Map.entry("4", 3.0),
                                Map.entry("5", 2.0),
                                Map.entry("6", 7.0),
                                Map.entry("7", 0.0),
                                Map.entry("8", 6.0),
                                Map.entry("9", 2.0),
                                Map.entry("10", 5.0),
                                Map.entry("11", 3.0),
                                Map.entry("12", 7.0)
                        )),
                        Map.entry("8", Map.ofEntries(
                                Map.entry("1", 8.0),
                                Map.entry("2", 7.0),
                                Map.entry("3", 5.0),
                                Map.entry("4", 9.0),
                                Map.entry("5", 4.0),
                                Map.entry("6", 1.0),
                                Map.entry("7", 6.0),
                                Map.entry("8", 0.0),
                                Map.entry("9", 5.0),
                                Map.entry("10", 3.0),
                                Map.entry("11", 5.0),
                                Map.entry("12", 9.0)
                        )),
                        Map.entry("9", Map.ofEntries(
                                Map.entry("1", 8.0),
                                Map.entry("2", 7.0),
                                Map.entry("3", 5.0),
                                Map.entry("4", 5.0),
                                Map.entry("5", 4.0),
                                Map.entry("6", 6.0),
                                Map.entry("7", 2.0),
                                Map.entry("8", 5.0),
                                Map.entry("9", 0.0),
                                Map.entry("10", 3.0),
                                Map.entry("11", 1.0),
                                Map.entry("12", 5.0)
                        )),
                        Map.entry("10", Map.ofEntries(
                                Map.entry("1", 11.0),
                                Map.entry("2", 10.0),
                                Map.entry("3", 8.0),
                                Map.entry("4", 8.0),
                                Map.entry("5", 7.0),
                                Map.entry("6", 4.0),
                                Map.entry("7", 5.0),
                                Map.entry("8", 3.0),
                                Map.entry("9", 3.0),
                                Map.entry("10", 0.0),
                                Map.entry("11", 2.0),
                                Map.entry("12", 6.0)
                        )),
                        Map.entry("11", Map.ofEntries(
                                Map.entry("1", 9.0),
                                Map.entry("2", 8.0),
                                Map.entry("3", 6.0),
                                Map.entry("4", 6.0),
                                Map.entry("5", 5.0),
                                Map.entry("6", 6.0),
                                Map.entry("7", 3.0),
                                Map.entry("8", 5.0),
                                Map.entry("9", 1.0),
                                Map.entry("10", 2.0),
                                Map.entry("11", 0.0),
                                Map.entry("12", 4.0)
                        )),
                        Map.entry("12", Map.ofEntries(
                                Map.entry("1", 13.0),
                                Map.entry("2", 12.0),
                                Map.entry("3", 10.0),
                                Map.entry("4", 10.0),
                                Map.entry("5", 9.0),
                                Map.entry("6", 10.0),
                                Map.entry("7", 7.0),
                                Map.entry("8", 9.0),
                                Map.entry("9", 5.0),
                                Map.entry("10", 6.0),
                                Map.entry("11", 4.0),
                                Map.entry("12", 0.0)
                        ))
                )
        );
    }

    public static AllVerticesShortestPathScenario<String> createLargeDirectedWeightedMultiOriginShortestPathScenario() {
        return new AllVerticesShortestPathScenario<>(
                GraphScenarios.createLargeDirectedWeightedGraphScenario(),
                Map.ofEntries(
                        Map.entry("S", Map.ofEntries(
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
                        )),
                        Map.entry("A", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", 0.0),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", 2.0),
                                Map.entry("D", 4.0),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", 5.0),
                                Map.entry("G", 9.0),
                                Map.entry("H", 9.0),
                                Map.entry("I", 12.0),
                                Map.entry("T", 12.0)
                        )),
                        Map.entry("B", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", 0.0),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", 1.0),
                                Map.entry("E", 2.0),
                                Map.entry("F", 3.0),
                                Map.entry("G", 4.0),
                                Map.entry("H", 5.0),
                                Map.entry("I", 7.0),
                                Map.entry("T", 8.0)
                        )),
                        Map.entry("C", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", 0.0),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", 3.0),
                                Map.entry("G", Double.POSITIVE_INFINITY),
                                Map.entry("H", 7.0),
                                Map.entry("I", Double.POSITIVE_INFINITY),
                                Map.entry("T", 10.0)
                        )),
                        Map.entry("D", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", 0.0),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", 2.0),
                                Map.entry("G", 5.0),
                                Map.entry("H", 6.0),
                                Map.entry("I", 8.0),
                                Map.entry("T", 9.0)
                        )),
                        Map.entry("E", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", 0.0),
                                Map.entry("F", Double.POSITIVE_INFINITY),
                                Map.entry("G", 2.0),
                                Map.entry("H", 3.0),
                                Map.entry("I", 5.0),
                                Map.entry("T", 6.0)
                        )),
                        Map.entry("F", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", 0.0),
                                Map.entry("G", Double.POSITIVE_INFINITY),
                                Map.entry("H", 4.0),
                                Map.entry("I", Double.POSITIVE_INFINITY),
                                Map.entry("T", 7.0)
                        )),
                        Map.entry("G", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", Double.POSITIVE_INFINITY),
                                Map.entry("G", 0.0),
                                Map.entry("H", 1.0),
                                Map.entry("I", 3.0),
                                Map.entry("T", 4.0)
                        )),
                        Map.entry("H", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", Double.POSITIVE_INFINITY),
                                Map.entry("G", Double.POSITIVE_INFINITY),
                                Map.entry("H", 0.0),
                                Map.entry("I", Double.POSITIVE_INFINITY),
                                Map.entry("T", 3.0)
                        )),
                        Map.entry("I", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", Double.POSITIVE_INFINITY),
                                Map.entry("G", Double.POSITIVE_INFINITY),
                                Map.entry("H", Double.POSITIVE_INFINITY),
                                Map.entry("I", 0.0),
                                Map.entry("T", 4.0)
                        )),
                        Map.entry("T", Map.ofEntries(
                                Map.entry("S", Double.POSITIVE_INFINITY),
                                Map.entry("A", Double.POSITIVE_INFINITY),
                                Map.entry("B", Double.POSITIVE_INFINITY),
                                Map.entry("C", Double.POSITIVE_INFINITY),
                                Map.entry("D", Double.POSITIVE_INFINITY),
                                Map.entry("E", Double.POSITIVE_INFINITY),
                                Map.entry("F", Double.POSITIVE_INFINITY),
                                Map.entry("G", Double.POSITIVE_INFINITY),
                                Map.entry("H", Double.POSITIVE_INFINITY),
                                Map.entry("I", Double.POSITIVE_INFINITY),
                                Map.entry("T", 0.0)
                        ))
                )
        );
    }

    /**
     * Represents a complete scenario for testing a shortest path algorithm.
     *
     * @param graphScenario the graph data (edges and type)
     * @param origin        the starting vertex for the shortest path calculation
     * @param distances     a map of expected shortest distances from the origin to all other vertices
     * @param <V>           the type of the vertices
     */
    public record ShortestPathScenario<V>(
            GraphScenarios.GraphScenario<V> graphScenario, String origin, Map<V, Double> distances) {
    }

    public record AllVerticesShortestPathScenario<V>(
            GraphScenarios.GraphScenario<V> graphScenario, Map<V, Map<V, Double>> distances) {
    }
}
