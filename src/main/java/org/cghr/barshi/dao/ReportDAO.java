package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.reports.entity.ReportEntity;

public class ReportDAO extends AbstractDatabaseDAO<ReportEntity> {
	
	private static ReportDAO teamDao = null;
	
	protected ReportDAO() {
		super("barshi-dashboard");
	}
	
	public static ReportDAO getInstance() {
		if(teamDao == null) {
			teamDao = new ReportDAO();
		}
		
		return teamDao;
	}
	
	public List<ReportEntity> getAllTeams() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createReportsEntityManager();
		
		Query query = entityManager.createQuery("SELECT report FROM ReportEntity report", ReportEntity.class);
		
		List<ReportEntity> reportList = query.getResultList();
		
		entityManager.close();
		
		return reportList;
	}
	
	@Override
	protected Class getGenericClass() {
		return ReportEntity.class;
	}

}
