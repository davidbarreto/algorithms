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

class GraphShortestPathTest {

    @ParameterizedTest()
    @MethodSource("shortestPathArguments")
    void shortestPath(Function<GraphScenarios.GraphArguments, MutableGraph<String>> graphImplementation,
                      BiFunction<Graph<String>, String, Map<String, Double>> shortestPathAlgorithm,
                      ShortestPathScenarios.ShortestPathScenario<String> scenario)
    {
        var graph = GraphScenarios.of(graphImplementation, scenario.graphScenario());
        assertThat(shortestPathAlgorithm.apply(graph, scenario.origin())).isEqualTo(scenario.distances());
    }

    static Stream<Arguments> shortestPathArguments() {
        return ShortestPathScenarios.scenarios().stream()
                .flatMap(scenario -> GraphScenarios.graphImplementations().stream()
                        .flatMap(graphImplementation -> ShortestPathScenarios.shortestPathImplementations().stream()
                                .map(algorithm ->
                                        Arguments.of(graphImplementation, algorithm, scenario)
                                )
                        )
                );
    }
}