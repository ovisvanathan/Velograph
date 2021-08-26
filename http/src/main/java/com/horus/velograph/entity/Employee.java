package com.horus.velograph.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;


/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity

public class Employee implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private int eid;
	private String ename;

	@Column
	private double salary;
	
	@Column
	int age;
	

	@Column	
	String phoneNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="did")
    private Department department;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="job_id")
    private Job job;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="eid")
    private List<Project> projects;
	

	public Employee() {
		super();
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(int eid, String ename, double salary, Job job,
			Department department) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;
		this.job = job;
		this.department = department;
	}
	

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ id="+eid+" ename="+ename+" salary="+salary+" job="+job.toString()+" Department="+department+"]";
	}
	
	
	
	
   
}
