package org.cghr.barshi.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.cghr.barshi.db.entityinterface.TeamInterface;

@Embeddable
public class Team implements TeamInterface {
	@Column(name = "name")
	private String name = null;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
