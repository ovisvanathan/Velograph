package com.horus.velograph.model;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;

import javax.inject.Inject;

import junit.framework.TestCase;
import com.horus.velograph.service.DAOService;
import com.horus.velograph.api.User;
import com.horus.velograph.coll.*;

import com.horus.velograph.util.Utils;

@Entity
public class ModelTest<T, V extends Vertex> extends TestCase {


	@Inject
	private DAOService<T> daos;

	Graph cg;

	public ModelTest() {

		/*
		testSaveGraphTxn();
		testSaveGraphNoTxn();
		testCreateUser();

		testSaveDAG();
		testVisitDAG();
		*/

//		testCreateUserWithGraph();	
	}

	@Before
	public void setup() {
	}
		
	@Test
	public void testSaveGraphTxn() {

		try {
			
				User u = new User();
				u.setUsername("u112");
				u.setPassword("pws123");
				u.setRole("guest");

				Graph g = new Graph("GX1A");
				
				g.addEdge(Utils.mv("v1"), Utils.mv("v2"));
				g.addEdge(Utils.mv("v2"), Utils.mv("v3"));
				g.addEdge(Utils.mv("v1"), Utils.mv("v4"));
				g.addEdge(Utils.mv("v4"), Utils.mv("v5"));

				g.setUser(u);

				daos.insertWithTransaction((T)g);			

			} catch(Exception e) {
				e.printStackTrace();				
			}

		
	}
	
	@Test
	public void testSaveGraphNoTxn() {

		try {

			User u = new User();
			u.setUsername("u117");
			u.setPassword("pws654");
			u.setRole("admin");

			Graph g = new Graph("GX1B");
			
			g.addEdge(Utils.mv("r1"), Utils.mv("s2"));
			g.addEdge(Utils.mv("r2"), Utils.mv("s3"));
			g.addEdge(Utils.mv("r1"), Utils.mv("s4"));
			g.addEdge(Utils.mv("r4"), Utils.mv("s5"));

			g.setUser(u);

			daos.insertWithoutTransaction((T)g);			

		} catch(Exception e) {
				e.printStackTrace();
		}

				
	}

	@Test
	public void testSaveDAG() {

		System.out.println(" ################# testSaveDAG() $$$$$$$$$$$$$$$$$$$$$$$$ ");	
	
		try {

			User u = new User();
			u.setUsername("u117");
			u.setPassword("pws654");
			u.setRole("admin");

			Graph g = new DAG("GX124");

			Vertex root = Utils.mv("root");
			g.addVertex(root);

			Vertex c1 = Utils.mv("ns126");
			g.addVertex(c1);

			Vertex c2 = Utils.mv("ns127");
			Vertex c3 = Utils.mv("ns128");
			Vertex fg4 = Utils.mv("knf29");			
			Vertex fg5 = Utils.mv("knf29");			
			Vertex sg2 = Utils.mv("rkp112");			
			Vertex sg3 = Utils.mv("rkp179");			
			Vertex sg4 = Utils.mv("mn218");			
			Vertex ym21 = Utils.mv("aps222");			

			g.addVertex(c2, c3, fg4, fg5, sg2, sg3, sg4, ym21);

			g.addVertex(sg4, ym21);

			g.addEdge(root, c1);

			g.addEdge(root, c2);

			root.addChild(c3);

			c1.addChild(fg4);
			
			g.addEdge(c1, fg5);
			
			c2.addChild(sg2);
			
			g.addEdge(c2, sg3);

			c2.addChild(sg4);
			
			System.out.println("c3 graph " + c3.getGraph());
			c3.addChild(ym21);
			
			g.setRoot(root);
			
			daos.insertWithTransaction((T)g);			

		} catch(Exception e) {
				e.printStackTrace();
		}

				
	}
	

