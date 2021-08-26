package com.horus.velograph.util;

import java.util.function.Supplier; 
import java.util.function.Function; 
import javax.transaction.Transactional; 

public class EntityRunner<S, T, R> {
	
	Function<T, R> f;	
	Supplier<T> s;	
	
	public void run(Supplier<T> s) {
	
	}

	public void run2(Function<T, R> f) {
	
	}

	public void run3(Supplier<T> s, Function<T, R> f) {
		this.f = f;
		this.s = s;	
	}
	
	@Transactional 	
	public R wrap() {	 
		return (R) f.apply(s.get());
	 }
			
	
}