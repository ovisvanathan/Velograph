package com.horus.velograph.model;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.CascadeType;
import javax.persistence.Transient;
import javax.persistence.JoinColumns;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import com.horus.velograph.util.Utils;
import com.horus.velograph.service.DAOService;
import com.horus.velograph.api.User;

import javax.inject.Inject;
import javax.inject.Named;

@Entity
public class Graph<V extends Vertex, E extends Edge> {

	public static final String BUILD_VERTICES_MAP = "BUILD_VERTICES_MAP";
	
	public static final int MAX_VERTEX_LIMIT = 100;
	
	public enum StreamTraversal {
		BFS, // BREADTH_FIRST,
		DFS // DEPTH_FIRST
	};
	
	String [] graphTypes = {
		"DIRECTED",
		"WEIGHTED",
		"DIRECTED_WEIGHTED",
		"UNDIRECTED", 
		"UNDIRECTED_WEIGHTED",
		"NONE"
	};
	
	int vertcount;
	
	public enum GraphType {
		DIRECTED(10),
		WEIGHTED(20),
		DIRECTED_WEIGHTED(30),
		UNDIRECTED(40),
		UNDIRECTED_WEIGHTED(50),
		NONE(60);
		
	   private final int value;

		private GraphType(int value) {
			this.value = value;
		}
	
		int getValue() {
			return value;
		}
		
		
		public String toString() {
			return name();
		}
	};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gid;

    @OneToMany(mappedBy = "graph", cascade = CascadeType.ALL, targetEntity=Vertex.class)
//	@OneToMany(fetch = FetchType.LAZY)
 // @JoinColumns({ @JoinColumn(name = "T01_VERT_ID"), //, 
//    referencedColumnName = "vertId"),
//  @JoinColumn(name = "T01_GPH_ID")})
  //, referencedColumnName = "graphId")}) 	
	  protected List<V> vertices = new LinkedList();

    @OneToMany(mappedBy = "edgeId", cascade = CascadeType.ALL, targetEntity=Edge.class)
    protected List<E> edgesList = new ArrayList();

	@Column
    public int edgesCount;

	@Column
	String name;

	@Transient
	protected Map<String, V> verticesMap;


	@Column
	String rootVertex;

	public Graph(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="eid")
	User user;

//	enum GraphType {
//		DIRECTED,
//		UNDIRECTED
//	};

	@Enumerated(EnumType.ORDINAL)
	GraphType graphType;
	
	@Inject @Named("velograph.properties")
	Properties configProps; 
	
	@Inject
	DAOService daos;	
	
	public int getGraphType() {
		return this.graphType.getValue();
	}
	
	public void setGraphType(GraphType type) {
		this.graphType = type;
	}
	
	public String getGraphTypeStr() {
		return this.graphType.name();
	}
	
	public void setGraphType(String typeName) {
		GraphType [] gtypes = GraphType.values();
		
		for(GraphType g : gtypes) {
			if(GraphType.valueOf(typeName) == g)
				setGraphType(g);
				break;
		}
	}
	
    public long getGid() {
        return gid;
    }

    public void setGid(long id) {
        this.gid = id;
    }

    public String getName() {
        return name;
    }

    public void setGid(String name) {
        this.name = name;
    }

   public int getNumVertices() {
        return this.vertices.size();
    }

    public int getNumEdges() {
        return this.edgesCount;
    }

	public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Vertex getRootVertex() {
        return this.verticesMap.get(this.rootVertex);
    }

    public void setRootVertex(String rootVertex) {
        this.rootVertex = rootVertex;
    }

    public void setRoot(Vertex root) {
        this.rootVertex = root.getVertLabel();
    
		buildVerticesMap();
	}


    public List<E> getEdgesList() {
        return this.edgesList;
    }

    public void setEdgesList(List<E> edgesList) {
        this.edgesList = edgesList;
    }

