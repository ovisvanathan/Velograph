package com.horus.velograph.api;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.CascadeType;

import com.horus.velograph.model.*;


/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int eid;
	
	@Column
	private String username;


	//private int job_id;
	@Column
	private String password;

	
	@Column
	private String role;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="graph_id")
    private List<Graph> graphs;			
		
	public User() {
		super();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return new Integer(eid).toString();
	}

	public String getUsername() {
		return username;
	}

	public String getNickname() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addGraph(Graph g) {
		if(graphs == null)
			graphs = new ArrayList();
		
		graphs.add(g);
	}


	public List<Graph> getGraphs() {
		return graphs;
	}

	public void setGraphs(List<Graph> graphs) {
		this.graphs = graphs;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[  username="+username+"  Pwd="+password+"]";
	}
	
	
	
	
   
}
