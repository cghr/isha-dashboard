package org.cghr.barshi.db.reports.uibeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.reports.entity.AreaReportsEntity;
import org.cghr.barshi.db.reports.entity.EnumerationReportEntity;
import org.cghr.barshi.db.reports.entity.HealthCheckupReportEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;
import org.cghr.barshi.db.reports.entity.UserReportsEntity;

public class HealthCheckupUIBeanImpl implements HealthCheckupUIBeanInterface {
	
	private Date reportDate = null;
	
	private AreaReportsEntity area = null;
	
	private TeamReportsEntity team = null;
	
	private UserReportsEntity user = null;
	
	private HealthCheckupReportEntity healthCheckupReport = null;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	
	public HealthCheckupUIBeanImpl(Date reportDate, AreaReportsEntity area, TeamReportsEntity team, UserReportsEntity user, HealthCheckupReportEntity healthCheckupReport) {
		this.reportDate = reportDate;
		this.area = area;
		this.team = team;
		this.user = user;
		this.healthCheckupReport = healthCheckupReport;
	}

	@Override
	public String getTeam() {
		return team.getName();
	}

	@Override
	public String getSurveyorName() {
		return user.getName();
	}

	@Override
	public String getSurveyorCode() {
		return user.getSurveyor().getId() + "";
	}

	@Override
	public String getDate() {
		return reportDate.toString();
	}

	@Override
	public String getTimeOfDeparture() {
		if(team.getTimeOfDeparture() == null) {
			return "-";
		} else {
			return team.getTimeOfDeparture().toString();
		}
	}

	@Override
	public String getTimeOfReturning() {
		if(team.getTimeOfReturn() == null) {
			return "-";
		} else {
			return team.getTimeOfReturn().toString();
		}
	}

	@Override
	public String getNoOfPhysicalDwellingsVisited() {
		if(healthCheckupReport.getNoOfPhysicalDwellingsVisited() != null) {
			return healthCheckupReport.getNoOfPhysicalDwellingsVisited().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoOfHouseholdsVisited() {
		if(healthCheckupReport.getNoOfHouseholdsVisited() != null) {
			return healthCheckupReport.getNoOfHouseholdsVisited().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoOfEligibleAdults() {
		if(healthCheckupReport.getNoOfEligibleAdults() != null) {
			return healthCheckupReport.getNoOfEligibleAdults().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getMinimumTarget() {
		if(healthCheckupReport.getMinimumTarget() != null) {
			return healthCheckupReport.getMinimumTarget().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoAvailable() {
		if(healthCheckupReport.getNoAvailable() != null) {
			return healthCheckupReport.getNoAvailable().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoNotAvailable() {
		if(healthCheckupReport.getNoNotAvailable() != null) {
			return healthCheckupReport.getNoNotAvailable().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoSignedConsent() {
		if(healthCheckupReport.getNoSignedConsent() != null) {
			return healthCheckupReport.getNoSignedConsent().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoRefusedConsent() {
		if(healthCheckupReport.getNoRefusedConsent() != null) {
			return healthCheckupReport.getNoRefusedConsent().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoOfPendingInterviews() {
		if(healthCheckupReport.getNoOfPendingInterviews() != null) {
			return healthCheckupReport.getNoOfPendingInterviews().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getPercentRefusal() {
		if(healthCheckupReport.getPercentRefusal() != null) {
			return healthCheckupReport.getPercentRefusal().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoParticipatedInFirstVisit() {
		if(healthCheckupReport.getNoParticipatedInFirstVisit() != null) {
			return healthCheckupReport.getNoParticipatedInFirstVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getPercentParticipatedInFirstVisit() {
		if(healthCheckupReport.getPercentParticipatedInFirstVisit() != null) {
			return healthCheckupReport.getPercentParticipatedInFirstVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoParticipatedInSecondVisit() {
		if(healthCheckupReport.getNoParticipatedInSecondVisit() != null) {
			return healthCheckupReport.getNoParticipatedInSecondVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getPercentCumulativeParticipatedBySecondVisit() {
		if(healthCheckupReport.getPercentCumulativeParticipatedBySecondVisit() != null) {
			return healthCheckupReport.getPercentCumulativeParticipatedBySecondVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoParticipatedInThirdVisit() {
		if(healthCheckupReport.getNoParticipatedInThirdVisit() != null) {
			return healthCheckupReport.getNoParticipatedInThirdVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getPercentCumulativeParticipatedByThirdVisit() {
		if(healthCheckupReport.getPercentCumulativeParticipatedByThirdVisit() != null) {
			return healthCheckupReport.getPercentCumulativeParticipatedByThirdVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoDbsCollectedByThirdVisit() {
		if(healthCheckupReport.getNoDBSCollectedByThirdVisit() != null) {
			return healthCheckupReport.getNoDBSCollectedByThirdVisit().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getDbsCollectionRate() {
		if(healthCheckupReport.getDbsCollectionRate() != null) {
			return healthCheckupReport.getDbsCollectionRate().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getNoOfHouseholdsAvailable() {
		if(healthCheckupReport.getNoOfHouseholdsVisited() != null) {
			return healthCheckupReport.getNoOfHouseholdsVisited().toString();
		} else {
			return "-";
		}
	}
	
	@Override
	public String getRemark() {
		if(healthCheckupReport.getRemark() != null) {
			return healthCheckupReport.getRemark().toString();
		} else {
			return "-";
		}
	}
}