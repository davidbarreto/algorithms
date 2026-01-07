package br.com.dbarreto.algorithm.graph;

import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.utils.GraphScenarios;
import br.com.dbarreto.utils.ShortestPathScenarios;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Parameterized test class for various shortest path algorithms.
 * <p>
 * This class tests different shortest path algorithm implementations against various
 * graph structures and scenarios.
 * </p>
 */
class GraphShortestPathTest {

    /**
     * Tests a shortest path algorithm on a given graph scenario.
     *
     * @param graphImplementation   A function to create the graph
     * @param shortestPathAlgorithm The shortest path algorithm to test
     * @param scenario              The test scenario, including the graph, origin, and expected distances
     */
    @ParameterizedTest()
    @MethodSource("shortestPathArguments")
    void shortestPath(Function<GraphScenarios.GraphArguments, MutableGraph<String>> graphImplementation,
                      BiFunction<Graph<String>, String, Map<String, Double>> shortestPathAlgorithm,
                      ShortestPathScenarios.ShortestPathScenario<String> scenario)
    {
        var graph = GraphScenarios.of(graphImplementation, scenario.graphScenario());
        assertThat(shortestPathAlgorithm.apply(graph, scenario.origin())).isEqualTo(scenario.distances());
    }

    /**
     * Provides a stream of arguments for the parameterized tests.
     * <p>
     * This method generates a Cartesian product of all graph implementations,
     * shortest path algorithms, and test scenarios.
     * </p>
     *
     * @return a stream of {@link Arguments} for the tests
     */
    static Stream<Arguments> shortestPathArguments() {
        return ShortestPathScenarios.scenarios().stream()
                .flatMap(scenario -> GraphScenarios.graphImplementations().stream()
                        .flatMap(graphImplementation -> ShortestPathScenarios.<String>shortestPathImplementations().stream()
                                .map(algorithm ->
                                        Arguments.of(graphImplementation, algorithm, scenario)
                                )
                        )
                );
    }
}
