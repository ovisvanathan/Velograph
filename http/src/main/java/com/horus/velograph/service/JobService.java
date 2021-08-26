package com.horus.velograph.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.horus.velograph.entity.Employee;
import com.horus.velograph.entity.Job;

public class JobService {

	public Job findJob(int jobId) {
		
		
//		Scanner sc=new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
	   	entitymanager.getTransaction( ).begin( );
	   	
	   	System.out.println("Enter Job ID");
	   	
	   	Job job = entitymanager.find(Job.class, jobId);

	   	System.out.println("Job: "+job.toString());
	   	
	   Employee emp=job.getEmployee();
	   
	   System.out.println("Employee: "+emp.toString());
	   	
		entitymanager.getTransaction( ).commit( );
		
		entitymanager.close();
		emfactory.close();
	
		return job;
	   	
	}

}