	@Test
	public void testVisitDAGviaStream() {

		System.out.println(" ################# testVisitDAGviaStream(() $$$$$$$$$$$$$$$$$$$$$$$$ ");	

		try {

			User u = new User();
			u.setUsername("u117");
			u.setPassword("pws654");
			u.setRole("admin");

			Graph g = new DAG("GCR11");

			Vertex root = Utils.mv("root");
			g.addVertex(root);

			Vertex c1 = Utils.mv("ns126");
			g.addVertex(c1);

			Vertex c2 = Utils.mv("ns127");
			Vertex c3 = Utils.mv("ns128");
			Vertex fg4 = Utils.mv("knf29");			
			Vertex fg5 = Utils.mv("knf30");			
			Vertex sg2 = Utils.mv("rkp112");			
			Vertex sg3 = Utils.mv("rkp179");			
			Vertex sg4 = Utils.mv("mn218");			
			Vertex ym21 = Utils.mv("aps222");			

			g.addVertex(c2, c3, fg4, fg5, sg2, sg3, sg4, ym21);

			g.addVertex(sg4, ym21);

			g.addEdge(root, c1);

			g.addEdge(root, c2);

			root.addChild(c3);

			c1.addChild(fg4);
			
			g.addEdge(c1, fg5);
			
			c2.addChild(sg2);
			
			g.addEdge(c2, sg3);

			c2.addChild(sg4);
			
			c3.addChild(ym21);
			
			g.setRoot(root);
			
			daos.insertWithoutTransaction((T)g);			

			List<Vertex> nodes = g.getChildren();

			for(Vertex v : nodes)
				System.out.println(" VDAGSTREAM nodes = " + v.getVertLabel());

		} catch(Exception e) {
				e.printStackTrace();
		}
				
	}

	@Test
	public void testVisitDAGviaVisitor() {

		System.out.println(" ################# testVisitDAGviaVisitor() $$$$$$$$$$$$$$$$$$$$$$$$ ");	

		try {

			User u = new User();
			u.setUsername("u117");
			u.setPassword("pws654");
			u.setRole("admin");

			Graph g = new DAG("GMX92");

			Vertex root = Utils.mv("root");
			g.addVertex(root);

			Vertex c1 = Utils.mv("ns126");
			g.addVertex(c1);

			Vertex c2 = Utils.mv("ns127");
			Vertex c3 = Utils.mv("ns128");
			Vertex fg4 = Utils.mv("knf29");			
			Vertex fg5 = Utils.mv("knf30");			
			Vertex sg2 = Utils.mv("rkp112");			
			Vertex sg3 = Utils.mv("rkp179");			
			Vertex sg4 = Utils.mv("mn218");			
			Vertex ym21 = Utils.mv("aps222");			

			g.addVertex(c2, c3, fg4, fg5, sg2, sg3, sg4, ym21);

			g.addVertex(sg4, ym21);

			g.addEdge(root, c1);

			g.addEdge(root, c2);

			root.addChild(c3);

			c1.addChild(fg4);
			
			g.addEdge(c1, fg5);
			
			c2.addChild(sg2);
			
			g.addEdge(c2, sg3);

			c2.addChild(sg4);
			
			c3.addChild(ym21);
			
			g.setRoot(root);
			
			daos.insertWithoutTransaction((T)g);			

			visit(g);	
	

		} catch(Exception e) {
				e.printStackTrace();
		}

				
	}
	
	
	private void visit(Graph g) {
	
		Vertex vt = g.getRootVertex();
	
		visitNode(g, vt);
		
	}

	private void visitNode(Graph g, Vertex root) {
	
		Map<String, Vertex> vertsMap = g.getVerticesMap();
		Set<String> edgeLabels = root.getEdgeVertices();
	
		if(edgeLabels != null && edgeLabels.size()>0) {
		
			for(String ename : edgeLabels) {			
				Vertex edge = vertsMap.get(ename);			
				visitNode(g, edge);	
			}		
		}
			System.out.println(" got node :: " + root.getVertLabel());	
	
	}
	
	@Test
	public void testCreateUser() {

		User user = new User();
	
		user.setUsername("unas");
		user.setPassword("pass123");

		daos.insertWithTransaction((T) user);			
				
	}

