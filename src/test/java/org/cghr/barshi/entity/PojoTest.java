package org.cghr.barshi.entity;

import java.util.HashSet;

import org.cghr.barshi.db.data.entity.AreaDataEntity;
import org.cghr.barshi.db.data.entity.UserDataEntity;
import org.junit.Assert;
import org.junit.Test;

public class PojoTest {
	@Test
	public void testUserEquals() {
		HashSet<UserDataEntity> set = new HashSet<UserDataEntity>();
		
		set.add(new UserDataEntity(1));
		
		Assert.assertTrue(set.contains(new UserDataEntity(1)));
	}
	
	@Test
	public void testAreaEquals() {
		HashSet<AreaDataEntity> set = new HashSet<AreaDataEntity>();
		
		set.add(new AreaDataEntity(1));
		
		Assert.assertTrue(set.contains(new AreaDataEntity(1)));
	}
}