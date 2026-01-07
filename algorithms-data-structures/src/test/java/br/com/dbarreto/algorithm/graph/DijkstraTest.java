package br.com.dbarreto.algorithm.graph;

import br.com.dbarreto.datastructure.graph.GraphType;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.AdjacencyListGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the Dijkstra algorithm implementation in {@link GraphShortestPath}.
 */
class DijkstraTest {

    /**
     * Tests the Dijkstra algorithm on a sample undirected graph.
     */
    @Test
    void shortestPath() {

        MutableGraph<String> graph = new AdjacencyListGraph<>(GraphType.UNDIRECTED);
        graph.addEdge("S", "A", 6);
        graph.addEdge("S", "D", 8);
        graph.addEdge("S", "E", 7);
        graph.addEdge("A", "B", 9);
        graph.addEdge("B", "C", 12);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "C", 3);
        graph.addEdge("D", "E", 10);

        assertThat(GraphShortestPath.dijkstra(graph, "S")).isEqualTo(
                Map.of("S", 0.0, "A", 6.0, "B", 15.0,
                        "C", 11.0, "D", 8.0, "E", 7.0));
    }
}
