package com.horus.velograph.service;

/*
 * Copyright (C) 2015 Hannes Dorfmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
import java.util.function.Function;

import com.horus.velograph.util.*;
import com.horus.velograph.entity.Gift;
import com.horus.velograph.util.HorusFactoryProcessor;

import javax.inject.Inject;

import java.util.List; 

import java.util.function.Supplier; 
import java.util.function.Function; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The service class
 *
 * @author Nitin Prakash
 */ 

@Factory("HorusFactory")
public class GiftService<T> {
 
//	@Inject
//	@PersistenceUnit()
    static EntityManager em; 

	static EntityManagerFactory emfactory;

	static {
	
			emfactory = Persistence.createEntityManagerFactory( "Tutorial" );
			em = emfactory.createEntityManager( );

	}
	
	public EntityManager getEntityManager() {
		return em;
	}

	/*
	EntityRunner<S,T,R> er = new EntityRunner();
	
	Function<T,R> f5 = (a) -> { em.persist(a); return (R) a; };

	Supplier<T> sg = () -> { return addGift(); };
	
	public T newEntity() {

		er.run3(sg, f5);

	//	er.setRunFunction(sg, f5);
		return (T) er.wrap();
	}
	*/
	
	Class getEntityClass() {
		return Gift.class;
	}

	public Gift addGift() {	
				
		Gift g = (Gift) HorusFactoryProcessor.getObjectFor(this.getClass(), this.getEntityClass().getSimpleName());
		
		em.persist(g);
	
		return (Gift) g;
	}		

	public Gift getGift(long id) {	
		return (Gift) em.find(Gift.class, id);
	}		

	public List getGifts() {
	//	return QuarkusUtils.allQuery(em, Gift.class, ).getResultList();		
	    return em.createQuery("Select t from " + Gift.class.getSimpleName() + " t").getResultList();
   
	}

	public List getAllGifts() {
	//	return QuarkusUtils.allQuery(em, Gift.class, ).getResultList();		
	    return em.createQuery("Select t from " + Gift.class.getSimpleName() + " t").getResultList();
   
	}	

	public Gift getGift(int id) {	
		return (Gift) em.find(Gift.class, id);
	}		

	public Gift addGift(Gift gift) {	
		em.persist(gift);		
		return gift;
	}		

	public Gift updateGift(Gift gift) {	
		Gift result = em.find(Gift.class, gift.getId());
        if (result != null) {
            em.persist(result);
        }

		return result;					
	}		

	public void deleteGift(long id) {	

		Gift result = em.find(Gift.class, id);
        if (result != null) {
			em.remove(result.getId());		
        }
			
	}		

}