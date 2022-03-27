package jlabs.graph.directed;

import com.jlabs.graph.Graph;
import com.jlabs.graph.Vertex;
import com.jlabs.graph.directed.OrientedEdge;

import java.util.HashSet;
import java.util.Set;

public class DirectedGraph <E extends OrientedEdge, V extends Vertex> extends Graph {

    private Set<E> edgeSet = new HashSet<>();

    @Override
    public Set<E> getEdges() {
        return edgeSet;
    }
}
