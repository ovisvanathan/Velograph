package com.horus.velograph.util;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

import com.horus.velograph.model.*;

import org.hibernate.Session;
import org.hibernate.MultiIdentifierLoadAccess;

import com.horus.velograph.service.DAOService;

/**
 * Entity implementation class for Entity: Client
 *
 */
public class Utils  {

		public static int HEADS = 0;
		public static int TAILS = 1;

		public static Vertex mv(String id) {
			return new Vertex(id);	
		}

		public static Vertex [] mv2(String id1, String id2) {
			return new Vertex [] { new Vertex(id1), new Vertex(id2) };	
		}
		
		static Random randomNum = new Random();
		
		public static int flip(){
			
			int result = randomNum.nextInt(2);

			if(result == 0){
				return HEADS;
			}else{
				return TAILS;
			}
		}

	
		public static Stream<String> flattenIfArray(Graph g, Object [] x) {

			List lv2 = new ArrayList(); 
			List lv4 = new ArrayList();
			for(Object xob : x) {
				if(xob.getClass().isArray()) {
					Object [] sbs = (Object []) xob;
					
					for(Object kob : sbs) {
						
						String skb1 = (String) kob;
						
						Vertex vt1 = g.toVertex(skb1);
						
						lv2 = vt1.children();
						lv4.addAll(lv2);
					}				
				}
				else {
					lv4.add(xob);
				}				
			}
			
			return lv4.stream();
		}

   
}
