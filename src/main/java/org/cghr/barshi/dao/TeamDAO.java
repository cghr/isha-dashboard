package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.data.entity.Team;

public class TeamDAO extends AbstractDatabaseDAO<Team> {
	
	private static TeamDAO teamDao = null;
	
	protected TeamDAO() {
		super();
	}
	
	public static TeamDAO getInstance() {
		if(teamDao == null) {
			teamDao = new TeamDAO();
		}
		
		return teamDao;
	}
	
	public List<Team> getAllTeams() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		Query query = entityManager.createQuery("SELECT team FROM Team team", Team.class);
		
		List<Team> teamList = query.getResultList();
		
		entityManager.close();
		
		return teamList;
	}
	
	public int getNewTeamId() {
		List<Team> teamList = getAllTeams();
		
		int max = 0;
		for(Team team : teamList) {
			if(team.getId() > max) {
				max = team.getId();
			}
		}
		
		return max + 1;
	}

	@Override
	protected Class getGenericClass() {
		return Team.class;
	}

}