	/*
	@Test
	public void testCollGraph() {

		cg = new CollectionsGraph("CGX15E");

		try {
				cg.addEdge(Utils.mv("r1"), Utils.mv("s2"));
				cg.addEdge(Utils.mv("r2"), Utils.mv("s3"));
				cg.addEdge(Utils.mv("r1"), Utils.mv("s4"));
				cg.addEdge(Utils.mv("r4"), Utils.mv("s5"));

				cg.addEdge(Utils.mv("s2"), Utils.mv("p5"));
				cg.addEdge(Utils.mv("s2"), Utils.mv("p6"));
				cg.addEdge(Utils.mv("s2"), Utils.mv("p7"));

				cg.addEdge(Utils.mv("p5"), Utils.mv("t2"));
				cg.addEdge(Utils.mv("p6"), Utils.mv("t5"));
				cg.addEdge(Utils.mv("p6"), Utils.mv("t6"));
				cg.addEdge(Utils.mv("p7"), Utils.mv("t7"));
				cg.addEdge(Utils.mv("p7"), Utils.mv("t8"));
				cg.addEdge(Utils.mv("p7"), Utils.mv("t9"));

				cg.addEdge(Utils.mv("s3"), Utils.mv("q3"));
				cg.addEdge(Utils.mv("s3"), Utils.mv("q4"));
				cg.addEdge(Utils.mv("s3"), Utils.mv("q5"));

				cg.addEdge(Utils.mv("q3"), Utils.mv("u6"));
				cg.addEdge(Utils.mv("q3"), Utils.mv("u7"));
				cg.addEdge(Utils.mv("q4"), Utils.mv("u8"));
				cg.addEdge(Utils.mv("q4"), Utils.mv("u9"));
				cg.addEdge(Utils.mv("q4"), Utils.mv("u3"));
				cg.addEdge(Utils.mv("q5"), Utils.mv("u12"));

				cg.addEdge(Utils.mv("s4"), Utils.mv("m4"));
				cg.addEdge(Utils.mv("s4"), Utils.mv("m5"));
				cg.addEdge(Utils.mv("s4"), Utils.mv("m6"));

				cg.addEdge(Utils.mv("m5"), Utils.mv("g2"));
				cg.addEdge(Utils.mv("m5"), Utils.mv("g3"));
				cg.addEdge(Utils.mv("m5"), Utils.mv("g4"));
				cg.addEdge(Utils.mv("m6"), Utils.mv("g5"));
				cg.addEdge(Utils.mv("m6"), Utils.mv("g6"));

				cg.addEdge(Utils.mv("s5"), Utils.mv("o6"));
				cg.addEdge(Utils.mv("s5"), Utils.mv("o7"));

				cg.addEdge(Utils.mv("o6"), Utils.mv("h4"));
				cg.addEdge(Utils.mv("o6"), Utils.mv("h5"));

				cg.addEdge(Utils.mv("q4"), Utils.mv("e7"));
				cg.addEdge(Utils.mv("q4"), Utils.mv("e8"));

				cg.addEdge(Utils.mv("g6"), Utils.mv("d12"));
				cg.addEdge(Utils.mv("g6"), Utils.mv("d19"));
				cg.addEdge(Utils.mv("g6"), Utils.mv("d26"));

				System.out.println(cg.getNumVertices());

				assertEquals(43, cg.getNumVertices());
				
				daos.insertWithoutTransaction((T)cg);			

			} catch(Exception e) {
				e.printStackTrace();				
			}

				
	}

	@Test
	public void testCollGraphTraverseBFS() {

			List<V> verts = cg.getVertices(CollectionsGraph.StreamTraversal.BREADTH_FIRST);
	
			for(V x : verts) {
				System.out.print(x.getVertLabel());
				System.out.print(" ,");
			}	
				System.out.println();
				
	}

	@Test
	public void testCollGraphTraverseDFS() {

			List<V> verts = cg.getVertices(CollectionsGraph.StreamTraversal.DEPTH_FIRST);
	
			for(V x : verts) {
				System.out.print(x.getVertLabel());
				System.out.print(" ,");
			}	
				System.out.println();

				
	}
		*/

	
	/*
	@Test
	public void testCreateUserWithGraph() {

		User user = new User();
	
		user.setUsername("unas");
		user.setPassword("pass123");
		
		Graph g1 = new Graph();
		
		g1.addEdge(Utils.mv("v1"), Utils.mv("v2"));
		g1.addEdge(Utils.mv("v2"), Utils.mv("v3"));
		
		Graph g2 = new Graph();
		
		g2.addEdge(Utils.mv("A1"), Utils.mv("A2"));
		g2.addEdge(Utils.mv("A2"), Utils.mv("A3"));
		
		
		user.addGraph(g1);
		user.addGraph(g2);
		

		daos.insertWithTransaction( (T) user);			
				
	}
	
	*/
	
	public static void main(String [] args) {
		new ModelTest();
	}

}