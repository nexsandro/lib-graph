package com.jlabs.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph <E extends Edge, V extends Vertex> {

    private Set<V> vertexes = new HashSet<>();

    private Set<E> edges = new HashSet<>();

    public Graph() {
        super();
    }

    public Set<V> getVertexes() {
        return vertexes;
    }

    public Set<E> getEdges() {
        return edges;
    }

    public void addVertex(V vertex) {
        this.getVertexes().add(vertex);
    }

    public void addEdge(E edge) {
        this.getEdges().add(edge);
    }

}
