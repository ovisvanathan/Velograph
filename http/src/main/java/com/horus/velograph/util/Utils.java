package com.horus.velograph.util;

import com.horus.velograph.model.Vertex;
import java.util.Random;

public class Utils {

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


		
}
