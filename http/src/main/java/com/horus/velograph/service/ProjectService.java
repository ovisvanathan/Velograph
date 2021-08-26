package com.horus.velograph.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.horus.velograph.entity.Employee;
import com.horus.velograph.entity.Project;

public class ProjectService {

	public List getProjects(int empId) {
		// TODO Auto-generated method stub
		List<Project> projects = null;
	//	Scanner sc=new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
	   	entitymanager.getTransaction( ).begin( );
	   	
	   	System.out.println("Enter Employee ID");
	   	
	 //  	int eid=sc.nextInt();
	   	
	   	Employee emp = entitymanager.find(Employee.class, empId);

	   	System.out.println("Employee: "+emp.toString());
	   	projects = emp.getProjects();
	   	
	   	Iterator<Project> ps=projects.iterator();
	   	
	   	int count=0;
	   	
	   	while(ps.hasNext())
	   	{
	   		System.out.println("Project "+(++count)+" : "+ps.next().toString());
	   	}
	   		
	   	
		entitymanager.getTransaction( ).commit( );
		
		
		entitymanager.close();
		emfactory.close();
	   	
		return projects;
	   	
	}

}
