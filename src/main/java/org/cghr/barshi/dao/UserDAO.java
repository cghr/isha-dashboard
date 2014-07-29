package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.entity.User;

public class UserDAO extends AbstractDatabaseDAO<User> {
	private static UserDAO userDao = null;

	private UserDAO() {
		super();
	}

	public static UserDAO getInstance() {
		if (userDao == null) {
			userDao = new UserDAO();
		}

		return userDao;
	}

	@Override
	protected Class getGenericClass() {
		return User.class;
	}
	
	public List<User> getAllUsers() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		Query query = entityManager.createQuery("SELECT user FROM User user", User.class);
		JPAUtils.getInstance().setEager(query);
		
		List<User> userList = query.getResultList();
		
		entityManager.close();
		
		return userList;
	}
	
	public int getNewUserId() {
		List<User> userList = getAllUsers();
		
		int max = 0;
		
		for(User user : userList) {
			if(user.getId() > max) {
				max = user.getId();
			}
		}
		
		return max + 1;
	}
}
