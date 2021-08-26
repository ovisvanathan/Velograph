package com.horus.velograph.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Column;

/**
 * Entity implementation class for Entity: Job
 *
 */
@Entity

public class Job implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private int job_id;
	private String designation;
	
	@Column
	private String jobCode;


	@OneToOne(mappedBy="job")
    private Employee employee;

	public Job() {
		super();
	}

	public Job(int job_id, String designation) {
		super();
		this.job_id = job_id;
		this.designation = designation;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "[ job_id="+job_id+" Designation="+designation+" ]";
	}
	
	
   
}
