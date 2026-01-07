package br.com.dbarreto.datastructure.graph.policy;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collection;
import java.util.List;

/**
 * An {@link EdgePolicy} for directed graphs, where edges have a specific direction from a source
 * vertex to a target vertex.
 * <p>
 * In this policy:
 * <ul>
 *     <li>An edge from vertex {@code u} to vertex {@code v} is represented as a single, one-way connection.</li>
 *     <li>The number of logical edges is equal to the number of physical edges stored in the graph.</li>
 *     <li>Every physical edge is considered a logical edge.</li>
 * </ul>
 */
public final class DirectedEdgePolicy implements EdgePolicy {

    /**
     * Returns a collection containing a single pair that represents the directed edge from the
     * source vertex to the target vertex.
     *
     * @param from the source vertex of the edge
     * @param to   the target vertex of the edge
     * @param <V>  the type of the vertices
     * @return a {@link List} containing a single {@link Pair} representing the directed edge
     */
    @Override
    public <V> Collection<Pair<V, V>> edgePairs(V from, V to) {
        return List.of(new SimplePair<>(from, to));
    }

    /**
     * Returns the logical edge count, which is the same as the physical edge count for a directed
     * graph. Each stored edge corresponds to one logical edge.
     *
     * @param physicalEdgeCount the total number of physical edges in the graph's storage
     * @return the same value as {@code physicalEdgeCount}
     */
    @Override
    public int logicalEdgeCount(int physicalEdgeCount) {
        return physicalEdgeCount;
    }

    /**
     * Returns the physical edge count, which is the same as the logical edge count for a directed
     * graph.
     *
     * @param logicalEdgeCount the total number of logical edges
     * @return the same value as {@code logicalEdgeCount}
     */
    @Override
    public int physicalEdgeCount(int logicalEdgeCount) {
        return logicalEdgeCount;
    }

    /**
     * Determines if an edge is a logical edge. In a directed graph, every physical edge is also
     * a logical edge.
     *
     * @param from the source vertex of the edge
     * @param to   the target vertex of the edge
     * @param <V>  the type of the vertices
     * @return always {@code true}, as all physical edges in a directed graph are logical edges
     */
    @Override
    public <V> boolean isLogicalEdge(V from, V to) {
        return true;
    }
}
