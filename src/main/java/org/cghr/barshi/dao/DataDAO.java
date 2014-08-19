package org.cghr.barshi.dao;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.cghr.barshi.db.reports.entity.EnumerationReportEntity;
import org.cghr.barshi.db.reports.entity.HealthCampReportEntity;
import org.cghr.barshi.db.reports.entity.HealthCheckupReportEntity;

public class DataDAO {
	private static DataDAO dataDao = null;
	
	public static DataDAO getInstance() {
		if(dataDao == null) {
			dataDao = new DataDAO();
		}
		
		return dataDao;
	}

	public EnumerationReportEntity getEnumerationReport(Date reportDate, Integer areaId, Integer userId) {
		EnumerationReportEntity enumerationReport = new EnumerationReportEntity(reportDate, areaId, userId);
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(reportDate);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		reportDate = cal.getTime();
		
		Connection connection = null;//JPAUtils.getInstance().getDataDatabaseConnection();
		
		enumerationReport.setNoOfPhysicalDwellingsVisited(getNoOfPhysicalDwellingsVisited(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfPhysicalDwellingsAdded(getNoOfPhysicalDwellingsAdded(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfHouseholdsVisited(getNoOfHouseholdsVisited(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfHouseholdsAdded(getNoOfHouseholdsAdded(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfMembersAdded(getNoOfMembersAdded(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfHouseholdsNotAvailable(getNoOfHouseholdsNotAvailable(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfPhysicalDwellingsRevisited(getNoOfPhysicalDwellingsRevisited(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfPhysicalDwellingsAddedOrUpdated(getNoOfPhysicalDwellingsAddedOrUpdated(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfHouseholdsRevisited(getNoOfHouseholdsRevisited(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfHouseholdsAdedOrUpdated(getNoOfHouseholdsAdedOrUpdated(reportDate, areaId, userId, connection));
		enumerationReport.setNoOfMembersAddedOrUpdated(getNoOfMembersAddedOrUpdated(reportDate, areaId, userId, connection));
		
		return enumerationReport;
	}
	
	public HealthCheckupReportEntity getHealthCheckupReport(Date reportDate, Integer areaId, Integer userId) {
		HealthCheckupReportEntity healthCheckupReport = new HealthCheckupReportEntity(reportDate, areaId, userId);
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(reportDate);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		reportDate = cal.getTime();
		
		Connection connection = null;//JPAUtils.getInstance().getDataDatabaseConnection();
		
		healthCheckupReport.setNoOfPhysicalDwellingsVisited(getNoOfPhysicalDwellingsVisited(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoOfHouseholdsVisited(getNoOfHouseholdsVisited(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoOfEligibleAdults(getNoOfEligibleAdults(reportDate, areaId, userId, connection));
		healthCheckupReport.setMinimumTarget(getMinTargetToBeAttained(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoAvailable(getNoAvailableAtHousehold(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoNotAvailable(getNoNotAvailableAtHousehold(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoSignedConsent(getNoSignedConsent(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoRefusedConsent(getNoRefusedConsent(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoOfPendingInterviews(getNoPendingInterviews(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoRefusal(getNoRefusal(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoParticipatedInFirstVisit(getNoParticipatedInFirstVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setPercentParticipatedInFirstVisit(getPercentParticipatedInFirstVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoParticipatedInSecondVisit(getNoParticipatedInSecondVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setPercentCumulativeParticipatedBySecondVisit(getPercentParticipatedBySecondVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoParticipatedInThirdVisit(getNoParticipatedInThirdVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setPercentCumulativeParticipatedByThirdVisit(getPercentParticipatedByThirdVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setNoDBSCollectedByThirdVisit(getNoDBSCollectedByThirdVisit(reportDate, areaId, userId, connection));
		healthCheckupReport.setDbsCollectionRate(getNoDBSCollectionRate(reportDate, areaId, userId, connection));
		
		return healthCheckupReport;
	}
	
	public HealthCampReportEntity getHealthCampReport(Date reportDate, Integer areaId, Integer userId) {
		HealthCampReportEntity healthCampReport = new HealthCampReportEntity(reportDate, areaId, userId);
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(reportDate);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		reportDate = cal.getTime();
		
		Connection connection = null;//JPAUtils.getInstance().getDataDatabaseConnection();
		
		healthCampReport.setPopulation(getPopulation(reportDate, areaId, userId, connection));
		healthCampReport.setEligible(getEligible(reportDate, areaId, userId, connection));
		healthCampReport.setDay(getDay(reportDate, areaId, userId, connection));
		healthCampReport.setCompleted(getCompleted(reportDate, areaId, userId, connection));
		healthCampReport.setCompletedCumulative(getCompletedCumulative(reportDate, areaId, userId, connection));
		healthCampReport.setRemaining(getRemaining(reportDate, areaId, userId, connection));
		healthCampReport.setBloodSamples(getBloodSamples(reportDate, areaId, userId, connection));
		healthCampReport.setBloodSamplesNotCollected(getBloodSamplesNotCollected(reportDate, areaId, userId, connection));
		
		return healthCampReport;
	}

	private Integer getBloodSamplesNotCollected(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getBloodSamples(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getRemaining(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getCompletedCumulative(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getCompleted(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Date getDay(Date reportDate, Integer areaId, Integer userId,
			Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getEligible(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPopulation(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoDBSCollectionRate(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoDBSCollectedByThirdVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPercentParticipatedByThirdVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoParticipatedInThirdVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPercentParticipatedBySecondVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoParticipatedInSecondVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPercentParticipatedInFirstVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoParticipatedInFirstVisit(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoRefusal(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoPendingInterviews(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoRefusedConsent(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoSignedConsent(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoNotAvailableAtHousehold(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoAvailableAtHousehold(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getMinTargetToBeAttained(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfEligibleAdults(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfPhysicalDwellingsVisited(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfPhysicalDwellingsAdded(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfHouseholdsVisited(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfHouseholdsAdded(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfMembersAdded(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfHouseholdsNotAvailable(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfPhysicalDwellingsRevisited(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfPhysicalDwellingsAddedOrUpdated(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfHouseholdsRevisited(Date reportDate, Integer areaId,
			Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfHouseholdsAdedOrUpdated(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfMembersAddedOrUpdated(Date reportDate,
			Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}
}
