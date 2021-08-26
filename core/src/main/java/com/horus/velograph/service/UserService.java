package com.horus.velograph.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.inject.Inject;

import com.horus.velograph.api.User;

public class UserService<T> {

	@Inject
	DAOService daos;
	
	public List getUsers(int clientId) {

		return getUsers(clientId, null);
	
	}

	public List getUsers(Integer clientId, Integer projectId) {
		// TODO Auto-generated method stub
		List<User> users = null;
	//	Scanner sc=new Scanner(System.in);
		
		EntityManager em = daos.getEntityManager();
   	
	   	System.out.println("Enter Employee ID");
	   	
	 //  	int eid=sc.nextInt();
	   	
	   	User emp = em.find(User.class, clientId);

	   	System.out.println("User: "+emp.toString());
	   	users = getUsers();
	   	
		List<User> pUsers = null;	
		if(projectId != null) {
		
			Iterator<User> ps= users.iterator();
			
			int count=0;
			
			while(ps.hasNext())
			{
				System.out.println("User "+(++count)+" : "+ps.next().toString());

				User g = ps.next();
			//	if(g.getProjectId() == projectId)
			
				pUsers.add(g);
			}

		} else
			pUsers = users;	   		
	   	
	//	entitymanager.getTransaction( ).commit( );
		
		
		em.close();
	   	
		return pUsers;
	   	
	}

	public List<User> getUsers() {
				
		EntityManager em = daos.getEntityManager();
		return em.createQuery("Select t from " + User.class.getSimpleName() + " t").getResultList();

	}
	
	public User getuserById(Long id) {
		EntityManager em = daos.getEntityManager();
		return em.find(User.class, id);		
	}

	public User getCurrentUser() {
			return new User();
	}


}
