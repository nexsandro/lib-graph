package com.jlabs.graph.persistence;

import com.jlabs.graph.Vertex;

import javax.persistence.*;

@Entity
@Table(name = "TB_VERT")
public class PersistentVertex extends Vertex {

    private Integer id;

    public PersistentVertex() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqVertex")
    @SequenceGenerator(name = "SeqVertex", initialValue = 1, allocationSize = 1, sequenceName = "SE_VRTX")
    @Column(name = "CD_VERT")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
