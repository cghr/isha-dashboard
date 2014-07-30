package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.data.entity.TeamDataEntity;

public class TeamDataDAO extends AbstractDatabaseDAO<TeamDataEntity> {
	
	private static TeamDataDAO teamDao = null;
	
	protected TeamDataDAO() {
		super();
	}
	
	public static TeamDataDAO getInstance() {
		if(teamDao == null) {
			teamDao = new TeamDataDAO();
		}
		
		return teamDao;
	}
	
	public List<TeamDataEntity> getAllTeams() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();
		
		Query query = entityManager.createQuery("SELECT team FROM TeamDataEntity team", TeamDataEntity.class);
		
		List<TeamDataEntity> teamList = query.getResultList();
		
		entityManager.close();
		
		return teamList;
	}
	
	public int getNewTeamId() {
		List<TeamDataEntity> teamList = getAllTeams();
		
		int max = 0;
		for(TeamDataEntity team : teamList) {
			if(team.getId() > max) {
				max = team.getId();
			}
		}
		
		return max + 1;
	}

	@Override
	protected Class getGenericClass() {
		return TeamDataEntity.class;
	}

}
