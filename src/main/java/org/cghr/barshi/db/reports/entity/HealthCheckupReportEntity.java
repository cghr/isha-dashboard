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
@Table(name = "rep_healthCheckup")
public class HealthCheckupReportEntity {
	@EmbeddedId
	private EmbeddedReportsId id = null;
	
	public EmbeddedReportsId getId() {
		return this.id;
	}
	
	protected HealthCheckupReportEntity() {
		super();
	}
	
	public HealthCheckupReportEntity(Date reportDate, int areaId, int userId) {
		id = new EmbeddedReportsId(reportDate, areaId, userId);
	}
	
	@Column(name = "pdVisited")
	private Integer noOfPhysicalDwellingsVisited = -1;
	
	public Integer getPercentRefusal() {
		return percentRefusal;
	}

	public void setPercentRefusal(Integer percentRefusal) {
		this.percentRefusal = percentRefusal;
	}

	@Column(name = "hhVisited")
	private Integer noOfHouseholdsVisited = -1;
	
	@Column(name = "eligibleAdults")
	private Integer noOfEligibleAdults = -1;
	
	@Column(name = "minTarget")
	private Integer minimumTarget = -1;
	
	@Column(name = "available")
	private Integer noAvailable = -1;
	
	@Column(name = "notAvailable")
	private Integer noNotAvailable = -1;
	
	@Column(name = "signedConsent")
	private Integer noSignedConsent = -1;
	
	@Column(name = "refusedConsent")
	private Integer noRefusedConsent = -1;
	
	@Column(name = "percentRefusal")
	private Integer percentRefusal = -1;
	
	@Column(name = "pendingInterviews")
	private Integer noOfPendingInterviews = -1;
	
	@Column(name = "refused")
	private Integer noRefusal = -1;
	
	@Column(name = "participatedInFirstVisit")
	private Integer noParticipatedInFirstVisit = -1;
	
	@Column(name = "percentParticipatedInFirstVisit")
	private Integer percentParticipatedInFirstVisit = -1;
	
	@Column(name = "participatedInSecondVisit")
	private Integer noParticipatedInSecondVisit = -1;
	
	@Column(name = "percentParticipatedBySecondVisit")
	private Integer percentCumulativeParticipatedBySecondVisit = -1;
	
	@Column(name = "participatedInThirdVisit")
	private Integer noParticipatedInThirdVisit = -1;
	
	@Column(name = "percentParticipatedByThirdVisit")
	private Integer percentCumulativeParticipatedByThirdVisit = -1;
	
	@Column(name = "dbsCollectedByThirdVisit")
	private Integer noDBSCollectedByThirdVisit = -1;
	
	@Column(name = "dbsCollectionRate")
	private Integer dbsCollectionRate = -1;
	
	@Column(name = "remark")
	private String remark = null;
	
	public Integer getNoOfPhysicalDwellingsVisited() {
		return noOfPhysicalDwellingsVisited;
	}

	public void setNoOfPhysicalDwellingsVisited(Integer noOfPhysicalDwellingsVisited) {
		this.noOfPhysicalDwellingsVisited = noOfPhysicalDwellingsVisited;
	}

	public Integer getNoOfHouseholdsVisited() {
		return noOfHouseholdsVisited;
	}

	public void setNoOfHouseholdsVisited(Integer noOfHouseholdsVisited) {
		this.noOfHouseholdsVisited = noOfHouseholdsVisited;
	}

	public Integer getNoOfEligibleAdults() {
		return noOfEligibleAdults;
	}

	public void setNoOfEligibleAdults(Integer noOfEligibleAdults) {
		this.noOfEligibleAdults = noOfEligibleAdults;
	}

	public Integer getMinimumTarget() {
		return minimumTarget;
	}

	public void setMinimumTarget(Integer minTargetToBeAttained) {
		this.minimumTarget = minTargetToBeAttained;
	}

	public Integer getNoAvailable() {
		return noAvailable;
	}

	public void setNoAvailable(Integer noAvailableAtHousehold) {
		this.noAvailable = noAvailableAtHousehold;
	}

	public Integer getNoNotAvailable() {
		return noNotAvailable;
	}

	public void setNoNotAvailable(Integer noNotAvailableAtHousehold) {
		this.noNotAvailable = noNotAvailableAtHousehold;
	}

	public Integer getNoSignedConsent() {
		return noSignedConsent;
	}

	public void setNoSignedConsent(Integer noSignedConsent) {
		this.noSignedConsent = noSignedConsent;
	}

	public Integer getNoRefusedConsent() {
		return noRefusedConsent;
	}

	public void setNoRefusedConsent(Integer noRefusedConsent) {
		this.noRefusedConsent = noRefusedConsent;
	}

	public Integer getNoOfPendingInterviews() {
		return noOfPendingInterviews;
	}

	public void setNoOfPendingInterviews(Integer noPendingInterviews) {
		this.noOfPendingInterviews = noPendingInterviews;
	}

	public Integer getNoRefusal() {
		return noRefusal;
	}

	public void setNoRefusal(Integer noRefusal) {
		this.noRefusal = noRefusal;
	}

	public Integer getNoParticipatedInFirstVisit() {
		return noParticipatedInFirstVisit;
	}

	public void setNoParticipatedInFirstVisit(Integer noParticipatedInFirstVisit) {
		this.noParticipatedInFirstVisit = noParticipatedInFirstVisit;
	}

	public Integer getPercentParticipatedInFirstVisit() {
		return percentParticipatedInFirstVisit;
	}

	public void setPercentParticipatedInFirstVisit(
			Integer percentParticipatedInFirstVisit) {
		this.percentParticipatedInFirstVisit = percentParticipatedInFirstVisit;
	}

	public Integer getNoParticipatedInSecondVisit() {
		return noParticipatedInSecondVisit;
	}

	public void setNoParticipatedInSecondVisit(Integer noParticipatedInSecondVisit) {
		this.noParticipatedInSecondVisit = noParticipatedInSecondVisit;
	}

	public Integer getPercentCumulativeParticipatedBySecondVisit() {
		return percentCumulativeParticipatedBySecondVisit;
	}

	public void setPercentCumulativeParticipatedBySecondVisit(
			Integer percentParticipatedBySecondVisit) {
		this.percentCumulativeParticipatedBySecondVisit = percentParticipatedBySecondVisit;
	}

	public Integer getNoParticipatedInThirdVisit() {
		return noParticipatedInThirdVisit;
	}

	public void setNoParticipatedInThirdVisit(Integer noParticipatedInThirdVisit) {
		this.noParticipatedInThirdVisit = noParticipatedInThirdVisit;
	}

	public Integer getPercentCumulativeParticipatedByThirdVisit() {
		return percentCumulativeParticipatedByThirdVisit;
	}

	public void setPercentCumulativeParticipatedByThirdVisit(
			Integer percentParticipatedByThirdVisit) {
		this.percentCumulativeParticipatedByThirdVisit = percentParticipatedByThirdVisit;
	}

	public Integer getNoDBSCollectedByThirdVisit() {
		return noDBSCollectedByThirdVisit;
	}

	public void setNoDBSCollectedByThirdVisit(Integer noDBSCollectedByThirdVisit) {
		this.noDBSCollectedByThirdVisit = noDBSCollectedByThirdVisit;
	}

	public Integer getDbsCollectionRate() {
		return dbsCollectionRate;
	}

	public void setDbsCollectionRate(Integer noDBSCollectionRate) {
		this.dbsCollectionRate = noDBSCollectionRate;
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
		} else if (obj instanceof HealthCheckupReportEntity) {
			return id.equals(((HealthCheckupReportEntity) obj).getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
