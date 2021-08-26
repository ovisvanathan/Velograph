package com.horus.velograph.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.horus.velograph.entity.Employee;

public class EmployeeService {


	 public Employee addEmployee(Employee employee)
	 {
		 
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );	   	
		entitymanager.getTransaction( ).begin( );
				
		entitymanager.persist(employee);
		return employee;
	 }

	 public Employee getEmployee(int employeeId)
	 {		 
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
		Employee dbEmployee =entitymanager.find(Employee.class, employeeId);
		System.out.println("Employee " + dbEmployee);		
		entitymanager.close();				
		return dbEmployee;
	 }

	 public List<Employee> getAllEmployees()
	 {		 
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
		Query q = entitymanager.createQuery("from Employee e");
        return q.getResultList();
	}

	 public Employee updateEmployee(Employee emp)
	 {		 
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
		
		int empId = emp.getEid();
		
		Employee dbEmployee =entitymanager.find(Employee.class, empId);
		System.out.println("Employee " + dbEmployee);	
		
		entitymanager.merge(emp);
			
		dbEmployee =entitymanager.find(Employee.class, empId);
		System.out.println("changed Employee " + dbEmployee);	
		
		entitymanager.close();				
		return dbEmployee;
	 }

	 public void deleteEmployee(int employeeId)
	 {		 
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "RivuChk_JPA" );
	   	EntityManager entitymanager = emfactory.createEntityManager( );
		Employee dbEmployee =entitymanager.find(Employee.class, employeeId);
		System.out.println("Employee " + dbEmployee);	
		
		entitymanager.remove(dbEmployee);
			
		dbEmployee =entitymanager.find(Employee.class, employeeId);
		System.out.println("no Employee " + dbEmployee);	
		
		entitymanager.close();				
	 }

}
