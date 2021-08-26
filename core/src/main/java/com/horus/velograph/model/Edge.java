package com.horus.velograph.model;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumns;


@Entity
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long edgeId;

	@Column
	String label;

	@Column
	double weight;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="gid")
	Graph graph;

	@Column
	String vertex1;
	
	@Column
	String vertex2;	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({
	  @JoinColumn(name = "vertLabel", insertable = false, updatable = false),
	  @JoinColumn(name = "graphId", insertable = false, updatable = false)
	})
	Vertex v1;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({
	  @JoinColumn(name = "vertLabel", insertable = false, updatable = false),
	  @JoinColumn(name = "graphId", insertable = false, updatable = false)
	})
	Vertex v2;
	
	public Edge(Vertex v, Vertex w) {

		this.v1 = v;
		this.v2 = w;

	}

	public Edge(Vertex v, Vertex w, String label) {

		this.v1 = v;
		this.v2 = w;

		this.label = label;
	}

	public Edge() {
	
	}

	public Edge(Vertex v, Vertex w, String label, double weight) {

		this.v1 = v;		
		this.v2 = w;

		this.label = label;
		this.weight = weight;
	}

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public long getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(long id) {
        this.edgeId = id;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

	public void setV1(Vertex v1) {
        this.v1 = v1;
    }


	public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public String getVertex1() {
        return vertex1;
    }

    public String getVertex2() {
        return vertex2;
    }

	public void setVertex1(String vertex1) {
        this.vertex1 = vertex1;
    }


	public void setVertex2(String vertex2) {
        this.vertex2 = vertex2;
    }
	
    public double getWeight() {
        return weight;
    }

    public String getLabel() {
        return label;
    }


}