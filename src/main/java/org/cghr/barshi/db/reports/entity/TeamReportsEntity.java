package org.cghr.barshi.db.reports.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cghr.barshi.db.data.entity.TeamDataEntity;
import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.entity.Team;
import org.cghr.barshi.db.entity.User;

@Entity
@Table(name = "rep_team")
public class TeamReportsEntity {
	@EmbeddedId
	private ReportsId id = new ReportsId();

	@Embedded
	private Team team = new Team();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "rep_teamuser", joinColumns = {
		@JoinColumn(name = "teamId", referencedColumnName = "id", columnDefinition = "BIGINT(10)")
	}, inverseJoinColumns = {
		@JoinColumn(name = "userId", referencedColumnName = "id", columnDefinition = "BIGINT(10)")
	})
	private Set<UserReportsEntity> surveyors = new HashSet<UserReportsEntity>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "rep_assignment", joinColumns = {
		@JoinColumn(name = "teamId", referencedColumnName = "id", columnDefinition = "BIGINT(10)")
	}, inverseJoinColumns = {
		@JoinColumn(name = "areaId", referencedColumnName = "id", columnDefinition = "BIGINT(10)")
	})
	private Set<AreaReportsEntity> areas = new HashSet<AreaReportsEntity>();
	
	@Column(name = "departureTime")
	private Date timeOfDeparture = null;
	
	@Column(name = "returnTime")
	private Date timeOfReturn = null;
	
	@ManyToOne
	@JoinColumn(name = "reportDate", referencedColumnName = "id")
	private ReportEntity report = null;

	protected TeamReportsEntity() {
		super();
	}

	public TeamReportsEntity(int id, Date date, Team team) {
		this.id.setId(id);
		this.id.setReportDate(date);
		this.team = team;
	}

	public ReportsId getId() {
		return id;
	}

	private void setId(ReportsId id) {
		this.id = id;
	}

	public String getName() {
		return team.getName();
	}

	public void setName(String name) {
		team.setName(name);
	}

	public void addSurveyor(UserReportsEntity surveyor_reports) {
		surveyors.add(surveyor_reports);
	}
	
	public void addSurveyor(User surveyor) {
		UserReportsEntity surveyor_reports = new UserReportsEntity(surveyor.getId(), id.getDate(), surveyor);
		addSurveyor(surveyor_reports);
	}
	
	public void removeSurveyor(UserReportsEntity surveyor) {
		
		surveyors.remove(surveyor);
	}

	public Set<UserReportsEntity> getSurveyors() {
		HashSet<UserReportsEntity> surveyorSet = new HashSet<UserReportsEntity>();

		if (surveyors != null) {
			surveyorSet.addAll(surveyors);
		}

		return surveyorSet;
	}

	public void addArea(AreaReportsEntity area_reports) {
		areas.add(area_reports);
	}
	
	public void addArea(Area area) {
		AreaReportsEntity area_reports = new AreaReportsEntity(area.getId(), id.getDate(), area);
		addArea(area_reports);
	}

	public void removeArea(AreaReportsEntity area) {
		areas.remove(area);
	}

	public Set<AreaReportsEntity> getAreas() {
		HashSet<AreaReportsEntity> areaSet = new HashSet<AreaReportsEntity>();

		if (areas != null) {
			areaSet.addAll(areas);
		}

		return areaSet;
	}
	
	public void setTimeOfDeparture(Date timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}
	
	public Date getTimeOfDeparture() {
		return this.timeOfDeparture;
	}
	
	public void setTimeOfReturn(Date timeOfReturn) {
		this.timeOfReturn = timeOfReturn;
	}
	
	public Date getTimeOfReturn() {
		return this.timeOfReturn;
	}

	@Transient
	private String validationError = null;
	
	public boolean isValid() {
		// Check reason if surveyor is absent.
		for(UserReportsEntity surveyor : getSurveyors()) {
			if(!surveyor.isPresent()) {
				if(surveyor.getAbsentReason() == null || surveyor.getAbsentReason().trim().length() < 4) {
					validationError = "Surveyor " + surveyor.getName() + " is absent. Please provide a reason (> 4 characters)";
					return false;
				}
			}
		}
		
		if(timeOfDeparture == null) {
			validationError = "Please provide Time of Departure";
			return false;
		}
		
		if(timeOfReturn == null) {
			validationError = "Please provide Time of Return";
			return false;
		}
		
		// Time of departure must be on the same day of the report id.
		if(timeOfDeparture.getTime() - id.getDate().getTime() > (24 * 60 * 60 * 1000)) {
			validationError = "Date of Departure should be: " + id.getDate();
			return false;
		}
		
		// Time of return must be on the same day of the report id.
		if(timeOfReturn.getTime() - id.getDate().getTime() > (24 * 60 * 60 * 1000)) {
			validationError = "Date of Return should be: " + id.getDate();
			return false;
		}
		
		// Time of return must be after time of departure
		if(timeOfReturn.getTime() - timeOfDeparture.getTime() < 60 * 60 * 1000) {
			validationError = "Time of Return must be one hour later than time of departure";
			return false;
		}
		
		return true;
	}
	
	public String getValidationError() {
		return validationError;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} else if (obj instanceof TeamReportsEntity) {
			return id.equals(((TeamReportsEntity) obj).getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
