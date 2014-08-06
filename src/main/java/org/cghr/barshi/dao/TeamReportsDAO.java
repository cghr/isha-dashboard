package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;

public class TeamReportsDAO extends AbstractDatabaseDAO<TeamReportsEntity> {
	
	private static TeamReportsDAO teamDao = null;
	
	protected TeamReportsDAO() {
		super("barshi-dashboard");
	}
	
	public static TeamReportsDAO getInstance() {
		if(teamDao == null) {
			teamDao = new TeamReportsDAO();
		}
		
		return teamDao;
	}
	
	public List<TeamReportsEntity> getAllTeams() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createReportsEntityManager();
		
		Query query = entityManager.createQuery("SELECT team FROM TeamReportsEntity team", TeamReportsEntity.class);
		
		List<TeamReportsEntity> teamList = query.getResultList();
		
		entityManager.close();
		
		return teamList;
	}
	
	@Override
	protected Class getGenericClass() {
		return TeamReportsEntity.class;
	}

}
