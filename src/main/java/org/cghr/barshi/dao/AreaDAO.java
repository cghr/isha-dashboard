package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.entity.Area;

public class AreaDAO extends AbstractDatabaseDAO<Area> {
	private static AreaDAO areaDao = null;
	
	private AreaDAO() {
		super();
	}
	
	public static AreaDAO getInstance() {
		if(areaDao == null) {
			areaDao = new AreaDAO();
		}
		
		return areaDao;
	}
	
	public List<Area> getAllAreas() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createEntityManager();
		
		Query query = entityManager.createQuery("SELECT area FROM Area area", Area.class);
		JPAUtils.getInstance().setEager(query);
		
		List<Area> areaList = query.getResultList();
		
		entityManager.close();
		
		return areaList;
	}
	
	public Integer getNewAreaId() {
		List<Area> areaList = getAllAreas();
		
		int max = 0;
		
		for(Area area : areaList) {
			if(area.getId() > max) {
				max = area.getId();
			}
		}
		
		return max + 1;
	}

	@Override
	protected Class getGenericClass() {
		return Area.class;
	}
}
