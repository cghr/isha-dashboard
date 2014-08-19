package org.cghr.barshi.db.reports.uibeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.cghr.barshi.db.reports.entity.AreaReportsEntity;
import org.cghr.barshi.db.reports.entity.HealthCampReportEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;
import org.cghr.barshi.db.reports.entity.UserReportsEntity;

public class HealthCampUIBeanImpl implements HealthCampUIBeanInterface {
	
	private Date reportDate = null;
	
	private AreaReportsEntity area = null;
	
	private TeamReportsEntity team = null;
	
	private UserReportsEntity user = null;
	
	private HealthCampReportEntity healthCampReport = null;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	
	public HealthCampUIBeanImpl(Date reportDate, AreaReportsEntity area, TeamReportsEntity team, UserReportsEntity user, HealthCampReportEntity healthCampReport) {
		this.reportDate = reportDate;
		this.area = area;
		this.team = team;
		this.user = user;
		this.healthCampReport = healthCampReport;
	}

	@Override
	public String getTeam() {
		return team.getName();
	}

	@Override
	public String getArea() {
		return area.getName();
	}

	@Override
	public String getPopulation() {
		if(healthCampReport.getPopulation() != null) {
			return healthCampReport.getPopulation().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getEligible() {
		if(healthCampReport.getEligible() != null) {
			return healthCampReport.getEligible().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getDay() {
		if(healthCampReport.getId() != null && healthCampReport.getId().getReportDate() != null) {
			return healthCampReport.getId().getReportDate().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getCompleted() {
		if(healthCampReport.getCompleted() != null) {
			return healthCampReport.getCompleted().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getCompletedCumulative() {
		if(healthCampReport.getCompletedCumulative() != null) {
			return healthCampReport.getCompletedCumulative().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getRemaining() {
		if(healthCampReport.getRemaining() != null) {
			return healthCampReport.getRemaining().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getBloodSamples() {
		if(healthCampReport.getBloodSamples() != null) {
			return healthCampReport.getBloodSamples().toString();
		} else {
			return "-";
		}
	}

	@Override
	public String getBloodSamplesNotCollected() {
		if(healthCampReport.getBloodSamplesNotCollected() != null) {
			return healthCampReport.getBloodSamplesNotCollected().toString();
		} else {
			return "-";
		}
	}
}