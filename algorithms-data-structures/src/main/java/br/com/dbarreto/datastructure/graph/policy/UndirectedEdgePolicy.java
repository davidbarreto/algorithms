package br.com.dbarreto.datastructure.graph.policy;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collection;
import java.util.List;

/**
 * An {@link EdgePolicy} for undirected graphs, where edges are bidirectional and represent a
 * mutual connection between two vertices.
 * <p>
 * In this policy:
 * <ul>
 *     <li>A logical edge between vertices {@code u} and {@code v} is represented by two physical
 *     edges: one from {@code u} to {@code v}, and one from {@code v} to {@code u}.</li>
 *     <li>The number of logical edges is half the number of physical edges stored in the graph.</li>
 *     <li>To avoid duplication when iterating over logical edges, only one of the two physical
 *     edges is considered the "canonical" representation. This implementation uses the vertices'
 *     identity hash codes to make a consistent choice.</li>
 * </ul>
 */
public final class UndirectedEdgePolicy implements EdgePolicy {

    /**
     * Returns a collection of two pairs that represent the bidirectional nature of an undirected
     * edge: one pair from source to target, and another from target to source.
     *
     * @param from the source vertex of the logical connection
     * @param to   the target vertex of the logical connection
     * @param <V>  the type of the vertices
     * @return a {@link List} containing two {@link Pair}s: {@code (from, to)} and {@code (to, from)}
     */
    @Override
    public <V> Collection<Pair<V, V>> edgePairs(V from, V to) {
        return List.of(
                new SimplePair<>(from, to),
                new SimplePair<>(to, from)
        );
    }

    /**
     * Returns the logical edge count by dividing the physical edge count by two, since each
     * logical edge in an undirected graph corresponds to two physical edges.
     *
     * @param physicalEdgeCount the total number of physical edges in the graph's storage
     * @return the number of logical edges, which is {@code physicalEdgeCount / 2}
     */
    @Override
    public int logicalEdgeCount(int physicalEdgeCount) {
        return physicalEdgeCount / 2;
    }

    /**
     * Returns the physical edge count required to store a given number of logical edges. For an
     * undirected graph, this is twice the number of logical edges.
     *
     * @param logicalEdgeCount the total number of logical edges
     * @return the corresponding number of physical edges, which is {@code logicalEdgeCount * 2}
     */
    @Override
    public int physicalEdgeCount(int logicalEdgeCount) {
        return logicalEdgeCount * 2;
    }

    /**
     * Determines whether a given physical edge from {@code from} to {@code to} should be
     * considered the canonical representation of a logical edge.
     * <p>
     * To ensure that each undirected edge is counted only once when iterating, this implementation
     * selects the physical edge where the source vertex has a smaller identity hash code than the
     * target vertex. This provides a consistent and arbitrary rule for identifying the logical edge.
     *
     * @param from the source vertex of the physical edge
     * @param to   the target vertex of the physical edge
     * @param <V>  the type of the vertices
     * @return {@code true} if the identity hash code of {@code from} is less than that of {@code to},
     *         {@code false} otherwise
     */
    @Override
    public <V> boolean isLogicalEdge(V from, V to) {
        return System.identityHashCode(from) < System.identityHashCode(to);
    }
}
