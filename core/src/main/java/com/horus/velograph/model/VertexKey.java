package com.horus.velograph.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class VertexKey implements java.io.Serializable {

	   private static final long serialVersionUID = -1176248537673293674L;

       public Graph graph;

       public String vertLabel;

		public VertexKey() {

		}


		public VertexKey(String name, Graph graph) {
			vertLabel = name;
			graph = graph;
		}
		
		
		public String getVertLabel() {
			return vertLabel;
		}

   }