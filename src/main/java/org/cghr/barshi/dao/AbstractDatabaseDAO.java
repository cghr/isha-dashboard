package org.cghr.barshi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.entity.Area;

public abstract class AbstractDatabaseDAO<T> implements DAO<T> {
	T t;

	@Override
	public void create(T entity) {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		entityManager.persist(entity);
		
		transaction.commit();
		
		entityManager.close();
	}

	@Override
	public T read(Object id) {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		Class clazz = null;
		clazz = this.getGenericClass();
		
		T t = (T) entityManager.find(clazz, id);
		
		if(t != null) {
			entityManager.detach(t);
		}
		
		entityManager.close();
		
		return t;
	}

	@Override
	public void update(T entity) {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		entityManager.merge(entity);
		
		transaction.commit();
		
		entityManager.detach(entity);
		
		entityManager.close();
	}

	@Override
	public void delete(Object id) {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		
		T t = (T) entityManager.find(getGenericClass(), id);
		
		entityManager.remove(t);
		
		transaction.commit();
		
		entityManager.close();
	}
	
	protected abstract Class getGenericClass();
}