    private void validateVertex(V v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v.toString() + " is not a vertex");
    }

    public int degree(V v) {
        validateVertex(v);
        return GETVERTEX(v).getEdgeVertices().size();
    }

	protected Vertex GETVERTEX(V v) {
		return vertices.get(vertices.indexOf(v));
	}

//    public void addEdge(String vs1, String vs2)  throws Exception {
//		addEdge((V)Utils.mv(vs1), (V)Utils.mv(vs2));
//	}

	/*
	 *
	 */
    public void addEdge(V v, V w)  throws Exception {
      
		if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);

        if (!hasEdge(v, w)) edgesCount++;	
		
		Edge e2 = new Edge();
		e2.setGraph(this);
		if(this.graphType != GraphType.DIRECTED) {

				e2.setVertex1(w.getVertLabel());				
				e2.setVertex2(v.getVertLabel());				
				w.getEdgeVertices().add(v.getVertLabel());
		}

			e2.setVertex1(v.getVertLabel());				
			e2.setVertex2(w.getVertLabel());				
			
			GETVERTEX(v).getEdgeVertices().add(w.getVertLabel());			
			edgesList.add( (E) e2);
		
    }

    public void addVertex(V v) throws Exception {
        if (!hasVertex(v)) {
			v.setGraph(this);
			vertices.add(v);	
			return;
		}
	
	}

    public void addVertex(V ... vx) throws Exception {

		for(V v : vx) {
			if (!hasVertex(v)) {
				v.setGraph(this);
				vertices.add(v);
			}			
		}
	
	}

    public boolean hasEdge(V v, V w) {
        validateVertex(v);
        validateVertex(w);
	
		return vertices.get(vertices.indexOf(v)).getEdgeVertices().contains(w.getVertLabel());			
    }

    public boolean hasVertex(V v) {
        return vertices.contains(v);
    }
	
	/*
	 * Abstract Base implementation. Not applicable for all graphs.
	   only graph structures that are not dags should utilize this method.
	   DAGs have parent-child structure built in.
	*/	
	protected void buildVerticesMap() {
	
		if(this.verticesMap == null) {	
			this.verticesMap = new HashMap();
		
			Map vmap = new HashMap();
			for(Vertex vx : vertices)	
				this.verticesMap.put(vx.getVertLabel(), (V) vx);
		}
	}
	
	public interface Visitor<V> {
	
		public void visit(V v);

		public void visit(V v, V p);

		public List<V> getVisited();

	}
	
	class PrintVisitor<V> implements Visitor<V> {
		
		List visitedNodes = new LinkedList();

		public void visit(V v, V p) {
		
		}

		public void visit(V v) {
			Vertex vx = (Vertex) v;
			System.out.println("vertex id = " + vx.getVertNum());		
			System.out.println("vertex label = " + vx.getVertLabel());
			System.out.println("vertex Data = " + vx.getVertexData());

			visitedNodes.add(vx);
		}
		
		public List<V> getVisited() {
			return visitedNodes;
		}

	}
	
	
	/*
	 *   Breadth-first traversal of Grapgh
	 */
	public List<Vertex> getChildren(StreamTraversal vst, Visitor visitor) {
	
		List list = STRMTraversal.traverse(this, StreamTraversal.BFS, new PrintVisitor());

		list = STRMTraversal.traverse(this, StreamTraversal.BFS, new PrintVisitor());
	
		return list;
	}

	public List<Vertex> getChildren(StreamTraversal vst) {
	
		List list = STRMTraversal.traverse(this, vst, new PrintVisitor());
	
		return list;
	}

	public List<Vertex> getChildren() {
	
		StreamTraversal svt = StreamTraversal.BFS;

		return getChildren(svt);
	
	//	List list = STRMTraversal.traverse(this, StreamTraversal.BFS, new PrintVisitor());

	//	list = STRMTraversal.traverse(this, StreamTraversal.BFS, new PrintVisitor());
	
	}
	
	static class STRMTraversal { 
	
		public static List traverse(Graph g, StreamTraversal type, Visitor visitor) {
		
			if(type == StreamTraversal.BFS) {
				BFSTraversal.traverse(g, visitor);
			} else {
				DFSTraversal.traverse(g, visitor);
			}

			return visitor.getVisited();

		}
				
	}
	
	static class BFSTraversal extends STRMTraversal {

			static Map<Vertex, Integer> visitedMap = new HashMap();

			public static void traverse(Graph g, Visitor v) {
				traverseMe(g, v);
			}
			
			public static void traverseMe(Graph g, Visitor v) {
			
				List verts = g.getVertices();
	
				for(int x=0;x<verts.size();x++) {
					String vstr = ((Vertex)verts.get(x)).getVertLabel();				
					System.out.println("v:: " + vstr);
					
					if(vstr.equals("steps"))
						System.out.println("hurah step is at :: " +  x + " x = " + vstr);
						
				}
	
				System.out.println();
			
				for(int k=0;k<verts.size();k++) {
						Vertex vx = (Vertex) verts.get(k);
						visitVertex(vx, g, v);
						visitedMap.put(vx, 1);				
				}
			}

			public static void visitVertex(Vertex vx, Graph g, Visitor v) {

					if(!visitedMap.containsKey(vx)) {
				
						Set<String> edges = vx.getEdgeVertices();

						List verts = g.getVertices();

						System.out.println("vertex = " + vx.getVertLabel());		
	
						System.out.println("edges = " + edges);		
	
						List<Vertex> listOfEdgeVerts = new ArrayList();
						for(String eg : edges) {

							Vertex v2 = (Vertex) verts.get(verts.indexOf(new Vertex(eg)));

							v.visit(v2);

							listOfEdgeVerts.add(v2);					
						}
						
						
						for(int p=0;p<listOfEdgeVerts.size();p++) {
							Vertex vw = listOfEdgeVerts.get(p);							
							visitVertex(vw, g, v);	
							visitedMap.put(vw, 1);					
						}
											
						listOfEdgeVerts.clear();					
					
					
					}							
				
				}
		}
		

	static class DFSTraversal extends STRMTraversal {

			static Map<Vertex, Integer> visitedMap = new HashMap();
		
			public static void traverse(Graph g, Visitor v) {
				traverseMe(g, v);
			}

			public static void traverseMe(Graph g, Visitor v) {
			
				List verts = g.getVertices();
								
				for(int k=0;k<verts.size();k++) {
					Vertex vx = (Vertex) verts.get(k);
					visitVertex(vx, g, v);
					visitedMap.put(vx, 1);				
				}

				
			}
			
			public static void visitVertex(Vertex vx, Graph g, Visitor v) {

					if(!visitedMap.containsKey(vx)) {
				
						Set<String> edges = vx.getEdgeVertices();

						List verts = g.getVertices();
					
						List listOfEdgeVerts = new ArrayList();
						for(String eg : edges) {
													
							Vertex v2 = (Vertex) verts.get(verts.indexOf(new Vertex(eg)));

							v.visit(v2, vx);

							visitVertex(v2, g, v);	

							visitedMap.put(v2, 1);
		
						}
					
					}							
				
			}
		}

	public Map<String, V> getVerticesMap() {
		return this.verticesMap;
	}

	public List<V> getVertices() {
		return this.vertices;
	}

	public Vertex toVertex(String label) {		
		
		if(shouldBuildVerticesMap()) {	
			buildVerticesMap();
			return this.verticesMap.get(label);				
		} else {			
			vertcount++;
			return (Vertex) daos.findByName(Vertex.class, label);
		}
	
	}
	
	
	private boolean shouldBuildVerticesMap() {
	
		if(configProps.get(BUILD_VERTICES_MAP).equals("true") || vertcount > MAX_VERTEX_LIMIT)
				return true;
		else
				return false;				 
	
	}
	
	
	
	


}
