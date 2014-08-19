package org.cghr.barshi.db.reports.uibeans;

public interface HealthCheckupUIBeanInterface {
	public String getTeam();
	
	public String getSurveyorName();
	
	public String getSurveyorCode();
	
	public String getDate();
	
	public String getTimeOfDeparture();
	
	public String getTimeOfReturning();
	
	public String getNoOfPhysicalDwellingsVisited();

	public String getNoOfHouseholdsVisited();
	
	public String getNoOfHouseholdsAvailable();

	public String getNoOfEligibleAdults();

	public String getMinimumTarget();

	public String getNoAvailable();

	public String getNoNotAvailable();

	public String getNoSignedConsent();

	public String getNoRefusedConsent();

	public String getNoOfPendingInterviews();

	public String getPercentRefusal();

	public String getNoParticipatedInFirstVisit();
	
	public String getPercentParticipatedInFirstVisit();
	
	public String getNoParticipatedInSecondVisit();
	
	public String getPercentCumulativeParticipatedBySecondVisit();
	
	public String getPercentCumulativeParticipatedByThirdVisit();
	
	public String getNoParticipatedInThirdVisit();
	
	public String getNoDbsCollectedByThirdVisit();
	
	public String getDbsCollectionRate();
	
	public String getRemark();
}
