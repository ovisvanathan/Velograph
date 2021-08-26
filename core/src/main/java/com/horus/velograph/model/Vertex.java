package com.horus.velograph.model;

import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.OneToOne;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumns;
import javax.persistence.IdClass;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.MapsId;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.stream.Collectors;

import com.horus.velograph.util.Utils;


@Entity
@IdClass(VertexKey.class)
public class Vertex {

	/*
	@ElementCollection // 1
//    @CollectionTable(name = "vertex_edge") // , joinColumns = @JoinColumn(name = "id")) // 2
   @CollectionTable(name = "vertex_edge", joinColumns = { @JoinColumn(name = "T02_VERT_ID"), @JoinColumn(name = "T02_GPH_ID") })// 2
//	@JoinColumns({
//	  @JoinColumn(name = "vertLabel", insertable = false, updatable = false),
//	  @JoinColumn(name = "graphId", insertable = false, updatable = false)
//	}))
    @Column(name = "v_id") // 3
	*/
	@Transient
	Set<String> edgeVertices = new LinkedHashSet();

//    @EmbeddedId 
 //   private VertexKey vkey;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Edge e1;

//	@OneToOne(cascade = CascadeType.ALL)

	 @Id
	@Column(name="vert_id", length=64)
	 protected String vertLabel;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VertIdGenerator")
	@GenericGenerator(name = "VertIdGenerator",
			strategy = "com.horus.velograph.idgen.BigIntegerSequenceGenerator",
			parameters = {
				@Parameter(name = "sequence", value = "xyz_id_sequence")
			})
	@Column(name="vert_num", length=64)
	long vertNum;
	
	/* uncomment for string sequence */
	/*
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vert_seq")
    
	@GenericGenerator(
        name = "vert_seq", 
        strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @Column(name="vert_num")
	String vertNum;

	*/
	
	/* uncomment for date sequence */
	/*
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
        name = "book_seq", 
        strategy = "org.thoughts.on.java.generators.DatePrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = DatePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50")})
   
    @Column(name="vert_num")
	String vertNum;
	*/

	@Id
    @ManyToOne
    @JoinColumn
	Graph graph;
	
	@Column(length = 65535,columnDefinition="Text")
	String vertexData;

	@Transient
	Object modelData;

	@Column(name="parent_vert_id", length=64)	
	String parentVertex;

	public Vertex() {
	
	}
	
	public Vertex(String vertId) {
		this.vertLabel = vertId;
	}
	
    public Graph getGraph() {
        return this.graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;   
	}

	public void addChild(Vertex child) throws Exception {
		this.graph.addEdge(this, child);
	}

    public long getVertNum() {
        return this.vertNum;
    }

    public void setVertNum(long vertNum) {
        this.vertNum = vertNum;   
	}

	public String getVertLabel() {
		return this.vertLabel;
	}

	public String getVertexData() {
		return this.vertexData;
	}

	public void setVertexData(String vertexData) {
		this.vertexData = vertexData;
	}

	public Object getModelData() {
		return this.modelData;
	}

	public void setModelData(Object modelData) {
		this.modelData = modelData;
	}


	public String getParentVertex() {
		return this.parentVertex;
	}

	public void setParentVertex(String pv) {
		this.parentVertex = pv;
	}

    public Set<String> getEdgeVertices() {
        return this.edgeVertices;
    }

    public void setEdgeVertices(Set<String> items) {
        this.edgeVertices = items;
    }
	
	public boolean equals(Object other) {
								
		return this.getVertLabel().equals(((Vertex)other).getVertLabel());	
	
	}


	public List<Vertex> children() {
		
		Graph g = this.getGraph();
		
		return Stream.concat( Arrays.asList(this).stream(), 
										this.getEdgeVertices().stream()
														.map(x -> g.toVertex(x)).flatMap(
																		p -> p.children().stream()))
								.collect(Collectors.toList());
		
												
	}

}