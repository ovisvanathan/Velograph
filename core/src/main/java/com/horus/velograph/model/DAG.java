package com.horus.velograph.model;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.CascadeType;
import javax.persistence.Transient;
import javax.persistence.JoinColumns;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import com.horus.velograph.util.Utils;
import com.horus.velograph.service.DAOService;
import com.horus.velograph.api.User;


@Entity
public class DAG<V extends Vertex, E extends Edge> extends Graph<V, E> {

	public DAG(String name) {
		super(name);
	}
    private void validateVertex(V v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v.toString() + " is not a vertex");
    }

//    public void addEdge(String vs1, String vs2) throws Exception {
//			throw new Exception("not available. use addVertex(v, w)");
//	}
	    	
    public void addEdge(V v, V w) throws Exception {
      		
		edgesCount++;	
		
		w.setParentVertex(v.getVertLabel());
		GETVERTEX(v).getEdgeVertices().add(w.getVertLabel());			
		
    }

    public void addVertex(V v) throws Exception {

        if (!hasVertex(v)) {
			v.setGraph(this);
			vertices.add(v);
			return;
		}
				
	}

    public boolean hasEdge(V v, V w) {
        validateVertex(v);
        validateVertex(w);

		if(w.getParentVertex().equals(v.getVertLabel()))
			return true;

		return false;
    }

    public boolean hasVertex(V v) {
        return vertices.contains(v);
    }
	



}
