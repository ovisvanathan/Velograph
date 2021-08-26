package com.horus.velograph.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

/**
 * Entity implementation class for Entity: Department
 *
 */
@Entity
public class Project implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	 private int p_id;
	private String title;
	
	@ManyToOne
    @JoinColumn(name="eid")
    private Employee client;
	
	@Column
	String projectCode;
	
	public Project() {
		super();
	}

	public Project(int p_id, String title, Employee client) {
		super();
		this.p_id = p_id;
		this.title = title;
		this.client = client;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String ProjectCode) {
		this.projectCode = ProjectCode;
	}

	public Employee getClient() {
		return client;
	}

	public void setClient(Employee client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "[Project ID="+p_id+" Title="+title+" ]";
	}
	
		
	
}
