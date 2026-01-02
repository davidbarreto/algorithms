package br.com.dbarreto.algorithm.graph;

import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.AdjacencyListGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void shortestPath() {

        MutableGraph<String> graph = new AdjacencyListGraph<>(Graph.UNDIRECTED_GRAPH);
        graph.addEdge("S", "A", 6);
        graph.addEdge("S", "D", 8);
        graph.addEdge("S", "E", 7);
        graph.addEdge("A", "B", 9);
        graph.addEdge("B", "C", 12);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "C", 3);
        graph.addEdge("D", "E", 10);

        assertEquals(Dijkstra.shortestPath(graph, "S"),
                Map.of("S", 0.0, "A", 6.0, "B", 15.0,
                        "C", 11.0, "D", 8.0, "E", 7.0));
    }
}