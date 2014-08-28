package org.cghr.barshi.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.cghr.barshi.dao.TeamDataDAO;
import org.cghr.barshi.dao.UserDataDAO;
import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.db.data.entity.AreaDataEntity;
import org.cghr.barshi.db.data.entity.TeamDataEntity;
import org.cghr.barshi.db.data.entity.UserDataEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	private Integer userId = null;
	
	@Before
	public void setup() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		userId = UserDataDAO.getInstance().getNewUserId();
		UserDataEntity user = new UserDataEntity(userId);

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
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();

		TypedQuery<AreaDataEntity> query = entityManager.createQuery("SELECT a FROM AreaDataEntity a", AreaDataEntity.class);
		JPAUtils.getInstance().setEager(query);

		List<AreaDataEntity> areaList = query.getResultList();
		entityManager.close();

		Assert.assertNotNull(areaList);
		Assert.assertNotEquals(0, areaList.size());
	}

	@Test
	public void testEquality() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();

		UserDataEntity expectedUser = new UserDataEntity(userId);
		expectedUser.setName("Ravi");
		expectedUser.setUsername("ravitez");
		expectedUser.setClearPassword("Ravi's Password");
		expectedUser.setRole("user");

		entityManager.merge(expectedUser);
		
		transaction.commit();
		
		UserDataEntity actualUser = entityManager.find(UserDataEntity.class, userId);
		
		entityManager.detach(actualUser);
		
		entityManager.close();

		Assert.assertEquals(expectedUser.getName(), actualUser.getName());
	}
}
