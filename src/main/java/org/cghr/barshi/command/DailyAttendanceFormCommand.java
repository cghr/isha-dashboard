package org.cghr.barshi.command;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.cghr.barshi.component.AttendanceComponentWrapper;
import org.cghr.barshi.dao.ReportDAO;
import org.cghr.barshi.dao.TeamReportsDAO;
import org.cghr.barshi.db.BarshiEntityManagerFactory;
import org.cghr.barshi.db.data.entity.TeamDataEntity;
import org.cghr.barshi.db.reports.entity.ReportEntity;
import org.cghr.barshi.db.reports.entity.TeamReportsEntity;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class DailyAttendanceFormCommand implements MenuBar.Command {
	private TeamDataEntity team = null;
	private ReportEntity report = null;
	
	public DailyAttendanceFormCommand(TeamDataEntity team, Date date) {
		this.team = team;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		Date reportDate = cal.getTime();
		
		report = ReportDAO.getInstance().read(reportDate);
		if(report == null) {
			report = new ReportEntity(reportDate);
		}
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		Label teamLabel = new Label(team.getName());
		teamLabel.setSizeUndefined();
		teamLabel.setStyleName(Reindeer.LABEL_H1);
		
		// TODO: If report for the same day exists, and its status is verified, setEditable(false).
		
		// TODO: Display the attendance form
		final AttendanceComponentWrapper attendanceComponentWrapper = new AttendanceComponentWrapper(team, report.getId());
		Component attendanceComponent = attendanceComponentWrapper.getAttendanceComponent();
		
		// TODO: Display the Daily Enumeration report for the current team
		
		// TODO: Display the Daily Health Checkup report for the current team
		
		// TODO: Display the Daily Progress of Health Camp
		
		// TODO: If report does not exist, then evaluate Daily Reports for the current team.
		
		final String okItem = "I have verified the above information, and have not found any mistakes";
		final String problemItem = "I have found irregularities in the above information";
		
		final OptionGroup optionGroup = new OptionGroup();
		optionGroup.setImmediate(true);
		optionGroup.addItem(okItem);
		optionGroup.addItem(problemItem);
		optionGroup.setMultiSelect(false);
		
		final CheckBox synchronizedCheckBox = new CheckBox("I have ensured that all the surveyors' data has been synchronized.");
		synchronizedCheckBox.setVisible(false);
		synchronizedCheckBox.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				boolean selected = (Boolean) event.getProperty().getValue();
				report.setSynchronized(selected);
			}
		});
		
		final TextArea problemReport = new TextArea();
		problemReport.setWidth("100%");
		problemReport.setInputPrompt("Describe the problem here");
		problemReport.setVisible(false);
		
		problemReport.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String problem = (String) event.getProperty().getValue();
				report.setProblem(problem);
			}
		});
		
		optionGroup.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				// Change Values of the report object;
				String selected = (String) optionGroup.getValue();
				
				if(okItem.equals(selected)) {
					report.setOk(true);
					report.setSynchronized(null);
					synchronizedCheckBox.setVisible(false);
					problemReport.setVisible(false);
				} else if(problemItem.equals(selected)) {
					report.setOk(false);
					synchronizedCheckBox.setVisible(true);
					problemReport.setVisible(true);
				}
			}
		});
		
		Button submitButton = new Button("Submit");
		submitButton.setSizeUndefined();
		submitButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				TeamReportsEntity teamReports = attendanceComponentWrapper.getTeamReport();
				if(!teamReports.isValid()) {
					Notification.show("Error!",teamReports.getValidationError(),Notification.Type.ERROR_MESSAGE);
					return;
				}
				
				if(!report.isValid()) {
					Notification.show("Error!", report.getValidationError(), Notification.Type.ERROR_MESSAGE);
					return;
				}
				
				EntityManager entityManager = BarshiEntityManagerFactory.getInstance().createReportsEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				
				try {
					TeamReportsEntity teamReport = attendanceComponentWrapper.getTeamReport();
					
					entityManager.persist(teamReport);
					entityManager.persist(report);
					
					entityManager.detach(teamReport);
					entityManager.detach(report);
					
					transaction.commit();
					Notification.show("Success!", "Report has been submitted successfully!", Notification.Type.HUMANIZED_MESSAGE);
				} catch(Exception e) {
					transaction.rollback();
					Notification.show("Error!", "There was some problem saving the report to the database. Please contact the administrator", Notification.Type.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					entityManager.close();
				}
			}
		});
		
		VerticalLayout problemLayout = new VerticalLayout();
		problemLayout.setSpacing(true);
		problemLayout.setWidth("400px");
		problemLayout.addComponent(optionGroup);
		problemLayout.addComponent(synchronizedCheckBox);
		problemLayout.addComponent(problemReport);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("100%");
		verticalLayout.setSpacing(true);
		verticalLayout.addComponent(teamLabel);
		verticalLayout.setComponentAlignment(teamLabel, Alignment.MIDDLE_CENTER);
		verticalLayout.addComponent(attendanceComponent);
		verticalLayout.setComponentAlignment(attendanceComponent, Alignment.MIDDLE_CENTER);
		verticalLayout.addComponent(problemLayout);
		verticalLayout.setComponentAlignment(problemLayout, Alignment.MIDDLE_CENTER);
		verticalLayout.addComponent(submitButton);
		verticalLayout.setComponentAlignment(submitButton, Alignment.MIDDLE_CENTER);
		
		Window window = new Window("Attendance Form");
		window.setSizeFull();
		window.setModal(true);
		window.setResizable(false);
		window.setDraggable(false);
		
		window.setContent(verticalLayout);
		
		UI.getCurrent().addWindow(window);
	}
}
