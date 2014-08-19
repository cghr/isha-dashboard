package org.cghr.barshi.db.reports.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rep_healthCamp")
public class HealthCampReportEntity {
	@EmbeddedId
	private EmbeddedReportsId id = null;
	
	@Column(name = "population")
	private Integer population = -1;
	
	@Column(name = "eligible")
	private Integer eligible = -1;
	
	@Column(name = "day", columnDefinition = "DATE")
	private Date day = null;
	
	@Column(name = "completed")
	private Integer completed = -1;
	
	@Column(name = "completedCumulative")
	private Integer completedCumulative = -1;
	
	@Column(name = "remaining")
	private Integer remaining = -1;
	
	@Column(name = "bloodSamples")
	private Integer bloodSamples = -1;
	
	@Column(name = "bloodSamplesNotCollected")
	private Integer bloodSamplesNotCollected = -1;
	
	public EmbeddedReportsId getId() {
		return this.id;
	}
	
	protected HealthCampReportEntity() {
		super();
	}
	
	public HealthCampReportEntity(Date reportDate, int areaId, int userId) {
		id = new EmbeddedReportsId(reportDate, areaId, userId);
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getEligible() {
		return eligible;
	}

	public void setEligible(Integer eligible) {
		this.eligible = eligible;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	public Integer getCompletedCumulative() {
		return completedCumulative;
	}

	public void setCompletedCumulative(Integer completedCumulative) {
		this.completedCumulative = completedCumulative;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public Integer getBloodSamples() {
		return bloodSamples;
	}

	public void setBloodSamples(Integer bloodSamples) {
		this.bloodSamples = bloodSamples;
	}

	public Integer getBloodSamplesNotCollected() {
		return bloodSamplesNotCollected;
	}

	public void setBloodSamplesNotCollected(Integer bloodSamplesNotCollected) {
		this.bloodSamplesNotCollected = bloodSamplesNotCollected;
	}
}
