package com.horus.velograph.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * Entity implementation class for Entity: Department
 *
 */
@Entity
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private int did;

	@Column
	private String dname;

	public Department() {
		super();
	}


	public Department(int did, String dname) {
		super();
		this.did = did;
		this.dname = dname;
	}


	public int getDid() {
		return did;
	}


	public void setDid(int did) {
		this.did = did;
	}


	public String getDname() {
		return dname;
	}


	public void setDname(String dname) {
		this.dname = dname;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return "[ id="+did+" dname="+dname+"]";
	}
	
	
   
}
