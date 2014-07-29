package org.cghr.barshi.entity;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class PojoTest {
	@Test
	public void testUserEquals() {
		HashSet<User> set = new HashSet<User>();
		
		set.add(new User(1));
		
		Assert.assertTrue(set.contains(new User(1)));
	}
	
	@Test
	public void testAreaEquals() {
		HashSet<Area> set = new HashSet<Area>();
		
		set.add(new Area(1));
		
		Assert.assertTrue(set.contains(new Area(1)));
	}
}