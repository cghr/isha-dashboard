package org.cghr.barshi.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.cghr.barshi.dao.TeamDAO;
import org.cghr.barshi.dao.UserDAO;
import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.db.data.entity.Area;
import org.cghr.barshi.db.data.entity.Team;
import org.cghr.barshi.db.data.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	private Integer userId = null;
	
	@Before
	public void setup() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		userId = UserDAO.getInstance().getNewUserId();
		User user = new User(userId);

		user.setName("Sagar");
		user.setUsername("sagpatke");
		user.setClearPassword("MyClearPassword");
		user.setRole("user");

		entityManager.persist(user);
		
		transaction.commit();
		
		entityManager.close();
	}

	@Test
	public void testJPAConnectivity() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();

		TypedQuery<Area> query = entityManager.createQuery("SELECT a FROM Area a", Area.class);
		JPAUtils.getInstance().setEager(query);

		List<Area> areaList = query.getResultList();
		entityManager.close();

		Assert.assertNotNull(areaList);
		Assert.assertNotEquals(0, areaList.size());
	}

	@Test
	public void testEquality() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();

		User expectedUser = new User(userId);
		expectedUser.setName("Ravi");
		expectedUser.setUsername("ravitez");
		expectedUser.setClearPassword("Ravi's Password");
		expectedUser.setRole("user");

		entityManager.merge(expectedUser);
		
		transaction.commit();
		
		User actualUser = entityManager.find(User.class, userId);
		
		entityManager.detach(actualUser);
		
		entityManager.close();

		Assert.assertEquals(expectedUser.getName(), actualUser.getName());
	}
	
	@Test
	public void testTeamJPA() {
		List<Team> teamList = TeamDAO.getInstance().getAllTeams();
		
		Assert.assertNotNull(teamList);
		Assert.assertNotEquals(teamList.size(), 0);
		
		for(Team team : teamList) {
			Set<User> surveyors = team.getSurveyors();
			System.out.println("Team: " + team.getName());
			for(User surveyor : surveyors) {
				System.out.println("|- " + surveyor.getUsername());
			}
			
			Set <Area> areas = team.getAreas();
			System.out.println("Team: " + team.getName());
			for(Area area : areas) {
				System.out.println("|- " + area.getName());
			}
		}
	}
	
	@After
	public void cleanup() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		User user = entityManager.find(User.class, userId);
		
		entityManager.remove(user);
		
		transaction.commit();
		
		entityManager.close();
	}
}
