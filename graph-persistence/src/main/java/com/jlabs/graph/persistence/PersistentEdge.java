package com.jlabs.graph.persistence;

import com.jlabs.graph.directed.OrientedEdge;

import javax.persistence.*;

@Entity
@Table(name = "TB_EDGE")
public class PersistentEdge extends OrientedEdge {

    private Integer id;

    public PersistentEdge() {
        super();
    }

    @Id
    @GeneratedValue(generator = "SeqEdge")
    @SequenceGenerator(name = "SeqEdge",  initialValue = 1, allocationSize = 1, sequenceName = "SE_EDGE")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "CD_SORC", referencedColumnName = "CD_VERT", nullable = false)
    public PersistentVertex getSource() {
        return (PersistentVertex) super.getSource();
    }

    @Override
    @ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "CD_TRGT", referencedColumnName = "CD_VERT", nullable = false)
    public PersistentVertex getTarget() {
        return (PersistentVertex) super.getTarget();
    }

}
