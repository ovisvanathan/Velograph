package com.horus.velograph.util;

public interface HorusCreator {

	public Object get(String name);
	
	public Object get(java.io.InputStream xmlSource);
	
	public Object get(String name, Object ... args);


}