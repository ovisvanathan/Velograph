package com.horus.velograph.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.inject.Inject;

import com.horus.velograph.model.Graph;
import com.horus.velograph.api.User;

public class GraphService<T> {

	@Inject
	DAOService daos;

	public List getGraphs(int clientId) {

		return getGraphs(clientId, null);
	
	}

	public List getGraphs(Integer clientId, Integer projectId) {
		// TODO Auto-generated method stub
		List<Graph> graphs = null;
	//	Scanner sc=new Scanner(System.in);
		
		EntityManager em = daos.getEntityManager();
	   	
	   	System.out.println("Enter Employee ID");
	   	
	 //  	int eid=sc.nextInt();
	   	
	   	User emp = em.find(User.class, clientId);

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
		
		
		em.close();
	   	
		return pGraphs;
	   	
	}

	public List<Graph> getGraphs() {
				
			EntityManager em = daos.getEntityManager();
			return em.createQuery("Select t from " + Graph.class.getSimpleName() + " t").getResultList();

	}
	
	public Graph getGraphById(Long id) {
			EntityManager em = daos.getEntityManager();
			return em.find(Graph.class, id);		
	}

}
