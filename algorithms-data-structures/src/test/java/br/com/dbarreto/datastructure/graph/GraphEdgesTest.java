package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.utils.GraphScenarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GraphEdgesTest {

    @ParameterizedTest
    @MethodSource("graphArguments")
    @DisplayName("Should return correct physical edges")
    void shouldReturnCorrectPhysicalEdges(MutableGraph<String> graph) {
        Collection<Graph.Edge<String>> edges = graph.physicalEdges();
        
        // The default populated graph has 9 logical edges.
        // For directed graphs, physical edges == logical edges.
        // For undirected graphs, physical edges == 2 * logical edges.
        System.out.println(edges);
        if (graph.type() == GraphType.DIRECTED) {
            assertThat(edges).hasSize(9);
        } else {
            assertThat(edges).hasSize(18);
        }
    }

    @ParameterizedTest
    @MethodSource("graphArguments")
    @DisplayName("Should return correct logical edges")
    void shouldReturnCorrectLogicalEdges(MutableGraph<String> graph) {
        Collection<Graph.Edge<String>> edges = graph.logicalEdges();
        
        // Logical edges should always be 9 for the default populated graph.
        assertThat(edges).hasSize(9);
    }

    static Stream<Arguments> graphArguments() {
        return Stream.of(
                Arguments.of(GraphScenarios.createAdjacencyListGraph()),
                Arguments.of(GraphScenarios.createAdjacencyMatrixGraph()),
                Arguments.of(GraphScenarios.createIncidenceMatrixGraph()),
                Arguments.of(GraphScenarios.createNodeBasedGraph()),
                Arguments.of(GraphScenarios.createUndirectedAdjacencyListGraph()),
                Arguments.of(GraphScenarios.createUndirectedAdjacencyMatrixGraph()),
                Arguments.of(GraphScenarios.createUndirectedIncidenceMatrixGraph()),
                Arguments.of(GraphScenarios.createUndirectedNodeBasedGraph())
        );
    }
}
