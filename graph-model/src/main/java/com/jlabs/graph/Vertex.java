package com.jlabs.graph;

public class Vertex {

    public Vertex() {
        super();
    }

    public Edge appendChild(Vertex other) {
        return new Edge(this, other);
    }
}
