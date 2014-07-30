package org.cghr.barshi.db.data.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.cghr.barshi.db.entity.Team;
import org.cghr.barshi.db.entityinterface.TeamInterface;

@Entity
@Table(name = "team")
public class TeamDataEntity {
	@Id
	@Column(name = "id")
	private Integer id = null;
	
	@Embedded
	private Team team = new Team();

	@OneToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "teamuser", joinColumns = {
		@JoinColumn(name = "teamId", referencedColumnName = "id")
	}, inverseJoinColumns = {
		@JoinColumn(name = "userId", referencedColumnName = "id")
	})
	private HashSet<UserDataEntity> surveyors = null;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "assignment", joinColumns = {
			@JoinColumn(name = "teamId", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "areaId", referencedColumnName = "areaId")
	})
	private HashSet<AreaDataEntity> areas = null;
	
	protected TeamDataEntity() {
		super();
	}
	
	public TeamDataEntity(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return team.getName();
	}

	public void setName(String name) {
		team.setName(name);
	}
	
	public void addSurveyor(UserDataEntity surveyor) {
		surveyors.add(surveyor);
	}
	
	public void removeSurveyor(UserDataEntity surveyor) {
		surveyors.remove(surveyor);
	}
	
	public Set<UserDataEntity> getSurveyors() {
		HashSet<UserDataEntity> surveyorSet = new HashSet<UserDataEntity>();
		
		if(surveyors != null) {
			surveyorSet.addAll(surveyors);
		}
		
		return surveyorSet;
	}
	
	public void addArea(AreaDataEntity area) {
		areas.add(area);
	}
	
	public void removeArea(AreaDataEntity area) {
		areas.remove(area);
	}
	
	public Set<AreaDataEntity> getAreas() {
		HashSet<AreaDataEntity> areaSet = new HashSet<AreaDataEntity>();
		
		if(areas != null) {
			areaSet.addAll(areas);
		}
		
		return areaSet;
	}
}
