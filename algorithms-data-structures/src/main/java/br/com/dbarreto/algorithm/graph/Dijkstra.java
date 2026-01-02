package br.com.dbarreto.algorithm.graph;

import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.tuple.Pair;

import java.util.*;

public class Dijkstra {

    private Dijkstra() {}

    public static <V> Map<V, Double> shortestPath(Graph<V> graph, V from) {

        Collection<V> vertices = graph.vertices();
        PriorityQueue<Pair<V, Double>> queue = new PriorityQueue<>(Comparator.comparing(Pair::second));
        Map<V, Double> distance = new HashMap<>();

        for (V v : vertices) {
            distance.put(v, Double.POSITIVE_INFINITY);
        }

        queue.add(new Pair<>(from, 0.0));
        distance.put(from, 0.0);

        while (!queue.isEmpty()) {
            V minimumDistanceVertex = queue.poll().first();
            for (V neighbor : graph.neighborsOf(minimumDistanceVertex)) {
                double newDistance = distance.get(minimumDistanceVertex)
                        + graph.weight(minimumDistanceVertex, neighbor);
                if (newDistance < distance.get(neighbor)) {
                    distance.put(neighbor, newDistance);
                    queue.add(new Pair<>(neighbor, newDistance));
                }
            }

        }
        return distance;
    }
}
