package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.utils.GraphScenarios;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for various {@link Graph} implementations.
 * <p>
 * Verifies basic functionality like adding/removing vertices and edges,
 * checking connectivity, and handling edge cases.
 * </p>
 */
class GraphTest {

    /**
     * Verifies that directed graphs correctly handle edge directionality,
     * vertex/edge counts, and neighbor retrieval.
     */
    @ParameterizedTest
    @MethodSource("graphArguments")
    void testDirectedGraph(Graph<String> graph) {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("A", "C"));
        assertFalse(graph.hasEdge("B", "A"));

        assertEquals(7, graph.vertexCount());
        assertEquals(9, graph.edgeCount());

        assertThat(graph.neighborsOf("A")).containsExactlyInAnyOrder("B", "D", "E");
        assertThat(graph.neighborsOf("B")).containsExactlyInAnyOrder("E", "C", "G");
        assertThat(graph.neighborsOf("F")).isEmpty();
    }

    /**
     * Verifies that mutable operations (remove edge, remove vertex) work correctly
     * on directed graphs, updating counts and connectivity.
     */
    @ParameterizedTest
    @MethodSource("graphArguments")
    void testDirectedMutableGraph(MutableGraph<String> graph) {
        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertEquals(8, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertEquals(6, graph.vertexCount());
        assertEquals(5, graph.edgeCount());
    }

    /**
     * Verifies that undirected graphs correctly handle symmetric edges,
     * where a connection A-B implies B-A.
     */
    @ParameterizedTest
    @MethodSource("undirectedGraphArguments")
    void testUndirectedGraph(Graph<String> graph) {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "A"));
        assertTrue(graph.hasEdge("B", "C"));
        assertTrue(graph.hasEdge("C", "B"));

        assertEquals(7, graph.vertexCount());
        assertEquals(9, graph.edgeCount());

        assertThat(graph.neighborsOf("A")).containsExactlyInAnyOrder("B", "D", "E");
        assertThat(graph.neighborsOf("B")).containsExactlyInAnyOrder("A", "E", "C", "G");
        assertThat(graph.neighborsOf("F")).containsExactlyInAnyOrder("E");
    }

    /**
     * Verifies that mutable operations work correctly on undirected graphs,
     * ensuring that removing an edge or vertex removes the connection in both directions.
     */
    @ParameterizedTest
    @MethodSource("undirectedGraphArguments")
    void testUndirectedMutableGraph(MutableGraph<String> graph) {
        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("B", "A"));
        assertEquals(8, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("C", "B"));
        assertEquals(6, graph.vertexCount());
        assertEquals(5, graph.edgeCount());
    }

    /**
     * Verifies that operations on non-existent elements do not cause errors or corrupt the graph state.
     */
    @ParameterizedTest
    @MethodSource("graphArguments")
    void testGraphRobustness(MutableGraph<String> graph) {
        int initialVertexCount = graph.vertexCount();
        int initialEdgeCount = graph.edgeCount();

        // Removing non-existent elements should not throw or change state
        graph.removeVertex("NonExistent");
        graph.removeEdge("A", "NonExistent");
        graph.removeEdge("NonExistent", "A");

        assertEquals(initialVertexCount, graph.vertexCount());
        assertEquals(initialEdgeCount, graph.edgeCount());
    }

    /**
     * Verifies that isolated vertices can be added and have no neighbors.
     */
    @ParameterizedTest
    @MethodSource("graphArguments")
    void testIsolatedVertex(MutableGraph<String> graph) {
        graph.addVertex("Isolated");

        assertTrue(graph.containsVertex("Isolated"));
        assertThat(graph.neighborsOf("Isolated")).isEmpty();
    }

    /**
     * Verifies that adding an edge with weight 0 throws an exception.
     */
    @ParameterizedTest
    @MethodSource("graphArguments")
    void testZeroWeightEdge(MutableGraph<String> graph) {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B", 0.0));
    }

    /**
     * Verifies that edges can be added with specific weights and that weight() returns the correct value.
     */
    @ParameterizedTest
    @MethodSource("emptyGraphArguments")
    void testWeightedGraph(MutableGraph<String> graph) {
        assertTrue(graph.addEdge("A", "B", 5.0));
        assertTrue(graph.addEdge("B", "C", 2.5));

        assertTrue(graph.hasEdge("A", "B"));
        assertThat(graph.weight("A", "B")).hasValue(5.0);
        assertThat(graph.weight("B", "C")).hasValue(2.5);
    }

    /**
     * Verifies that addEdge(u, v) uses the default weight.
     */
    @ParameterizedTest
    @MethodSource("emptyGraphArguments")
    void testDefaultWeight(MutableGraph<String> graph) {
        assertTrue(graph.addEdge("A", "B"));

        assertTrue(graph.hasEdge("A", "B"));
        assertThat(graph.weight("A", "B")).hasValue(MutableGraph.DEFAULT_WEIGHT);
    }

    /**
     * Verifies that adding an edge that already exists does not update its weight.
     */
    @ParameterizedTest
    @MethodSource("emptyGraphArguments")
    void testUpdateWeight(MutableGraph<String> graph) {
        assertTrue(graph.addEdge("A", "B", 3.0));
        assertThat(graph.weight("A", "B")).hasValue(3.0);

        // Try adding again, should return false and NOT update weight
        assertFalse(graph.addEdge("A", "B", 10.0));
        assertThat(graph.weight("A", "B")).hasValue(3.0);

        graph.removeEdge("A", "B");
        assertTrue(graph.addEdge("A", "B", 10.0));
        assertThat(graph.weight("A", "B")).hasValue(10.0);
        
        // Edge count should remain 1
        assertEquals(1, graph.edgeCount());
    }

    /**
     * Verifies that adding an edge twice with different weights does not update the weight
     * and returns false for the second call.
     */
    @ParameterizedTest
    @MethodSource("emptyGraphArguments")
    void testAddEdgeTwiceWithDifferentWeights(MutableGraph<String> graph) {
        // First addition
        assertTrue(graph.addEdge("A", "B", 5.0));
        assertThat(graph.weight("A", "B")).isNotEmpty();
        assertThat(graph.weight("A", "B")).hasValue(5.0);

        // Second addition with different weight
        assertFalse(graph.addEdge("A", "B", 10.0));
        
        // Weight should remain 5.0
        assertThat(graph.weight("A", "B")).isNotEmpty();
        assertThat(graph.weight("A", "B")).hasValue(5.0);
    }

    /**
     * Verifies that weight() returns an empty optional for non-existent edges.
     */
    @ParameterizedTest
    @MethodSource("emptyGraphArguments")
    void testWeightOfNonExistentEdge(MutableGraph<String> graph) {
        graph.addVertex("A");
        graph.addVertex("B");

        assertFalse(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("A", "C"));
        assertThat(graph.weight("A", "B")).isEmpty();
        assertThat(graph.weight("A", "C")).isEmpty(); // C doesn't exist
    }

    /**
     * Verifies that removing an edge clears its weight.
     */
    @ParameterizedTest
    @MethodSource("emptyGraphArguments")
    void testRemoveEdgeWithWeight(MutableGraph<String> graph) {
        graph.addEdge("A", "B", 5.0);
        graph.removeEdge("A", "B");

        assertFalse(graph.hasEdge("A", "B"));
        assertThat(graph.weight("A", "B")).isEmpty();
    }

    static Stream<Arguments> graphArguments() {
        return Stream.of(
                Arguments.of(GraphScenarios.createAdjacencyListGraph()),
                Arguments.of(GraphScenarios.createAdjacencyMatrixGraph()),
                Arguments.of(GraphScenarios.createIncidenceMatrixGraph()),
                Arguments.of(GraphScenarios.createNodeBasedGraph())
        );
    }

    static Stream<Arguments> undirectedGraphArguments() {
        return Stream.of(
                Arguments.of(GraphScenarios.createUndirectedAdjacencyListGraph()),
                Arguments.of(GraphScenarios.createUndirectedAdjacencyMatrixGraph()),
                Arguments.of(GraphScenarios.createUndirectedIncidenceMatrixGraph()),
                Arguments.of(GraphScenarios.createUndirectedNodeBasedGraph())
        );
    }

    static Stream<Arguments> emptyGraphArguments() {
        return Stream.of(
                Arguments.of(new br.com.dbarreto.datastructure.graph.impl.AdjacencyListGraph<String>()),
                Arguments.of(new br.com.dbarreto.datastructure.graph.impl.AdjacencyMatrixGraph<String>(10)),
                Arguments.of(new br.com.dbarreto.datastructure.graph.impl.IncidenceMatrixGraph<String>(10, 20)),
                Arguments.of(new br.com.dbarreto.datastructure.graph.impl.NodeBasedGraph<String>())
        );
    }
}
