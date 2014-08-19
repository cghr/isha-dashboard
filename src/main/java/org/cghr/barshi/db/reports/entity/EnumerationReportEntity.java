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
@Table(name = "rep_enumeration")
public class EnumerationReportEntity {
	@EmbeddedId
	private EmbeddedReportsId id = null;
	
	@Column(name = "pdVisited")
	private Integer noOfPhysicalDwellingsVisited = -1;
	
	@Column(name = "pdAdded")
	private Integer noOfPhysicalDwellingsAdded = -1;
	
	@Column(name = "hhVisited")
	private Integer noOfHouseHoldsVisited = -1;
	
	@Column(name = "hhAdded")
	private Integer noOfHouseholdsAdded = -1;
	
	@Column(name = "memAdded")
	private Integer noOfMembersAdded = -1;
	
	@Column(name = "hhNotAvailable")
	private Integer noOfHouseholdsNotAvailable = -1;
	
	@Column(name = "pdRevisited")
	private Integer noOfPhysicalDwellingsRevisited = -1;
	
	@Column(name = "pdAddedOrUpdated")
	private Integer noOfPhysicalDwellingsAddedOrUpdated = -1;
	
	@Column(name = "hhRevisited")
	private Integer noOfHouseholdsRevisited = -1;
	
	@Column(name = "hhAddedOrUpdated")
	private Integer noOfHouseholdsAdedOrUpdated = -1;
	
	@Column(name = "memAddedOrUpdated")
	private Integer noOfMembersAddedOrUpdated = -1;
	
	@Column(name = "remark")
	private String remark = null;
	
	protected EnumerationReportEntity() {
		super();
	}
	
	public EnumerationReportEntity(Date reportDate, int areaId, int userId) {
		id = new EmbeddedReportsId(reportDate, areaId, userId);
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UserReportsEntity surveyor = null;
	
	public Integer getNoOfPhysicalDwellingsVisited() {
		return noOfPhysicalDwellingsVisited;
	}

	public EmbeddedReportsId getId() {
		return id;
	}

	public void setNoOfPhysicalDwellingsVisited(Integer noOfPhysicalDwellingsVisited) {
		this.noOfPhysicalDwellingsVisited = noOfPhysicalDwellingsVisited;
	}

	public Integer getNoOfPhysicalDwellingsAdded() {
		return noOfPhysicalDwellingsAdded;
	}

	public void setNoOfPhysicalDwellingsAdded(Integer noOfPhysicalDwellingsAdded) {
		this.noOfPhysicalDwellingsAdded = noOfPhysicalDwellingsAdded;
	}

	public Integer getNoOfHouseholdsVisited() {
		return noOfHouseHoldsVisited;
	}

	public void setNoOfHouseholdsVisited(Integer noOfHouseHoldsVisited) {
		this.noOfHouseHoldsVisited = noOfHouseHoldsVisited;
	}

	public Integer getNoOfHouseholdsAdded() {
		return noOfHouseholdsAdded;
	}

	public void setNoOfHouseholdsAdded(Integer noOfHouseholdsAdded) {
		this.noOfHouseholdsAdded = noOfHouseholdsAdded;
	}

	public Integer getNoOfMembersAdded() {
		return noOfMembersAdded;
	}

	public void setNoOfMembersAdded(Integer noOfMembersAdded) {
		this.noOfMembersAdded = noOfMembersAdded;
	}

	public Integer getNoOfHouseholdsNotAvailable() {
		return noOfHouseholdsNotAvailable;
	}

	public void setNoOfHouseholdsNotAvailable(Integer noOfHouseholdsNotAvailable) {
		this.noOfHouseholdsNotAvailable = noOfHouseholdsNotAvailable;
	}

	public Integer getNoOfPhysicalDwellingsRevisited() {
		return noOfPhysicalDwellingsRevisited;
	}

	public void setNoOfPhysicalDwellingsRevisited(
			Integer noOfPhysicalDwellingsRevisited) {
		this.noOfPhysicalDwellingsRevisited = noOfPhysicalDwellingsRevisited;
	}

	public Integer getNoOfPhysicalDwellingsAddedOrUpdated() {
		return noOfPhysicalDwellingsAddedOrUpdated;
	}

	public void setNoOfPhysicalDwellingsAddedOrUpdated(
			Integer noOfPhysicalDwellingsAddedOrUpdated) {
		this.noOfPhysicalDwellingsAddedOrUpdated = noOfPhysicalDwellingsAddedOrUpdated;
	}

	public Integer getNoOfHouseholdsRevisited() {
		return noOfHouseholdsRevisited;
	}

	public void setNoOfHouseholdsRevisited(Integer noOfHouseholdsRevisited) {
		this.noOfHouseholdsRevisited = noOfHouseholdsRevisited;
	}

	public Integer getNoOfHouseholdsAdedOrUpdated() {
		return noOfHouseholdsAdedOrUpdated;
	}

	public void setNoOfHouseholdsAdedOrUpdated(Integer noOfHouseholdsAdedOrUpdated) {
		this.noOfHouseholdsAdedOrUpdated = noOfHouseholdsAdedOrUpdated;
	}

	public Integer getNoOfMembersAddedOrUpdated() {
		return noOfMembersAddedOrUpdated;
	}

	public void setNoOfMembersAddedOrUpdated(Integer noOfMembersAddedOrUpdated) {
		this.noOfMembersAddedOrUpdated = noOfMembersAddedOrUpdated;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} else if (obj instanceof EnumerationReportEntity) {
			return id.equals(((EnumerationReportEntity) obj).getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
