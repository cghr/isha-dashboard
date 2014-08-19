package org.cghr.barshi.db;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.openjpa.persistence.OpenJPAEntityManagerSPI;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.apache.openjpa.persistence.OpenJPAQuery;
import org.apache.openjpa.persistence.jdbc.FetchMode;
import org.apache.openjpa.persistence.jdbc.JDBCFetchPlan;

public class JPAUtils {
	
	private static JPAUtils jpaUtils = null;
	
	private JPAUtils() {
		super();
	}
	
	public static JPAUtils getInstance() {
		if(jpaUtils == null) {
			jpaUtils = new JPAUtils();
		}
		
		return jpaUtils;
	}
	
	public void setEager(Query query) {
		OpenJPAQuery jpaQuery = OpenJPAPersistence.cast(query);
		JDBCFetchPlan fetchPlan = (JDBCFetchPlan) jpaQuery.getFetchPlan();
		fetchPlan.setEagerFetchMode(FetchMode.JOIN);
		fetchPlan.setSubclassFetchMode(FetchMode.PARALLEL);
	}
	
	public Connection getDataDatabaseConnection() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();
		OpenJPAEntityManagerSPI spi = (OpenJPAEntityManagerSPI)entityManager.getDelegate();
		return (Connection) spi.getConnection();
	}
	
	public Connection getReportsDatabaseConnection() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createReportsEntityManager();
		OpenJPAEntityManagerSPI spi = (OpenJPAEntityManagerSPI)entityManager.getDelegate();
		return (Connection) spi.getConnection();
	}
}
