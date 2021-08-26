package com.horus.velograph.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.Query;

import javax.inject.Inject;

import com.horus.velograph.model.Graph;

import com.picasso.paddle.annotation.Resource;

@Resource
@Transactional
public class DAOService<T> {

    @PersistenceContext(name="Tutorial")
	protected EntityManagerFactory emf;

	@Inject
    protected EntityManager em;  
		
	private DAOService() {
		em = getEntityManager();
	}
		
	public EntityManager getEntityManager() {
		return emf.createEntityManager();	
	}
	
    public T insertWithTransaction(T item) {

		em.getTransaction().begin();

		em.persist(item);

		em.getTransaction().commit();

        return item;
    }
    
    public T insertWithoutTransaction(T item) {
		em.persist(item);
        return item;
    }

    public T findByName(Class<T> kclass, String label) {

		T item = null;
		Query query = em.createQuery("SELECT u FROM " + kclass.getSimpleName() + " u WHERE u.vertLabel =: label");
		query.setParameter("label", label);
		try {
			item = (T) query.getSingleResult();
		} catch (Exception e) {
			// Handle exception
		}
		return item;		
    }

    public T findByName(Class<T> kclass, long id) {
		return em.find(kclass, id);
    }
	
	
	public List<T> getGraphs() {	
	   return em.createQuery("Select t from " + Graph.class.getSimpleName() + " t").getResultList();
	}

	public List<T> getGraphById(Long id) {	
		return (List<T>) em.find(Graph.class, id);
	}		
    
}