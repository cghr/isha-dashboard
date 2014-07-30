package org.cghr.barshi.db.data.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {
	@Id
	@Column(name = "id")
	private Integer id = null;

	@Column(name = "name")
	private String name = null;

	@OneToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "teamuser", joinColumns = {
		@JoinColumn(name = "teamId", referencedColumnName = "id")
	}, inverseJoinColumns = {
		@JoinColumn(name = "userId", referencedColumnName = "id")
	})
	private HashSet<User> surveyors = null;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "assignment", joinColumns = {
			@JoinColumn(name = "teamId", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "areaId", referencedColumnName = "areaId")
	})
	private HashSet<Area> areas = null;
	
	protected Team() {
		super();
	}
	
	public Team(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addSurveyor(User surveyor) {
		surveyors.add(surveyor);
	}
	
	public void removeSurveyor(User surveyor) {
		surveyors.remove(surveyor);
	}
	
	public Set<User> getSurveyors() {
		HashSet<User> surveyorSet = new HashSet<User>();
		
		if(surveyors != null) {
			surveyorSet.addAll(surveyors);
		}
		
		return surveyorSet;
	}
	
	public void addArea(Area area) {
		areas.add(area);
	}
	
	public void removeArea(Area area) {
		areas.remove(area);
	}
	
	public Set<Area> getAreas() {
		HashSet<Area> areaSet = new HashSet<Area>();
		
		if(areas != null) {
			areaSet.addAll(areas);
		}
		
		return areaSet;
	}
}
