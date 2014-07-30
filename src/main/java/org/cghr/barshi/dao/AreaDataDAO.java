package org.cghr.barshi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.db.data.entity.AreaDataEntity;

public class AreaDataDAO extends AbstractDatabaseDAO<AreaDataEntity> {
	private static AreaDataDAO areaDao = null;
	
	private AreaDataDAO() {
		super();
	}
	
	public static AreaDataDAO getInstance() {
		if(areaDao == null) {
			areaDao = new AreaDataDAO();
		}
		
		return areaDao;
	}
	
	public List<AreaDataEntity> getAllAreas() {
		EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createDataEntityManager();
		
		Query query = entityManager.createQuery("SELECT area FROM AreaDataEntity area", AreaDataEntity.class);
		JPAUtils.getInstance().setEager(query);
		
		List<AreaDataEntity> areaList = query.getResultList();
		
		entityManager.close();
		
		return areaList;
	}
	
	public Integer getNewAreaId() {
		List<AreaDataEntity> areaList = getAllAreas();
		
		int max = 0;
		
		for(AreaDataEntity area : areaList) {
			if(area.getId() > max) {
				max = area.getId();
			}
		}
		
		return max + 1;
	}

	@Override
	protected Class getGenericClass() {
		return AreaDataEntity.class;
	}
}
