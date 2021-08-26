package com.horus.velograph.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.horus.velograph.model.Graph;
import com.horus.velograph.api.User;

public class GraphService {

	
	public List getGraphs(int clientId) {

		return getGraphs(clientId, null);
	
	}

	public List getGraphs(Integer clientId, Integer projectId) {
		// TODO Auto-generated method stub
		List<Graph> graphs = null;
	//	Scanner sc=new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Tutorial" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
	 //  	entitymanager.getTransaction( ).begin( );
	   	
	   	System.out.println("Enter Employee ID");
	   	
	 //  	int eid=sc.nextInt();
	   	
	   	User emp = entitymanager.find(User.class, clientId);

	   	System.out.println("User: "+emp.toString());
	   	graphs=emp.getGraphs();
	   	
		List<Graph> pGraphs = null;	
		if(projectId != null) {
		
			Iterator<Graph> ps= graphs.iterator();
			
			int count=0;
			
			while(ps.hasNext())
			{
				System.out.println("Graph "+(++count)+" : "+ps.next().toString());

				Graph g = ps.next();
			//	if(g.getProjectId() == projectId)
			
				pGraphs.add(g);
			}

		} else
			pGraphs = graphs;	   		
	   	
	//	entitymanager.getTransaction( ).commit( );
		
		
		entitymanager.close();
		emfactory.close();
	   	
		return pGraphs;
	   	
	}

//	public static List<Graph> getGraphs() {
//			   return em.createQuery("Select t from " + Graph.class.getSimpleName() + " t").getResultList();

//	}
	
//	public static Graph getGraphById(Long id) {
//		return (List<T>) em.find(Graph.class, id);
		
		
//	}

}
