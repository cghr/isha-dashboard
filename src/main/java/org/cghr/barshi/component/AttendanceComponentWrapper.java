package org.cghr.barshi.component;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.cghr.barshi.db.data.entity.AreaDataEntity;
import org.cghr.barshi.db.data.entity.TeamDataEntity;
import org.cghr.barshi.db.data.entity.UserDataEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;
import org.cghr.barshi.db.reports.entity.UserReportsEntity;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class AttendanceComponentWrapper {
	private TeamDataEntity team_data = null;
	private TeamReportsEntity team_reports = null;
	
	private Date date = null;

	public AttendanceComponentWrapper(TeamDataEntity team_data, Date date) {
		this.team_data = team_data;
		this.date = date;
		this.team_reports = getTeamReports(team_data);
	}

	public Component getAttendanceComponent() {
		// Get Surveyor's list
		Collection<UserReportsEntity> userCollection_reports = team_reports.getSurveyors();
		
		Label attendanceLabel = new Label("Attendance");
		attendanceLabel.setSizeUndefined();
		attendanceLabel.setStyleName(Reindeer.LABEL_H2);
		
		VerticalLayout surveyorAttendanceLayout = new VerticalLayout();
		surveyorAttendanceLayout.setWidth("300px");
		surveyorAttendanceLayout.setSpacing(true);
		surveyorAttendanceLayout.setStyleName("sectioned");
		surveyorAttendanceLayout.addComponent(attendanceLabel);
		surveyorAttendanceLayout.setComponentAlignment(attendanceLabel, Alignment.MIDDLE_CENTER);
		
		// Generate attendance checkbox with reason for each surveyor
		for(final UserReportsEntity userReportsEntity : userCollection_reports) {
			final CheckBox checkBox = new CheckBox();
			checkBox.setWidth("127px");
			checkBox.setCaption(userReportsEntity.getName());
			
			final Label reasonLabel = new Label("Reason: ");
			
			final TextField reasonField = new TextField();
			reasonField.setWidth("100px");
			reasonField.setInputPrompt("Type Reason Here");
			reasonField.addValueChangeListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(ValueChangeEvent event) {
					String absentReason = (String) event.getProperty().getValue();
					userReportsEntity.setAbsentReason(absentReason);
					checkBox.setValue(false);
				}
			});
			
			checkBox.addValueChangeListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(ValueChangeEvent event) {
					boolean b = (Boolean) event.getProperty().getValue();
					userReportsEntity.setPresent(b);
					reasonLabel.setVisible(!b);
					reasonField.setVisible(!b);
				}
			});
			
			HorizontalLayout horizontalLayout = new HorizontalLayout();
			horizontalLayout.setSpacing(true);
			horizontalLayout.addComponent(checkBox);
			horizontalLayout.addComponent(reasonLabel);
			horizontalLayout.addComponent(reasonField);
			horizontalLayout.setComponentAlignment(reasonField, Alignment.MIDDLE_CENTER);
			
			surveyorAttendanceLayout.addComponent(horizontalLayout);
		}
		
		// Generate travel fields
		Label departureLabel = new Label("Time of Departure from Barshi:");
		departureLabel.setWidth("180px");
		
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.set(Calendar.MILLISECOND, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(startCalendar.getTime());
		endCalendar.add(Calendar.DATE, 1);
		
		PopupDateField departureDatePopup = new PopupDateField();
		departureDatePopup.setWidth("100px");
		departureDatePopup.setDateFormat("hh:mm a");
		departureDatePopup.setResolution(Resolution.MINUTE);
		departureDatePopup.setRangeStart(startCalendar.getTime());
		departureDatePopup.setRangeEnd(endCalendar.getTime());
		
		departureDatePopup.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				Date timeOfDeparture = (Date) event.getProperty().getValue();
				team_reports.setTimeOfDeparture(timeOfDeparture);
			}
		});
		
		Label returnLabel = new Label("Time of returning to Barshi:");
		returnLabel.setWidth("180px");
		
		PopupDateField returnDatePopup = new PopupDateField();
		returnDatePopup.setWidth("100px");
		returnDatePopup.setDateFormat("hh:mm a");
		returnDatePopup.setResolution(Resolution.MINUTE);
		returnDatePopup.setRangeStart(startCalendar.getTime());
		returnDatePopup.setRangeEnd(endCalendar.getTime());
		
		returnDatePopup.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				Date timeOfReturn = (Date) event.getProperty().getValue();
				team_reports.setTimeOfReturn(timeOfReturn);
			}
		});
		
		HorizontalLayout departureLayout = new HorizontalLayout();
		departureLayout.setSpacing(true);
		departureLayout.addComponent(departureLabel);
		departureLayout.addComponent(departureDatePopup);
		
		HorizontalLayout returnLayout = new HorizontalLayout();
		returnLayout.setSpacing(true);
		returnLayout.addComponent(returnLabel);
		returnLayout.addComponent(returnDatePopup);
		
		surveyorAttendanceLayout.addComponent(departureLayout);
		surveyorAttendanceLayout.addComponent(returnLayout);
		
		return surveyorAttendanceLayout;
	}
	
	public TeamReportsEntity getTeamReport() {
		return team_reports;
	}
	
	private TeamReportsEntity getTeamReports(TeamDataEntity team_data) {
		TeamReportsEntity teamReportsEntity = new TeamReportsEntity(team_data.getId(), date);
		
		for(UserDataEntity user_data : team_data.getSurveyors()) {
			teamReportsEntity.addSurveyor(user_data.getUser());
		}
		
		for(AreaDataEntity area_data : team_data.getAreas()) {
			teamReportsEntity.addArea(area_data.getArea());
		}
		
		return teamReportsEntity;
	}
}
