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

import com.horus.velograph.model.Graph;
import com.horus.velograph.entity.Project;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity
public class Client implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	private int eid;
	
	private String ename;
	//private int job_id;
	private String ecode;
	
	private int salary;
	
	@ManyToOne
    @JoinColumn(name="did")
    private Department department;
	
	@OneToOne
    @JoinColumn(name="job_id")
    private Job job;
	
	@OneToMany
    @JoinColumn(name="eid")
    private List<Project> projects;


	@OneToMany
    @JoinColumn(name="eid")
    private List<Graph> graphs;	
	
	public Client() {
		super();
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEcode() {
		return ecode;
	}

	public void setECode(String ecode) {
		this.ecode = ecode;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
/*
	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
*/

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

	public Client(int eid, String ename, double salary, Job job,
			Department department) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.ecode = ecode;
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
