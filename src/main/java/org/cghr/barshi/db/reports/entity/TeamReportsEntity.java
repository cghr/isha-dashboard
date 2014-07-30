package org.cghr.barshi.db.reports.entity;

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
@Table(name = "rep_team")
public class TeamReportsEntity {
	@Id
	@Column(name = "id")
	private Integer id = null;
	
	@Embedded
	private Team team = new Team();

	@OneToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "rep_teamuser", joinColumns = {
		@JoinColumn(name = "teamId", referencedColumnName = "id")
	}, inverseJoinColumns = {
		@JoinColumn(name = "userId", referencedColumnName = "id")
	})
	private HashSet<UserReportsEntity> surveyors = null;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "rep_assignment", joinColumns = {
			@JoinColumn(name = "teamId", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "areaId", referencedColumnName = "areaId")
	})
	private HashSet<AreaReportsEntity> areas = null;
	
	protected TeamReportsEntity() {
		super();
	}
	
	public TeamReportsEntity(int id) {
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
	
	public void addSurveyor(UserReportsEntity surveyor) {
		surveyors.add(surveyor);
	}
	
	public void removeSurveyor(UserReportsEntity surveyor) {
		surveyors.remove(surveyor);
	}
	
	public Set<UserReportsEntity> getSurveyors() {
		HashSet<UserReportsEntity> surveyorSet = new HashSet<UserReportsEntity>();
		
		if(surveyors != null) {
			surveyorSet.addAll(surveyors);
		}
		
		return surveyorSet;
	}
	
	public void addArea(AreaReportsEntity area) {
		areas.add(area);
	}
	
	public void removeArea(AreaReportsEntity area) {
		areas.remove(area);
	}
	
	public Set<AreaReportsEntity> getAreas() {
		HashSet<AreaReportsEntity> areaSet = new HashSet<AreaReportsEntity>();
		
		if(areas != null) {
			areaSet.addAll(areas);
		}
		
		return areaSet;
	}
}
