package org.cghr.barshi.dao;

public interface DAO<T> {
	public void create(T entity);
	
	public T read(Object id);
	
	public void update(T entity);
	
	public void delete(Object id);
}
