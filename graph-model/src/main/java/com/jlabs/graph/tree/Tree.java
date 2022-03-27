package jlabs.graph.tree;

import com.jlabs.graph.Graph;
import com.jlabs.graph.Vertex;
import com.jlabs.graph.directed.OrientedEdge;

public class Tree <E extends OrientedEdge, V extends Vertex> extends Graph {

    private V root;

    public Tree(V root) {
        this.root = root;
    }

    public V getRoot() {
        return root;
    }

    public void setRoot(V root) {
        this.root = root;
    }
}
