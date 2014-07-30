package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.db.data.entity.UserDataEntity;

public class UserDataDAO extends AbstractDatabaseDAO<UserDataEntity> {
	private static UserDataDAO userDao = null;

	private UserDataDAO() {
		super();
	}

	public static UserDataDAO getInstance() {
		if (userDao == null) {
			userDao = new UserDataDAO();
		}

		return userDao;
	}

	@Override
	protected Class getGenericClass() {
		return UserDataEntity.class;
	}
	
	public List<UserDataEntity> getAllUsers() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();
		
		Query query = entityManager.createQuery("SELECT a FROM UserDataEntity a", UserDataEntity.class);
		JPAUtils.getInstance().setEager(query);
		
		List<UserDataEntity> userList = query.getResultList();
		
		entityManager.close();
		
		return userList;
	}
	
	public int getNewUserId() {
		List<UserDataEntity> userList = getAllUsers();
		
		int max = 0;
		
		for(UserDataEntity user : userList) {
			if(user.getId() > max) {
				max = user.getId();
			}
		}
		
		return max + 1;
	}
}
