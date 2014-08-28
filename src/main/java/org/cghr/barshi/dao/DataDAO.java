package org.cghr.barshi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.cghr.barshi.db.JPAUtils;
import org.cghr.barshi.db.reports.entity.EnumerationReportEntity;
import org.cghr.barshi.db.reports.entity.HealthCampReportEntity;
import org.cghr.barshi.db.reports.entity.HealthCheckupReportEntity;

public class DataDAO {
	private static DataDAO dataDao = null;

	public static DataDAO getInstance() {
		if (dataDao == null) {
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

		Connection connection = null;

		try {
			connection = JPAUtils.getInstance().getDataDatabaseConnection();

			enumerationReport.setNoOfPhysicalDwellingsVisited(getNoOfPhysicalDwellingsVisitedDuringEnumeration(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfPhysicalDwellingsAdded(getNoOfPhysicalDwellingsAddedDuringEnumeration(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfHouseholdsVisited(getNoOfHouseholdsVisitedDuringEnumeration(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfHouseholdsAdded(getNoOfHouseholdsAdded(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfMembersAdded(getNoOfMembersAdded(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfHouseholdsNotAvailable(getNoOfHouseholdsNotAvailable(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfPhysicalDwellingsRevisited(getNoOfPhysicalDwellingsRevisited(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfPhysicalDwellingsAddedOrUpdated(getNoOfPhysicalDwellingsAddedOrUpdated(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfHouseholdsRevisited(getNoOfHouseholdsRevisited(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfHouseholdsAdedOrUpdated(getNoOfHouseholdsAdedOrUpdated(reportDate, areaId, userId, connection));
			enumerationReport.setNoOfMembersAddedOrUpdated(getNoOfMembersAddedOrUpdated(reportDate, areaId, userId, connection));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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

		Connection connection = null;

		try {
			connection = JPAUtils.getInstance().getDataDatabaseConnection();

			healthCheckupReport.setNoOfPhysicalDwellingsVisited(getNoOfPhysicalDwellingsVisitedDuringHealtCheckup(reportDate, areaId, userId, connection));
			healthCheckupReport.setNoOfHouseholdsVisited(getNoOfHouseholdsVisitedDuringHealthCheckup(reportDate, areaId, userId, connection));
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
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return healthCheckupReport;
	}

	private Integer getNoOfHouseholdsVisitedDuringHealthCheckup(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfPhysicalDwellingsVisitedDuringHealtCheckup(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
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

		Connection connection = null;

		try {
			connection = JPAUtils.getInstance().getDataDatabaseConnection();

			healthCampReport.setPopulation(getPopulation(reportDate, areaId, userId, connection));
			healthCampReport.setEligible(getEligible(reportDate, areaId, userId, connection));
			healthCampReport.setDay(getDay(reportDate, areaId, userId, connection));
			healthCampReport.setCompleted(getCompleted(reportDate, areaId, userId, connection));
			healthCampReport.setCompletedCumulative(getCompletedCumulative(reportDate, areaId, userId, connection));
			healthCampReport.setRemaining(getRemaining(reportDate, areaId, userId, connection));
			healthCampReport.setBloodSamples(getBloodSamples(reportDate, areaId, userId, connection));
			healthCampReport.setBloodSamplesNotCollected(getBloodSamplesNotCollected(reportDate, areaId, userId, connection));
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return healthCampReport;
	}

	private Integer getBloodSamplesNotCollected(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getBloodSamples(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getRemaining(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getCompletedCumulative(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getCompleted(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Date getDay(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getEligible(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPopulation(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoDBSCollectionRate(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoDBSCollectedByThirdVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPercentParticipatedByThirdVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoParticipatedInThirdVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPercentParticipatedBySecondVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoParticipatedInSecondVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPercentParticipatedInFirstVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoParticipatedInFirstVisit(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoRefusal(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoPendingInterviews(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoRefusedConsent(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoSignedConsent(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoNotAvailableAtHousehold(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoAvailableAtHousehold(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getMinTargetToBeAttained(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getNoOfEligibleAdults(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	// FIXME: TBD: Physical Dwellings Visited = Physical Dwellings Added
	private Integer getNoOfPhysicalDwellingsVisitedDuringEnumeration(Date reportDate, Integer areaId, Integer userId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM house WHERE SUBSTR(houseId,1,2)=? AND SUBSTR(houseId,3,5)=? AND CAST(timelog AS DATE) = CAST(? AS DATE)");
		preparedStatement.setString(1, userId.toString());
		preparedStatement.setString(2, areaId.toString());
		preparedStatement.setDate(3, new java.sql.Date(reportDate.getTime()));
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		Integer physicalDwellingsVisited = rs.getInt(1);
		
		return physicalDwellingsVisited;
	}

	private Integer getNoOfPhysicalDwellingsAddedDuringEnumeration(Date reportDate, Integer areaId, Integer userId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM house WHERE SUBSTR(houseId,1,2)=? AND SUBSTR(houseId,3,3)=? AND CAST(timelog AS DATE) = CAST(? AS DATE)");
		preparedStatement.setString(1, userId.toString());
		preparedStatement.setString(2, areaId.toString());
		preparedStatement.setDate(3, new java.sql.Date(reportDate.getTime()));
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		Integer physicalDwellingsAdded = rs.getInt(1);
		
		return physicalDwellingsAdded;
	}

	private Integer getNoOfHouseholdsVisitedDuringEnumeration(Date reportDate, Integer areaId, Integer userId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM enumVisit WHERE CAST(timelog AS DATE) = CAST(? AS DATE) AND surveyor=?");
		preparedStatement.setDate(1, new java.sql.Date(reportDate.getTime()));
		preparedStatement.setInt(2, userId);
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		Integer houseHoldsVisited = rs.getInt(1);
		
		return houseHoldsVisited;
	}

	private Integer getNoOfHouseholdsAdded(Date reportDate, Integer areaId, Integer userId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM household WHERE CAST(timelog AS DATE) = CAST(? AS DATE) and SUBSTR(householdId,1,2)=?");
		preparedStatement.setDate(1, new java.sql.Date(reportDate.getTime()));
		preparedStatement.setInt(2, userId);
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		Integer houseHoldsAdded = rs.getInt(1);
		
		return houseHoldsAdded;
	}

	private Integer getNoOfMembersAdded(Date reportDate, Integer areaId, Integer userId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM member WHERE CAST(timelog AS DATE) = CAST(? AS DATE) and SUBSTR(memberId,1,2)=? AND SUBSTR(memberId,3,3)=?");
		preparedStatement.setDate(1, new java.sql.Date(reportDate.getTime()));
		preparedStatement.setString(2, userId.toString());
		preparedStatement.setString(3, userId.toString());
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		Integer houseHoldsAdded = rs.getInt(1);
		
		return houseHoldsAdded;
	}

	private Integer getNoOfHouseholdsNotAvailable(Date reportDate, Integer areaId, Integer userId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM enumVisit WHERE CAST(timelog AS DATE) = CAST(? AS DATE) AND surveyor=? AND hhAvailability IN ('Door temporarily locked','Door permanently locked')");
		preparedStatement.setDate(1, new java.sql.Date(reportDate.getTime()));
		preparedStatement.setInt(2, userId);
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		Integer householdsNotAvailable = rs.getInt(1);
		
		return householdsNotAvailable;
	}

	private Integer getNoOfPhysicalDwellingsRevisited(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// FIXME Auto-generated method stub
		return null;
	}

	// FIXME: Update Not Possible. Only Addition is Allowed.
	private Integer getNoOfPhysicalDwellingsAddedOrUpdated(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	// FIXME: Unable to obtain Household Revisit information
	private Integer getNoOfHouseholdsRevisited(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	// FIXME: Update not possible
	private Integer getNoOfHouseholdsAdedOrUpdated(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	// FIXME: Update Not Possible
	private Integer getNoOfMembersAddedOrUpdated(Date reportDate, Integer areaId, Integer userId, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}
}
