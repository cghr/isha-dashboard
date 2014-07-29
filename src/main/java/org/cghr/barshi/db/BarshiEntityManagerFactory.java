package org.cghr.barshi.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BarshiEntityManagerFactory {
	private static BarshiEntityManagerFactory barshiEntityManagerFactory = null;
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	private BarshiEntityManagerFactory() {
		super();
	}
	
	public static BarshiEntityManagerFactory getInstance() {
		if(barshiEntityManagerFactory == null) {
			barshiEntityManagerFactory = new BarshiEntityManagerFactory();
		}
		
		return barshiEntityManagerFactory;
	}
	
	public EntityManager createEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("barshi-dashboard");
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
}
