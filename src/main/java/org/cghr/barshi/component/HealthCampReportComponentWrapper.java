package org.cghr.barshi.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.cghr.barshi.dao.DataDAO;
import org.cghr.barshi.dao.TeamDataDAO;
import org.cghr.barshi.db.data.entity.AreaDataEntity;
import org.cghr.barshi.db.data.entity.TeamDataEntity;
import org.cghr.barshi.db.data.entity.UserDataEntity;
import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.entity.Team;
import org.cghr.barshi.db.entity.User;
import org.cghr.barshi.db.reports.entity.AreaReportsEntity;
import org.cghr.barshi.db.reports.entity.HealthCampReportEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;
import org.cghr.barshi.db.reports.entity.UserReportsEntity;
import org.cghr.barshi.db.reports.uibeans.HealthCampUIBeanImpl;
import org.cghr.barshi.db.reports.uibeans.HealthCampUIBeanInterface;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

public class HealthCampReportComponentWrapper {
	private List<Integer> areaIdList = null;
	
	private List<Integer> teamIdList = null;
	
	private List<Integer> userIdList = null;
	
	private Date reportDate = null;
	
	public HealthCampReportComponentWrapper(Date reportDate, List<Integer> areaIdList, List<Integer> teamIdList, List<Integer> userIdList) {
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
	
	public Component evaluateHealthCampReportTable() {
		if(reportDate == null) {
			throw new RuntimeException("reportDate cannot be null");
		}
		
		BeanItemContainer<HealthCampUIBeanInterface> healthCampBeanItemContainer = new BeanItemContainer<HealthCampUIBeanInterface>(HealthCampUIBeanInterface.class);
		
		healthCampBeanItemContainer.addAll(evaluateHealthCampUIBeanList());
		
		Table healthCampReportTable = new Table();
		healthCampReportTable.setContainerDataSource(healthCampBeanItemContainer);
		
		List<Object> visibleColumns = new ArrayList<Object>();
		
		// TODO:
		visibleColumns.add("team");
		visibleColumns.add("area");
		visibleColumns.add("population");
		visibleColumns.add("eligible");
		visibleColumns.add("day");
		visibleColumns.add("completed");
		visibleColumns.add("completedCumulative");
		visibleColumns.add("remaining");
		visibleColumns.add("bloodSamples");
		visibleColumns.add("bloodSamplesNotCollected");
		
		healthCampReportTable.setVisibleColumns(visibleColumns.toArray());
		
		return healthCampReportTable;
	}
	
	private Collection<HealthCampUIBeanInterface> evaluateHealthCampUIBeanList() {
		List<TeamReportsEntity> teamList_reports = getCurrentTeamList();
		
		List<HealthCampUIBeanInterface> healthCampUIBeanList = new ArrayList<HealthCampUIBeanInterface>();
		
		for(TeamReportsEntity team_reports : teamList_reports) {
			for(AreaReportsEntity area_reports : team_reports.getAreas()) {
				for(UserReportsEntity surveyor_reports : team_reports.getSurveyors()) {
					// TODO: Create HealthCampReportEntity
					HealthCampReportEntity healthCampReport = DataDAO.getInstance().getHealthCampReport(reportDate, area_reports.getArea().getId(), surveyor_reports.getSurveyor().getId());
					
					HealthCampUIBeanInterface healthCampUIBean = new HealthCampUIBeanImpl(reportDate, area_reports, team_reports, surveyor_reports, healthCampReport);
					healthCampUIBeanList.add(healthCampUIBean);
				}
			}
		}
		
		return healthCampUIBeanList;
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
