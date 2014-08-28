package org.cghr.barshi.db.reports.uibeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.reports.entity.AreaReportsEntity;
import org.cghr.barshi.db.reports.entity.EnumerationReportEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;
import org.cghr.barshi.db.reports.entity.UserReportsEntity;

public class EnumerationUIBeanImpl implements EnumerationUIBeanInterface {
	
	private Date reportDate = null;
	
	private AreaReportsEntity area = null;
	
	private TeamReportsEntity team = null;
	
	private UserReportsEntity user = null;
	
	private EnumerationReportEntity enumerationReport = null;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	
	public EnumerationUIBeanImpl(Date reportDate, AreaReportsEntity area, TeamReportsEntity team, UserReportsEntity user, EnumerationReportEntity enumerationReport) {
		this.reportDate = reportDate;
		this.area = area;
		this.team = team;
		this.user = user;
		this.enumerationReport = enumerationReport;
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
		return user.getSurveyor().getId().toString();
	}

	@Override
	public String getDate() {
		return dateFormat.format(reportDate);
	}

	@Override
	public String getTimeOfDeparture() {
		if(team.getTimeOfDeparture() == null) {
			return "-";
		} else {
			return timeFormat.format(team.getTimeOfDeparture());
		}
	}

	@Override
	public String getTimeOfReturning() {
		if(team.getTimeOfReturn() == null) {
			return "-";
		} else {
			return timeFormat.format(team.getTimeOfReturn());
		}
	}

	@Override
	public String getNoOfPhysicalDwellingsVisited() {
		Integer no = enumerationReport.getNoOfPhysicalDwellingsVisited();
		
		/*if(no == null) {
			return "-";
		} else {
			return no.toString();
		}*/
		
		return "TBD";
	}

	@Override
	public String getNoOfPhysicalDwellingsAdded() {
		Integer no = enumerationReport.getNoOfPhysicalDwellingsAdded();
		
		/*if(no == null) {
			return "-";
		} else {
			return no.toString();
		}*/
		
		return "TBD";
	}

	@Override
	public String getNoOfHouseholdsVisited() {
		// TODO: Retrieve information from enumVisits;
		
		Integer no = enumerationReport.getNoOfHouseholdsVisited();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfHouseholdsAdded() {
		Integer no = enumerationReport.getNoOfHouseholdsAdded();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfMembersAdded() {
		Integer no = enumerationReport.getNoOfMembersAdded();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfHouseholdsNotAvailable() {
		Integer no = enumerationReport.getNoOfHouseholdsNotAvailable();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfPhysicalDwellingsRevisited() {
		Integer no = enumerationReport.getNoOfPhysicalDwellingsRevisited();
		
		/*if(no == null) {
			return "-";
		} else {
			return no.toString();
		}*/
		
		return "TBD";
	}

	@Override
	public String getNoOfPhysicalDwellingsAddedOrUpdated() {
		Integer no = enumerationReport.getNoOfPhysicalDwellingsAddedOrUpdated();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfHouseholdsRevisited() {
		Integer no = enumerationReport.getNoOfHouseholdsRevisited();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfHouseholdsAddedOrUpdated() {
		Integer no = enumerationReport.getNoOfHouseholdsAdedOrUpdated();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getNoOfMembersAddedOrUpdated() {
		Integer no = enumerationReport.getNoOfMembersAddedOrUpdated();
		
		if(no == null) {
			return "-";
		} else {
			return no.toString();
		}
	}

	@Override
	public String getRemark() {
		return enumerationReport.getRemark();
	}
}
