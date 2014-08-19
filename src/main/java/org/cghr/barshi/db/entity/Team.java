package org.cghr.barshi.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.cghr.barshi.db.entityinterface.TeamInterface;

@Embeddable
public class Team implements TeamInterface {
	
	@Transient
	private Integer id = null;
	
	@Column(name = "name")
	private String name = null;
	
	public Team() {
		super();
	}
	
	public Team(int id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
