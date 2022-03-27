package com.jlabs.graph.directed;

import com.jlabs.graph.Edge;
import com.jlabs.graph.Vertex;

public class OrientedEdge extends Edge {

    public OrientedEdge() {
        super();
    }

    public OrientedEdge(Vertex source, Vertex target) {
        super(source, target);
    }

    public Vertex getSource() {
        return getA();
    }

    public Vertex getTarget() {
        return getB();
    }

    public void setSource(Vertex vertex) {
        setA(vertex);
    }

    public void setTarget(Vertex vertex) {
        setB(vertex);
    }
}
