package org.cghr.barshi.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.cghr.barshi.dao.AreaDataDAO;
import org.cghr.barshi.dao.DataDAO;
import org.cghr.barshi.dao.TeamDataDAO;
import org.cghr.barshi.db.data.entity.AreaDataEntity;
import org.cghr.barshi.db.data.entity.TeamDataEntity;
import org.cghr.barshi.db.data.entity.UserDataEntity;
import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.entity.Team;
import org.cghr.barshi.db.entity.User;
import org.cghr.barshi.db.reports.entity.AreaReportsEntity;
import org.cghr.barshi.db.reports.entity.EnumerationReportEntity;
import org.cghr.barshi.db.reports.entity.HealthCheckupReportEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;
import org.cghr.barshi.db.reports.entity.UserReportsEntity;
import org.cghr.barshi.db.reports.uibeans.EnumerationUIBeanImpl;
import org.cghr.barshi.db.reports.uibeans.EnumerationUIBeanInterface;
import org.cghr.barshi.db.reports.uibeans.HealthCheckupUIBeanImpl;
import org.cghr.barshi.db.reports.uibeans.HealthCheckupUIBeanInterface;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

public class HealthCheckupReportComponentWrapper {
	private List<Integer> areaIdList = null;
	
	private List<Integer> teamIdList = null;
	
	private List<Integer> userIdList = null;
	
	private Date reportDate = null;
	
	public HealthCheckupReportComponentWrapper(Date reportDate, List<Integer> areaIdList, List<Integer> teamIdList, List<Integer> userIdList) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(reportDate);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		this.reportDate = cal.getTime();
		
		this. areaIdList = areaIdList;
		
		this.teamIdList = teamIdList;
		
		this.userIdList = userIdList;
	}
	
	public Component evaluateHealthCheckupReportTable() {
		if(reportDate == null) {
			throw new RuntimeException("reportDate cannot be null");
		}
		
		BeanItemContainer<HealthCheckupUIBeanInterface> healthCheckupBeanItemContainer = new BeanItemContainer<HealthCheckupUIBeanInterface>(HealthCheckupUIBeanInterface.class);
		
		healthCheckupBeanItemContainer.addAll(evaluateHealthCheckupUIBeanList());
		
		Table healthCheckupReportTable = new Table();
		healthCheckupReportTable.setContainerDataSource(healthCheckupBeanItemContainer);
		
		List<Object> visibleColumns = new ArrayList<Object>();
		
		// TODO:
		visibleColumns.add("team");
		visibleColumns.add("surveyorName");
		visibleColumns.add("surveyorCode");
		visibleColumns.add("date");
		visibleColumns.add("timeOfDeparture");
		visibleColumns.add("timeOfReturning");
		visibleColumns.add("noOfPhysicalDwellingsVisited");
		visibleColumns.add("noOfHouseholdsVisited");
		visibleColumns.add("noOfEligibleAdults");
		visibleColumns.add("minimumTarget");
		visibleColumns.add("noAvailable");
		visibleColumns.add("noNotAvailable");
		visibleColumns.add("noSignedConsent");
		visibleColumns.add("noRefusedConsent");
		visibleColumns.add("noOfPendingInterviews");
		visibleColumns.add("noParticipatedInFirstVisit");
		visibleColumns.add("percentParticipatedInFirstVisit");
		visibleColumns.add("noParticipatedInSecondVisit");
		visibleColumns.add("percentCumulativeParticipatedBySecondVisit");
		visibleColumns.add("noParticipatedInThirdVisit");
		visibleColumns.add("percentCumulativeParticipatedByThirdVisit");
		visibleColumns.add("noDbsCollectedByThirdVisit");
		visibleColumns.add("dbsCollectionRate");
		visibleColumns.add("remark");
		
		healthCheckupReportTable.setVisibleColumns(visibleColumns.toArray());
		
		return healthCheckupReportTable;
	}
	
	private Collection<HealthCheckupUIBeanInterface> evaluateHealthCheckupUIBeanList() {
		List<TeamReportsEntity> teamList_reports = getCurrentTeamList();
		
		List<HealthCheckupUIBeanInterface> enumerationUIBeanList = new ArrayList<HealthCheckupUIBeanInterface>();
		
		for(TeamReportsEntity team_reports : teamList_reports) {
			for(AreaReportsEntity area_reports : team_reports.getAreas()) {
				for(UserReportsEntity surveyor_reports : team_reports.getSurveyors()) {
					// TODO: Create HealthCheckupReportEntity
					HealthCheckupReportEntity healthCheckupReport = DataDAO.getInstance().getHealthCheckupReport(reportDate, area_reports.getArea().getId(), surveyor_reports.getSurveyor().getId());
					
					HealthCheckupUIBeanInterface healthCheckupUIBean = new HealthCheckupUIBeanImpl(reportDate, area_reports, team_reports, surveyor_reports, healthCheckupReport);
					enumerationUIBeanList.add(healthCheckupUIBean);
				}
			}
		}
		
		return enumerationUIBeanList;
	}
	
	private List<TeamReportsEntity> getCurrentTeamList() {
		System.out.println("inGetCurrentTeamList");
		List<TeamReportsEntity> teamList_report = new ArrayList<TeamReportsEntity>();
		
		if(teamIdList == null || teamIdList.size() == 0) {
			System.out.println("teamIDList is empty or null");
			List<TeamDataEntity> teamList_data = TeamDataDAO.getInstance().getAllTeams();
			
			System.out.println("teamList_data.size: " + teamList_data.size());
			
			for(TeamDataEntity team_data : teamList_data) {
				System.out.println("Inside Team Data Loop for team " + team_data.getName());
				Team team = team_data.getTeam();
				
				TeamReportsEntity team_report = new TeamReportsEntity(team.getId(), reportDate, team);
				teamList_report.add(team_report);
				
				for(AreaDataEntity area_data : team_data.getAreas()) {
					System.out.println("Inside TeamData Area Loop" );
					Area area = area_data.getArea();
					
					AreaReportsEntity area_report = new AreaReportsEntity(area.getId(), reportDate, area);
					team_report.addArea(area_report);
				}
				
				for(UserDataEntity surveyor_data : team_data.getSurveyors()) {
					System.out.println("Inside TeamData Surveyor Loop");
					User surveyor = surveyor_data.getUser();
					
					UserReportsEntity user_report = new UserReportsEntity(surveyor.getId(), reportDate, surveyor);
					team_report.addSurveyor(surveyor);
				}
			}
		} else {
			// TODO:
		}
		
		System.out.println("Returning teamList_report");
		
		return teamList_report;
	}
}
