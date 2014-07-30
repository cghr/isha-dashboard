package org.cghr.barshi.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BarshiEntityManagerFactory {
	private static BarshiEntityManagerFactory barshiEntityManagerFactory = null;
	
	private static EntityManagerFactory dataEntityManagerFactory = null;
	private static EntityManagerFactory reportsEntityManagerFactory = null;
	
	private BarshiEntityManagerFactory() {
		super();
	}
	
	public static BarshiEntityManagerFactory getInstance() {
		if(barshiEntityManagerFactory == null) {
			barshiEntityManagerFactory = new BarshiEntityManagerFactory();
		}
		
		return barshiEntityManagerFactory;
	}
	
	public EntityManager createDataEntityManager() {
		if(dataEntityManagerFactory == null) {
			dataEntityManagerFactory = Persistence.createEntityManagerFactory("barshi-data");
		}
		
		EntityManager entityManager = dataEntityManagerFactory.createEntityManager();
		return entityManager;
	}
	
	public EntityManager createReportsEntityManager() {
		if(reportsEntityManagerFactory == null) {
			reportsEntityManagerFactory = Persistence.createEntityManagerFactory("barshi-data");
		}
		
		EntityManager entityManager = reportsEntityManagerFactory.createEntityManager();
		return entityManager;
	}
}
